package myevolution

import griffon.test.*
import org.gmock.WithGMock
import static org.hamcrest.CoreMatchers.anything

@WithGMock
class BehaviourUtilsTests extends GriffonUnitTestCase {

    void test_randomDnaChange() {
        def mockMath = mock(Math)

        mockMath.static.random().returns(0.0).times(2)
        play {
            def newDna = BehaviourUtils.randomDnaChange("10", 0.3)
            assertEquals("01", newDna)
        }

        mockMath.static.random().returns(0.29).times(2)
        play {
            def newDna = BehaviourUtils.randomDnaChange("10", 0.3)
            assertEquals("01", newDna)
        }

        mockMath.static.random().returns(0.0)
        mockMath.static.random().returns(0.4)
        play {
            def newDna = BehaviourUtils.randomDnaChange("10", 0.3)
            assertEquals("00", newDna)
        }

        mockMath.static.random().returns(0.6)
        mockMath.static.random().returns(0.0)
        play {
            def newDna = BehaviourUtils.randomDnaChange("10", 0.3)
            assertEquals("11", newDna)
        }

        mockMath.static.random().returns(1.0).times(2)
        play {
            def newDna = BehaviourUtils.randomDnaChange("10", 0.3)
            assertEquals("10", newDna)
        }
    }

    /**
     * BehaviourUtils.merge([situation, behaviour.condition])
     *
     * Testar först att bara lägga dem efter varann. Funkar det inte får jag väl bygga om det.
     */
    void test_merge_one_bit() {
        def situationRepr = "1"
        def behaviourConditionRepr = "0"

        assertEquals("10", BehaviourUtils.merge(situationRepr, behaviourConditionRepr))
        // 00 01 10 11
    }

    void test_randomNewCoords() {
        Organism org = mock(Organism)
        def mockMath = mock(Math)
        mockMath.static.random().returns(0)
        mockMath.static.cos(anything()).returns(0)
        mockMath.static.sin(anything()).returns(0)

        mockMath.static.PI.returns(3.14).atLeastOnce()

        org.centerX.returns 0
        org.centerY.returns 0

        // Ska inte röra organismen, bara generera nya koordinater
        org.setCenterX(anything()).never()
        org.setCenterY(anything()).never()
        org.setDirection(anything()).never()

        play {
            def newCoords = BehaviourUtils.randomNewCoords(org, 1)
            assertEquals([x: 0.0 as float, y: 0.0 as float], newCoords)
        }
    }

    void test_merge_two_plus_one_bit() {
        def situationRepr = "11"
        def behaviourConditionRepr = "0"

        assertEquals("110", BehaviourUtils.merge(situationRepr, behaviourConditionRepr))
        // 00 01 10 11
    }
}
