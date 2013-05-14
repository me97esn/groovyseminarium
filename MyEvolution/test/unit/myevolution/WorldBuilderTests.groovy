package myevolution

import org.codehaus.groovy.runtime.CurriedClosure
import org.newdawn.slick.Color

/**
 * Created by IntelliJ IDEA.
 * User: emilsandin
 * Date: 2011-03-01
 * Time: 09.41
 * To change this template use File | Settings | File Templates.
 */
class WorldBuilderTests extends GroovyTestCase {




    void test_build_with_radius() {
        def behaviours = [[action:new Behaviours().randomMove, desc:"randomMove"]]

        World world = new WorldBuilder(behaviours:behaviours, radius: 50).with {
            _________1_____2
            __3__4_____a___6
            ________________
        }.build()
        def org1 = world?.organisms.find { it.id == "1" }
        assertEquals(50.0 , org1.radius)
        assertEquals(9.0 * 50 * 2, org1.x)
        assertEquals(16*50 * 2, world.width)
    }

    void test_build() {
        World world = new WorldBuilder().with {
            _________1_____2
            __3__4_____a___6
            ________________
        }.build()
        assertEquals(6, world.organisms.size())

        def org1 = world?.organisms.find { it.id == "1" }
        assertEquals(1.0, org1.radius)
        assertEquals(9.0 * 2, org1.x)
        assertEquals(0.0, org1.y)

        assertEquals(16 *2, world.width)
        assertEquals(3 * 2, world.height)

    }

    void test_colorForDna_should_be_white(){
        assertEquals( new Color(0xff, 0xff, 0xff), BehaviourUtils.colorForDna("111111111111111"))
    }

    void test_colorForDna_should_be_black(){
        assertEquals( new Color(0x00, 0x00, 0x00), BehaviourUtils.colorForDna("000000000000000"))
    }

    void test_build_set_startindex_on_each_behaviour() {
        def behaviours = [
                [action: new Behaviours().randomMove, condition: "01"],
                [action: new Behaviours().eat, condition: "01"],
                [action: new Behaviours().die, condition: "01"]
        ]
        def builder = new WorldBuilder(behaviours: behaviours).with {
            _________1_____2
            ________________
            _____z__z__z____

        }
        World world = builder.build()

        // TODO detta test kommer ju smälla när jag ändrat world.representSituation och bör kanske tas bort då
        assertEquals(0, world.organismWithId("1").allBehaviours.first().startIndex)
        assertEquals(2 + builder.situationLength, world.organismWithId("1").allBehaviours[1].startIndex)
        assertEquals(4 + builder.situationLength*2, world.organismWithId("1").allBehaviours[2].startIndex)
    }



}