package se.britech.groovySeminarie.groovy

enum Meal {
	BREAKFAST, LUNCH, DINNER
}

class GroovyWithJava5Features{
	
	def print_generics(){
		def genericList = new ArrayList<String>()
		genericList << "Hej"
		genericList << 1
		
		println genericList
		
		print "foreach:"
		for (String string : genericList) {
			print "${genericList},"
		}
		println ""
	}
	
	def print_enumeration(){
		println Meal.BREAKFAST
	}
	
	def print_autoboxing(){
		Integer i = 1
		println i
	}
}
print GroovyWithJava5Features.class.methods.each{
	if(!it.parameterTypes && it.name =~ /print_/){
		println "******** ${it.name} ************"
		GroovyWithJava5Features.metaClass.invokeMethod(new GroovyWithJava5Features(), it.name)
		println ""
	}
}