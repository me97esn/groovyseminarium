package myevolution.integration

import org.gmock.WithGMock
import myevolution.*
import static org.hamcrest.CoreMatchers.anything

class CollisionIntegrationTests extends GroovyTestCase {
    void test_nothing() {

    }

    void test_if_no_organism_moves_and_all_grows_they_should_collide_eventually() {
        World world = new WorldBuilder(
                behaviours: [new Behaviour(condition: "11111", action: 'photosyntesis', threshold: 0.0),
                        new Behaviour(condition: "11111", action: 'storeEnergy', threshold: 0.0)]).with {
            __________
            ___12_____
            __________
        }.build()

        // They currently grow asymetrically, so the only organism that will collide is "1"
        def org1 = world.organismWithId("1")
        def radiusBefore = org1.radius
        world.update()
        assertEquals radiusBefore, org1.radius
    }

    void test_if_space_between_organisms_they_should_grow() {
        World world = new WorldBuilder(
                behaviours: [new Behaviour(condition: "11111", action: 'photosyntesis', threshold: 0.0),
                        new Behaviour(condition: "11111", action: 'storeEnergy', threshold: 0.0)],
                radius: 1).with {
            __________
            ___1_2_____
            __________
        }.build()

        def radiusesBefore = world.organisms*.radius
        world.update()
        assertTrue "$radiusesBefore and ${world.organisms*.radius} should differ, but didn't", radiusesBefore != world.organisms*.radius
    }

}

