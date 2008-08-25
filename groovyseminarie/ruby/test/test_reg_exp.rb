require "test/unit"
class TestRegExp < Test::Unit::TestCase
  def setup
    @regexp = /\[.*\]/
  end
  
  def test_find
    assert @regexp =~ "[helloWorld.groovy]"
  end
  
  def test_no_find
    assert !(@regexp =~ "\223\205So I've been musing a little while if its time the Java platform had its own dynamic language designed from the ground up to work real nice with existing Java code; creating/extending objects normal Java can use and vice versa. Python/Jython's a pretty good base - add the nice stuff from Ruby and maybe sprinkle on some AOP features and we could have a really Groovy new language for scripting Java objects, writing test cases and who knows, even doing real development in it.\224\r")
  end
end