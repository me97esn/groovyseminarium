package myevolution.integration

import org.gmock.WithGMock
import myevolution.*
import static org.hamcrest.CoreMatchers.anything
import org.junit.Test
import org.junit.Assert

class EventuallyDieTests {
    @Test
    void test_if_die_threshold_is_0_and_no_photosyntesiz_exists_all_organisms_should_die() {
        def builder = new WorldBuilder(
                behaviours: [
                        new Behaviour(
                                condition: "11111",
                                threshold: 0.0,
                                action: 'die'),
                        new Behaviour(
                                condition: "1",
                                threshold: 0.0,
                                action: 'consumeEnergy')
                ],
                radius: 3)
        World world = builder.with {
            ______________
            __1__2__3_____
            ______________
            __4__5__6_____
            ______________
            __7__8__9__a__
            ______________
        }.build()

        def org1 = world.organismWithId("1")

        // TODO uses random values which causes this test to fail sometimes
        (org1.energyLevel+1).times {i ->
            world.update()
        }

        Assert.assertFalse("Organism $org1.id should be dead, but wasnt't. It has energyLevel: ${org1.energyLevel}", org1.alive)

    }
}
