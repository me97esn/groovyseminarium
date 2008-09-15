package se.britech.groovySeminarie.groovy
String aString = "Jag är en sträng" // Statisk typning
def duckTyped = "Jag är oxå en sträng" // Dynamisk typning
duckTyped = 1 
duckTyped = new HashMap()

duckTyped.doSomeThing() // Kompilerar, men kommer smälla när det körs

void testSomething(){ // jUnit 
	
}

// Duck typing
def aMethod(){ 
	return "Hello"
}

