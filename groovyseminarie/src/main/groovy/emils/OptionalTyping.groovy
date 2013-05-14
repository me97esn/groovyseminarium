package emils
String aString = "Jag �r en str�ng" // Statisk typning
def duckTyped = "Jag �r ox� en str�ng" // Dynamisk typning
duckTyped = 1 
duckTyped = new HashMap()

duckTyped.doSomeThing() // Kompilerar, men kommer sm�lla n�r det k�rs

void testSomething(){ // jUnit 
	
}

// Duck typing
def aMethod(){ 
	return "Hello"
}

