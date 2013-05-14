package myevolution

import org.gmock.WithGMock
import org.codehaus.groovy.runtime.CurriedClosure

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-04-29
 * Time: 11:06
 *
 */
@WithGMock
class ChooseBehavioursAsPropertiesTests extends GroovyTestCase {
    void test_invoking_null_closure() {
        def behaviours = new Behaviours()

        behaviours.consumeEnergy = null
        play {
            behaviours.properties.each {key, value ->
                if (key == 'consumeEnergy')
                    if (value)
                        value()
            }
            // No exception should be thrown
        }
    }

    void test_replace_closure() {
        def behaviours = new Behaviours()

        behaviours.consumeEnergy = {1}
        assertEquals(1, behaviours.consumeEnergy())
        def name = "consumeEnergy"
        behaviours."$name" = {2}
        assertEquals(2, behaviours.consumeEnergy())
    }
}
