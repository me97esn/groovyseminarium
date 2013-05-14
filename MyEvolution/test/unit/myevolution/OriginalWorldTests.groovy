package myevolution

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2013-05-14
 * Time: 10.24
 * To change this template use File | Settings | File Templates.
 */
class OriginalWorldTests  extends GroovyTestCase {
    /**
    * This creates a world with two organisms, located:
    * ---------------
    * |______________|
    * |_____1________|
    * |__________2___|
    * |______________|
    *
    */
    void test_building_a_world(){
        def world = new World(organisms: [
//                new Organism(x, centerX,centerY, radius),
                new Organism(1,10,10, 10),
                new Organism(2,20,20, 10),
        ])
        def org1 = world.organisms.find { it.id == 1 }
        assertEquals(10.0 , org1.radius)
        // ... more asserts
    }
}
