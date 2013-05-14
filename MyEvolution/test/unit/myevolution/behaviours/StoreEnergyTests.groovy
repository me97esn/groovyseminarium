package myevolution.behaviours

import org.gmock.WithGMock
import myevolution.Organism
import myevolution.World
import myevolution.Behaviours

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-04-14
 * Time: 16:29
 *
 */
@WithGMock
class StoreEnergyTests extends GroovyTestCase {
    void test_storeEnergy_when_nothing_collides() {
        Organism organism = mock(Organism)
        def world = mock(World)
        def amountBefore = 1

        organism.radius.returns(amountBefore).atLeastOnce()
        organism.radius.set(match {it > amountBefore})
        organism.centerX.returns(0.0).atLeastOnce()
        organism.centerY.returns(0.0).atLeastOnce()

        world.blocked(0.0, 0.0).returns(false)

        organism.collides().returns false

        play {
            new Behaviours().storeEnergy(organism, world, 0.0, 5) // Hanterar inte matchning i beteendet nu
        }
    }

    void test_storeEnergy_when_organism_collides() {
        Organism organism = mock(Organism)
        def x = 1.0
        def y = 1.0
        def world = mock(World) {
            blocked(x, y)
        }
        def amountBefore = 1

        organism.radius.returns(amountBefore).atLeastOnce()
        organism.centerX.returns(x)
        organism.centerY.returns(y)
        organism.radius.set(match {it > amountBefore})
        organism.radius.set(amountBefore)

        organism.collides().returns true

        play {
            new Behaviours().storeEnergy(organism, world, 0.0, 5) // Hanterar inte matchning i beteendet nu
        }
    }
}
