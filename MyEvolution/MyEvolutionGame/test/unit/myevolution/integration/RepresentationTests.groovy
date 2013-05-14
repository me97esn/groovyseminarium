package myevolution.integration

import org.gmock.WithGMock
import myevolution.Organism
import myevolution.Behaviour
import myevolution.Behaviours
import myevolution.World

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-04-04
 * Time: 15:12
 *
 */
@WithGMock
class RepresentationTests extends GroovyTestCase {
    void test_representations_on_positions() {

        def organism = new Organism(1, 1, 1, 1, "000000011111111")
        def world = mock(World)
        world.representSituation(organism).returns "000000"

        organism.world = world
        // organism.chooseBehaviours = {organism.allBehaviours}
        organism.allBehaviours = [
                new Behaviour(startIndex: 0, threshold: 0, condition: "11", action: 'randomMove'),
                new Behaviour(startIndex: 1, threshold: 0, condition: "11", action: 'eat')
        ]

        play {
            def behaviours = organism.chooseBehaviours()

            // Now random move should have been curried with match:1,
            // and eat should be curried with match:0.5
            assertEquals( 7/8, behaviours.randomMove.@curriedParams.first() )
            assertEquals( 1.0, behaviours.eat.@curriedParams.first() )
        }
    }
}
