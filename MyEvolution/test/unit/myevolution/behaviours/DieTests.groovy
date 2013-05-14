package myevolution.behaviours

import myevolution.Organism
import myevolution.World
import org.gmock.WithGMock
import myevolution.Behaviours
import static org.hamcrest.CoreMatchers.anything
import myevolution.Behaviour

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-03-14
 * Time: 13:36
 *
 */
@WithGMock
class DieTests extends GroovyTestCase {

    Organism organism
    def world

    void setUp() {
        organism = new Organism(1, 1, 1, 1)
        world = mock(World)

        world.representSituation(organism).returns("1111").atLeastOnce()

        organism.world = world
        organism.dna = "11111"
        organism.allBehaviours = [
                new Behaviour(condition: "1", action: 'die')]
    }

    void test_should_die() {
        organism.radius = 0
        play {
            organism.behave()
        }
        assertFalse(organism.alive)
    }

    void test_should_not_die() {
        organism.radius = 2
        def allBehaviours = organism.allBehaviours
        play {
            organism.behave()
        }
        assertTrue(organism.alive)
    }
}
