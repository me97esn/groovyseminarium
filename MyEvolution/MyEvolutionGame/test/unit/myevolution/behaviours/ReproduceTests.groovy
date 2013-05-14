package myevolution.behaviours

import org.codehaus.groovy.runtime.CurriedClosure
import org.gmock.WithGMock
import myevolution.Organism
import myevolution.Behaviours
import myevolution.World
import static org.hamcrest.CoreMatchers.anything
import myevolution.Behaviour
import static org.hamcrest.CoreMatchers.any
import myevolution.BehaviourUtils

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-04-01
 * Time: 10:00
 *
 */
@WithGMock
class ReproduceTests extends GroovyTestCase {

    /**
     * To be able to reproduce an organism, a closure has to be curried with the new organism.
     * Here is a test that uncurrying is possible
     */
    void test_uncurry() {
        def closure = {int param -> param * 2}
        def curriedClosure = closure.curry(10)
        assertEquals(20, curriedClosure())

        def unCurriedClosure = curriedClosure.owner

        assertEquals(200, unCurriedClosure(100))
    }

    void test_reproduce() {
        def behaviours = new Behaviours()
        behaviours.consumeEnergy = {Organism organism, World world ->}
        def dnaStr = "10"

        def organism = mock(Organism) {
            radius.returns(10).atLeastOnce()
            energyLevel.returns(20).atLeastOnce()
            dna.returns(dnaStr).atLeastOnce()
            collides(0, 0).returns(null).atLeastOnce()
        }

        World world = mock(World)

        def match = 0.6

        def mockBehaviourUtils = mock(BehaviourUtils)
        def mockMath = mock(Math)
        mockMath.static.random().returns(0.0)
        mockMath.static.random().returns(0.2)
        mockMath.static.random().returns(0.4)
        mockMath.static.random().returns(0.6)
        mockMath.static.random().returns(0.8)

        def newOrganisms = mock(List)
        // Only call these methods if random is low enough, therefor this number differs
        4.times {
            organism.radius.set(anything())

            Organism mockOrg = mock(Organism, constructor(anything(), anything(), anything(), anything(), anything()))
            mockOrg.world.set(anything())
            world.newOrganisms.returns(newOrganisms)
            mockOrg.radius.set(anything())

            newOrganisms << mockOrg

            mockBehaviourUtils.static.randomNewCoords(anything(), anything()).returns( [x:0, y:0] )
            mockBehaviourUtils.static.randomDnaChange(dnaStr, any(Float)).returns( dnaStr )

            mockOrg.dna.returns "10"
            mockBehaviourUtils.static.colorForDna("10").returns "color"
            mockOrg.color.set("color")

            def mockClosure = mock(Closure)
            def mockBehaviour = mock(Behaviour) {
                action.returns(mockClosure)
                threshold.returns 0
                condition.returns "10"
                startIndex.returns 0
            }

            world.allBehaviours.returns([mockBehaviour])
            world.blocked(0, 0).returns false
            def allBehaviours = mock(List)
            mockOrg.allBehaviours.returns(allBehaviours)
            allBehaviours << any(Behaviour)
        }
        play {
            5.times {
                behaviours.reproduce(organism, world, match)
            }
        }
    }
}
