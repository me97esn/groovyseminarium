package se.britech.groovySeminarie.groovy
def doSomething(String s="Default string", obj=new Object()){
	println "${s}, ${obj}"
}
doSomething()
doSomething("En parameter")
doSomething("F�rsta parametern", "andra parametern")