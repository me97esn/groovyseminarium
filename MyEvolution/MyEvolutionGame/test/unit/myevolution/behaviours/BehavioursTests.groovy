package myevolution.behaviours

import myevolution.Behaviours
import myevolution.Organism
import myevolution.World
import org.gmock.WithGMock
import static org.hamcrest.CoreMatchers.anything
import myevolution.BehaviourUtils

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
class BehavioursTests extends GroovyTestCase {
    void test_newEmpty() {
        assertNull(Behaviours.newEmptyInstance().eat)
        assertNull(Behaviours.newEmptyInstance().randomMove)
        assertNull(Behaviours.newEmptyInstance().photosyntesis)
        assertNull(Behaviours.newEmptyInstance().consumeEnergy)

        assertNotNull(Behaviours.newEmptyInstance(storeEnergy: true).storeEnergy)
        def key = "storeEnergy"
        assertNotNull(Behaviours.newEmptyInstance((key): true).storeEnergy)
    }

    void test_removeAllUncurriedClosures() {
        def behaviours = new Behaviours()
        behaviours.consumeEnergy = behaviours.consumeEnergy.curry(null)
        behaviours.removeAllUncurriedClosures()

        assertNull behaviours.eat
        assertNull behaviours.randomMove
        assertNull behaviours.die
        assertNull behaviours.reproduce

        assertNotNull behaviours.consumeEnergy
    }

    void test_parameters_to_closure() {
        [new Behaviours().randomMove].each {closure ->
            // Should not fail
            try {
                def world = new World()
                def organism = new Organism(1, 1, 1, 1)
                organism.world = world
                closure(organism, world, 0.5)
            } catch (Exception e) {
                e.printStackTrace()
                fail("Could not invoke a closure, an exception occurred: ${e.getMessage()} ")
            }
        }
    }
}
