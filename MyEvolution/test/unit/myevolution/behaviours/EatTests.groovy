package myevolution.behaviours

import org.gmock.WithGMock
import static org.hamcrest.CoreMatchers.anything
import myevolution.Behaviours
import myevolution.World
import myevolution.Organism

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-03-14
 * Time: 12:54
 *
 */
@WithGMock
class EatTests extends GroovyTestCase {

    def org = new Organism(1, 1, 1, 1)
    def world

    void setUp() {
        world = mock(World)
    }

    void test_nothing() {

    }
}
