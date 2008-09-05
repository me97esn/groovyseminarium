package se.britech.groovySeminarie.groovy
String aString = "Jag är en sträng" // Statisk typning

def aDynamicTypedVariable = "Jag är oxå en sträng" // Dynamisk typning
aDynamicTypedVariable = 1 
aDynamicTypedVariable = new HashMap()

void testSomething(){ // Statisk typning så att jUnit 3 hittar metoden
	
}

// Duck typing så metoden kan returnera vad som helst, 
// eller vara void
def aMethod(){ 
	return "Hello"
}

