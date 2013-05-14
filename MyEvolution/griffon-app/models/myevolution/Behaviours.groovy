package myevolution

import org.codehaus.groovy.runtime.CurriedClosure

//import org.apache.log4j.Logger

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2011-02-10
 * Time: 21.56
 * To change this template use File | Settings | File Templates.
 */
class Behaviours {
    def reproduce = {Organism organism, World world, match ->
        def random = Math.random()
        if (match && random <= match && match * organism.energyLevel >= 10) {
            def coords = BehaviourUtils.randomNewCoords(organism, organism.radius * 2 + 1)
            if (!organism.collides(coords.x, coords.y) && !world.blocked(coords.x, coords.y)) {
                def clone = new Organism(null, coords.x as Float, coords.y, organism.radius, BehaviourUtils.randomDnaChange(organism.dna, 0.01))
                clone.color = BehaviourUtils.colorForDna(clone.dna)

                organism.radius /= 2

                // It should cost a little energy to reproduce
                2.times {
                    //consumeEnergy.call(organism, world)
                }

                clone.radius = organism.radius
                world.newOrganisms << clone
                world.allBehaviours.each {Behaviour behaviour ->
                    clone.world = world
                    clone.allBehaviours << new Behaviour(
                            action: behaviour.action,
                            threshold: behaviour.threshold,
                            condition: behaviour.condition,
                            startIndex: behaviour.startIndex
                    )
                }
            }
        }
    }

    def eat = {Organism organism, World world, match, collides ->
        println "eating: $collides"
        if (collides) {
            collides.each {Organism colliding ->
                println "eating..."
                def amount = Math.pow(organism.radius, 1 / 3) * 10
                if (amount > colliding.energyLevel) {
                    amount = colliding.energyLevel
                }
                storeEnergy.call(amount / 2)

                // What if storeEnergy is null in the following?
                colliding.chooseBehaviours().storeEnergy.call(-amount)
            }
        }
    }

    def randomMove = {Organism organism, World world, match ->
        def length = 5.0 * match

        // Anropa bara randommove om matchningen är tillräckligt bra
        def random = Math.random()
        if (match && random <= match) {
            length.times {
                consumeEnergy.call()
            }
            def randomNewCoords = BehaviourUtils.randomNewCoords(organism, length)
            def blocked = world.blocked(randomNewCoords.x, randomNewCoords.y)
            def collides = organism.collides(randomNewCoords.x, randomNewCoords.y)
            if (!blocked) {
                if (collides && eat) {
                    println "moving into other organism and should eat"
                    eat.call(collides)
                } else {
                    println "moving!"
                    organism.setCenterX(randomNewCoords.x)
                    organism.setCenterY(randomNewCoords.y)
                }
            }
            "Done with random move"
        }

    }

    def die = {Organism organism, World world, match ->
        if (organism.energyLevel <= 0) {
            organism.alive = false
        }
    }

    def photosyntesis = {Organism organism, World world, match ->
        def amount = Math.pow(organism.radius, 1 / 3) * 2 * match
        storeEnergy?.call(amount)
    }

    def storeEnergy = {Organism organism, World world, match, amount = 0 ->

        def radiusBefore = organism.radius

        organism.radius += amount
        if (amount > 0) {
            // Doesn't need to check for collisions if organism is shrinking

            if (world.blocked(organism.centerX, organism.centerY) || organism.collides()) {
                // STM.Rollback
                organism.radius = radiusBefore
            }
        }
    }

    def mutate = {Organism organism, World world, def match = 0.0 ->
        organism.with {
            dna = BehaviourUtils.randomDnaChange(dna, 0.0001)
            color = BehaviourUtils.colorForDna(dna)
        }
    }


    def consumeEnergy = {Organism organism, World world, def match = 0.0 ->
        def amount = Math.pow(organism.radius, 1 / 2) * 0.8
        storeEnergy?.call(-amount)
    }

    static Behaviours newEmptyInstance(Map keepClosures = null) {
        def b = new Behaviours()
        // Set all to null
        b.properties.each {k, v ->
            if (v instanceof Closure) {
                if (!keepClosures?.get(k)) {
                    b."$k" = null
                }
            }
        }
        b
    }

    def removeAllUncurriedClosures() {
        properties.each {k, v ->
            if (v instanceof Closure && v.class != CurriedClosure) {
                this."$k" = null
            }
        }
        this
    }
}
