import junit.framework.TestCase
public class TestRegExp extends TestCase{
    void testRegExp(){
		// Partial matches
		assert "siffran ett: 1" =~ /\d/
		assert !("ingen siffra" =~ /\d/)
		assert "siffran ett: 1" =~ "\\d"
		
		// Exact matches
		assert !("siffran ett: 1" ==~ /\d/)
		assert "siffran ett: 1" ==~ /[\w\s:]+/
	}
}
