package myevolution

import org.newdawn.slick.Color
import org.newdawn.slick.geom.Circle
import org.codehaus.groovy.runtime.CurriedClosure
import org.newdawn.slick.geom.Rectangle

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2011-01-22
 * Time: 21.19
 * To change this template use File | Settings | File Templates.
 */
class Organism extends Circle {
    boolean alive = true
    World world
    def allBehaviours = []
    float direction
    def id
    def color = new Color(0x66, 0x33, 0x00)
    def dna

    def getEnergyLevel() {
        Math.pow radius, 3
    }

    Organism(def id, float centerPointX, float centerPointY, float radius, def dna = null) {
        super(centerPointX, centerPointY, radius)
        this.dna = dna

        this.id = id
    }

    Organism(float centerPointX, float centerPointY, float radius) {
        super(centerPointX, centerPointY, radius)
    }

    def behave() {
        def behaviours = chooseBehaviours()

        behaviours.properties.each {name, closure ->
            // If it is a curried closure it is a matching behavoiur. Otherwise it shouldn't be called.
            if (closure && closure instanceof Closure) {

                // Eat shouldn't currently be called directly, but rather from randomMove
                if (name != 'eat')
                    closure()
            }
        }
    }

    /**
     * ChooseBehaviour as a closure so we can change it at runtime
     * Loops through all behaviours, and matches them towards the current situation
     * @Returns all behaviours with a match > 0.0
     */
    def chooseBehaviours = {
        assert world
        def behaviours = new Behaviours()
        def situation = world.representSituation(this)
        allBehaviours.each {Behaviour behaviour ->
            def mergedRepresentation = BehaviourUtils.merge(situation, behaviour.condition)
            def match = match(mergedRepresentation, behaviour.startIndex)
            Closure closure
            if (match >= behaviour.threshold) {
                closure = behaviours.properties.get(behaviour.action).curry(this).curry(world).curry(match)
                behaviours."${behaviour.action}" = closure
            }
        }

        behaviours.removeAllUncurriedClosures()

    }

    def calcDirection(Organism organism) {
        def xDistance = organism.centerX - this.centerX
        def yDistance = this.centerY - organism.centerY

        Math.atan2 yDistance, xDistance
    }

    def calcDistance(other) {
        def dx = this.centerX - other.centerX
        def dy = this.centerY - other.centerY
        def distanceCenterToCenter = Math.hypot(dx, dy)
        if (distanceCenterToCenter < this.radius + other.radius) {
            return 0
        } else {
            return distanceCenterToCenter - this.radius - other.radius
        }
    }

    def match(def represention, startindex = 0) {
        assert dna
        BehaviourUtils.match(dna, represention, startindex)
    }

    def boundingBox(localx = centerX, localy = centerY) {
        // TODO should only create new if points are dirty, otherwise resuse the old one
        new BoundingBox(localx - radius as float, localy - radius as float, radius * 2 + 1 as float, radius * 2 + 1 as float)
    }

    def coveredCoords(localx = centerX, localy = centerY) {
        boundingBox(localx, localy).coveredCoords()
    }

    def collides(x = centerX, y = centerY) {
        def result = []
        coveredCoords(x, y).each {coordX, coordY ->
            def collidingOrg
            collidingOrg = world.organismAt(coordX, coordY)
            if (collidingOrg && !result.contains(collidingOrg) && collidingOrg != this)
                result << collidingOrg
        }
        result
    }
}
