package myevolution.behaviours

import myevolution.BehaviourUtils
import myevolution.Behaviours
import myevolution.Organism
import myevolution.World
import org.gmock.WithGMock
import static org.hamcrest.CoreMatchers.anything

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-03-14
 * Time: 12:54
 *
 */
@WithGMock
class RandomMoveTests extends GroovyTestCase {
    def world
    def behaviourUtils
    def organism
    def behaviours = new Behaviours()
    def newCoords

    @Override protected void setUp() {
        world = mock(World)
        behaviourUtils = mock(BehaviourUtils)
        organism = mock(Organism)
        newCoords = [x: 0, y: 0]

        behaviours.consumeEnergy = {Organism organism, World world ->}.curry(organism).curry(world)

    }

    void test_randomMove_with_perfect_dna_match() {
        behaviourUtils.static.randomNewCoords(organism, anything()).returns(newCoords).atLeastOnce()

        world.blocked(newCoords.x, newCoords.y).returns(false).atLeastOnce()

        organism.collides(newCoords.x, newCoords.y).returns([]).atLeastOnce()

        // Med perfekt matchning ska organismen alltid röra sig
        organism.setCenterX(anything()).times(10)
        organism.setCenterY(anything()).times(10)

        def mockMath = mock(Math)
        def startRandom = 0.0
        10.times {
            mockMath.static.random().returns(startRandom += 0.1)
        }

        play {
            10.times {
                behaviours.randomMove(organism, world, 1.0)
            }
        }
    }

    void test_randomMove_with_partial_dna_match() {
        behaviourUtils.static.randomNewCoords(organism, anything()).returns(newCoords).atLeastOnce()

        world.blocked(newCoords.x, newCoords.y).returns(false).atLeastOnce()

        organism.collides(newCoords.x, newCoords.y).returns([]).atLeastOnce()

        // Med perfekt matchning ska organismen alltid röra sig
        organism.setCenterX(anything()).times(5)
        organism.setCenterY(anything()).times(5)

        def mockMath = mock(Math)
        def startRandom = 0.0
        10.times {
            mockMath.static.random().returns(startRandom += 0.1)
        }

        play {
            10.times {
                behaviours.randomMove(organism, world, 0.51)
            }
        }
    }

    void test_randomMove_not_blocked_no_collisions() {
        behaviourUtils.static.randomNewCoords(organism, anything()).returns(newCoords)

        world.blocked(newCoords.x, newCoords.y).returns false

        organism.collides(newCoords.x, newCoords.y).returns([])

        // Verifiera att x och y sätts på organism-mocken
        organism.setCenterX(anything())
        organism.setCenterY(anything())
        play {
            behaviours.randomMove(organism, world, 1.0)
        }
    }

    void test_randomMove_blocked_no_collisions() {
        behaviourUtils.static.randomNewCoords(organism, anything()).returns(newCoords)

        world.blocked(newCoords.x, newCoords.y).returns true

        organism.collides(newCoords.x, newCoords.y).returns([])

        // Verifiera att x och y sätts på organism-mocken
        organism.setCenterX(anything()).never()
        organism.setCenterY(anything()).never()
        play {
            behaviours.randomMove(organism, world, 1.0)
        }
    }


    void test_randomMove_blocked_collisions() {

        behaviourUtils.static.randomNewCoords(organism, anything()).returns(newCoords)

        world.blocked(newCoords.x, newCoords.y).returns true

        organism.collides(newCoords.x, newCoords.y).returns([mock(Organism)])

        // Verifiera att x och y sätts på organism-mocken
        organism.setCenterX(anything()).never()
        organism.setCenterY(anything()).never()
        play {
            behaviours.randomMove(organism, world, 1.0)
        }
    }
}
