package myevolution.behaviours

import myevolution.Organism
import org.gmock.WithGMock
import myevolution.Behaviours
import myevolution.World
import static org.hamcrest.CoreMatchers.anything

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-04-15
 * Time: 13:13
 * 
 */
@WithGMock
class PhotosyntesisTests extends GroovyTestCase{
    void test_energylevel() {
        def organism = mock(Organism){
            radius.returns(1)
        }
        def world = mock(World)
        Behaviours behaviours = new Behaviours()
        def storeMock = mock(Closure)
        behaviours.storeEnergy = storeMock

        storeMock.call(anything()) // match:0 results in 0
        // def photosyntesis = {Organism organism, World world, match ->
        play{
            behaviours.photosyntesis( organism, world, 0.0 )
        }
    }
}
