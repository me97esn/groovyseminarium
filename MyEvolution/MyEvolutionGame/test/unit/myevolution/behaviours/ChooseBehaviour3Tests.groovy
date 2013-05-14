package myevolution.behaviours

import myevolution.Behaviour
import myevolution.BehaviourUtils
import myevolution.Organism
import myevolution.World
import org.gmock.WithGMock

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-03-14
 * Time: 15:46
 *
 */
@WithGMock
class ChooseBehaviour3Tests extends GroovyTestCase {
    def dna = "01111111"
    def org = new Organism(1, 1, 1, 1, dna)
    World world


    void test_different_startIndex_should_cause_different_match() {
        world = mock(World)
        org.world = world
        def conditionRepr = "1" // Same for all behaviours
        def mockBehaviourUtils = mock(BehaviourUtils)
        def situation = "1"
        world.representSituation(org).returns( situation )

        org.allBehaviours = [
                new Behaviour(
                        startIndex: 0,
                        threshold: 0.1,
                        condition: conditionRepr,
                        action: 'randomMove'),
                new Behaviour(
                        startIndex: 1,
                        threshold: 0.1,
                        condition: conditionRepr,
                        action: 'eat')]
        // randomMove
        def mergedRepr = "11"
        mockBehaviourUtils.static.merge(conditionRepr, situation).returns( mergedRepr )
        mockBehaviourUtils.static.match(dna, mergedRepr, 0).returns(0.5)

        // eat
        def mergedRepr2 = "11"
        mockBehaviourUtils.static.merge(conditionRepr, situation).returns( mergedRepr2 )
        mockBehaviourUtils.static.match(dna, mergedRepr2, 1).returns(1.0)

        play {
            org.chooseBehaviours()
            // Do nothing, just verify that the closures has been curried with different args
        }
    }
}