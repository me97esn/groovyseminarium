package se.britech.groovySeminarie.groovy
String aString = "Jag �r en str�ng" // Statisk typning

def aDynamicTypedVariable = "Jag �r ox� en str�ng" // Dynamisk typning
aDynamicTypedVariable = 1 
aDynamicTypedVariable = new HashMap()

void testSomething(){ // Statisk typning s� att jUnit 3 hittar metoden
	
}

// Duck typing s� metoden kan returnera vad som helst, 
// eller vara void
def aMethod(){ 
	return "Hello"
}

