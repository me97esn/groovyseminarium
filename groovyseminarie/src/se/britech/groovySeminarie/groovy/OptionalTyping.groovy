package se.britech.groovySeminarie.groovy
String aString = "Jag är en sträng" // Statisk typning
def aDynamicTypedVariable = "Jag är oxå en sträng" // Dynamisk typning
aDynamicTypedVariable = 1 
aDynamicTypedVariable = new HashMap()

aDynamicTypedVariable.doSomeThing() // Kompilerar, men kommer smälla när det körs

void testSomething(){ // jUnit 
	
}

// Duck typing
def aMethod(){ 
	return "Hello"
}

