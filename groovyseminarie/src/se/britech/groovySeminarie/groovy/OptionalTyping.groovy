package se.britech.groovySeminarie.groovy
String aString = "Jag �r en str�ng" // Statisk typning
def aDynamicTypedVariable = "Jag �r ox� en str�ng" // Dynamisk typning
aDynamicTypedVariable = 1 
aDynamicTypedVariable = new HashMap()

aDynamicTypedVariable.doSomeThing() // Kompilerar, men kommer sm�lla n�r det k�rs

void testSomething(){ // jUnit 
	
}

// Duck typing
def aMethod(){ 
	return "Hello"
}

