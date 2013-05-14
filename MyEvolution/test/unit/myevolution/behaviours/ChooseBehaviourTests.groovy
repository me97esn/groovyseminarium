package myevolution.behaviours

import myevolution.Organism
import myevolution.Behaviours
import org.gmock.WithGMock
import myevolution.BehaviourUtils
import static org.hamcrest.CoreMatchers.anything
import myevolution.World
import myevolution.Behaviour

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
class ChooseBehaviourTests extends GroovyTestCase {
    def food
    def org = new Organism(1, 1, 1, 1)
    World world

    def conditionRepr

    def randomMoveMock
    def startIndex = 10

    void setUp() {
        conditionRepr = mock()
        randomMoveMock = mock()

        world = mock(World)
        food = mock()
        //world.food.returns food
        org.world = world
        org.allBehaviours = [
                new Behaviour(
                        startIndex: startIndex,
                        condition: conditionRepr,
                        action: "randomMove")]
    }

    def verify_choose_behaviour(def match, closure) {
        def mockBehaviourUtils = mock(BehaviourUtils)

        def situationRepresentation = mock()
        world.representSituation(org).returns(situationRepresentation)

        def mergedReprMock = mock()
        mockBehaviourUtils.static.merge(situationRepresentation, conditionRepr).returns(mergedReprMock)

        def orgMock = mock(org)
        orgMock.match(mergedReprMock, startIndex).returns match

        if (match)
            randomMoveMock.curry(match).returns(randomMoveMock).atMostOnce()

        play {
            //def choosenBehaviours = orgMock.chooseBehaviours()
            // Ska varken innehålla randomMove eller chooseBehaviour
            closure(orgMock.chooseBehaviours())
        }
    }

    void test_no_match_should_never_invoke_randomMove() {
        verify_choose_behaviour(0.0) { choosenBehaviours ->
       }
    }

    void test_partial_match_too_low_should_never_invoke_randomMove() {
        verify_choose_behaviour(0.4) { choosenBehaviours ->
        }
    }

    /**
     * match()-metoden ger en float som anger hur ofta randomMove-beteendet ska invokeras.
     */
    void test_perfekt_match_should_always_invoke_randomMove() {
        verify_choose_behaviour(1.0) {}
    }

    void test_partial_match_should_curry_randomMove_with_partial_match() {
        verify_choose_behaviour(0.6) {}
    }
}