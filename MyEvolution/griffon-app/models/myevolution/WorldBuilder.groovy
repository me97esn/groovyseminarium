package myevolution

import org.newdawn.slick.Color

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2011-03-01
 * Time: 09.42
 * To change this template use File | Settings | File Templates.
 */
class WorldBuilder {
    /**
     * o = organism med genererat id
     * z = SimpleFood
     * _ = land
     * alla andra chars är organismer med den angedda charen som id
     */

    /**
     * Börja på ett eftersom man skapar en circle med centerpointx, och sen blir x-värdet centerpoint - radius
     * Börjar vi med 1 kan man lägga organismer ända ut i kanten
     */
    def yIndex = 0
    private def width = 0
    def behaviours = []
    def organisms = []
    def radius = 1
    def world = new World()
    def biggestId = 100000
    def dna
    def startIndex = 0
    def situationLength

    WorldBuilder() {
        situationLength = world.representSituation(new Organism(1, 1, 1, 1, "1")).length()
    }


    def getProperty(String name) {
        def metaProperty = metaClass.getMetaProperty(name)
        if (!metaProperty) {
            if (name.length() > width) {
                width = name.length()
            }
            name.eachWithIndex {orgId, index ->
                if (orgId != '_') {
                    def centerX = index * radius * 2 + radius
                    def centerY = yIndex * radius * 2 + radius
                    if (orgId == "o")
                        orgId = "${biggestId++}"
                    def org = new Organism(orgId, centerX, centerY, radius)
                    startIndex = 0
                    behaviours.each {behaviour ->
                        behaviour.startIndex = startIndex

                        org.allBehaviours << new Behaviour(
                                action: behaviour.action,
                                threshold: behaviour.threshold,
                                condition: behaviour.condition,
                                startIndex: startIndex
                        )
                        if (behaviour.condition)
                            startIndex += (behaviour.condition.length() + situationLength)
                    }
                    org.world = world

                    organisms << org

                }
            }
            yIndex++
            return this
        } else {
            return metaProperty.getProperty(this)
        }
    }

    def build() {
        organisms = organisms.collect { org ->
            org.dna = generateRandomDNA(100)
            org.color = BehaviourUtils.colorForDna(org.dna)
            org
        }

        world.allBehaviours = behaviours
        world.organisms = organisms
        world.width = width * radius * 2
        world.height = yIndex * radius * 2
        world
    }

    def generateRandomDNA = {def length ->
        def result = ""
        length.times {result += "${Math.round(Math.random())}"}
        result
    }
}