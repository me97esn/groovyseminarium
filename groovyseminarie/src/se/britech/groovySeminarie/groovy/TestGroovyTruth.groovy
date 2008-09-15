package se.britech.groovySeminarie.groovy
import junit.framework.TestCase
public class TestGroovyTruth extends TestCase{
	void testTruth(){
		assert true
		assert !false
		assert 1
		assert -1
		assert !0		
		assert !null		
		assert "Britech"
		assert !""		
		assert [1,2,3]
		assert ![]		
		assert ["ett":1, "två":2]
		assert ![:]		
		StringBuffer sb = new StringBuffer()
		assert !sb
		sb.append("Emil")
		assert sb
	}
}
