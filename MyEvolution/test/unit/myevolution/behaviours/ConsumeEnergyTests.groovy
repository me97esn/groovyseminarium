package myevolution.behaviours

import myevolution.Behaviours
import myevolution.World
import myevolution.Organism
import org.gmock.WithGMock
import static org.hamcrest.CoreMatchers.anything

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-04-15
 * Time: 10:06
 *
 */
@WithGMock
class ConsumeEnergyTests extends GroovyTestCase {
    void test_consume() {
        def match = 0.0
        World world = mock(World)
        Organism org = mock(Organism)
        def behaviours = new Behaviours()

        def consumeEnergy = behaviours.consumeEnergy

        def storeMock = mock(Closure)
        behaviours.storeEnergy = storeMock

        org.radius.returns(2)

        storeMock.call(anything())

        play {
            consumeEnergy(org, world, match)
        }
    }
}
