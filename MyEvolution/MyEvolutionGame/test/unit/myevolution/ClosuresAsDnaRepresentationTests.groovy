package myevolution

/**
 * Created by IntelliJ IDEA.
 * User: xemisan 
 *       Emil Sandin
 *       Britech Consulting AB
 *      emil.sandin@britech.se
 * Date: 2011-04-28
 * Time: 15:47
 *
 */
class ClosuresAsDnaRepresentationTests extends GroovyTestCase {
    void test_can_change_variables_from_evaluated_string() {
        Binding binding = new Binding();

        def world = new World()

        binding.setVariable("foo", 2);
        binding.setVariable("world", world);
        GroovyShell shell = new GroovyShell(binding);

        def value = shell.evaluate("println 'Hello World!'; world.width=-1; return foo * 10");
        assertEquals( -1, world.width )
        def world2 = binding.getVariable("world")
        assertSame world, world2
    }
}
