package se.britech.groovySeminarie.groovy
import com.thoughtworks.xstream.annotations.*
import com.thoughtworks.xstream.*
enum Meal {
	BREAKFAST, LUNCH, DINNER
}

@XStreamAlias("person")
class AnnotatedClass {
    @XStreamAsAttribute
    @XStreamAlias('first-name')
    String firstname

    @XStreamAlias('surname')
    String lastname

    @XStreamOmitField
    String position
}

class GroovyWithJava5Features{
	
	def print_generics(){
		def genericList = new ArrayList<String>()
		genericList << "Hej"
		genericList << 1
		
		println genericList
		genericList

	}
	
	def print_foreach(){
		for (String string : print_generics()) {
			print "${string},"
		}
		println ""
	}
	
	def print_enumeration(){
		println Meal.BREAKFAST
	}
	
	def print_autoboxing(){
		Integer i = 1
		println i
		int i2 = new Integer(1)
		println i2
	}
	
	def foo1(int a, int... b){
		println "inparametrar: $a och $b"
	}
	
	def foo2(int a, int[] b){
		println "inparametrar: $a och $b"
	}
	
	def print_varargs(){
		foo1(1,2,3,4,5)
		foo2(1,2,3,4,5)
	}
	
	def print_annotations(){
		def xstream = new XStream()
		def msg = new AnnotatedClass(firstname:'Sarah',
                lastname:'Connor',
                position:'Protector')
		Annotations.configureAliases(xstream, AnnotatedClass)
		println xstream.toXML(msg)
	}
}
class GroovyWithJava5{
	static void main(args){
	//	 Kör alla metoderna
		print GroovyWithJava5Features.class.methods.each{
			if(!it.parameterTypes && it.name =~ /print_/){
				println "******** ${it.name} ************"
				GroovyWithJava5Features.metaClass.invokeMethod(new GroovyWithJava5Features(), it.name)
				println ""
			}
		}
	}
}