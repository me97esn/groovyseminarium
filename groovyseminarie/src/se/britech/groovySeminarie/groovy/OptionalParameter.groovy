package se.britech.groovySeminarie.groovy
public class OptionalParameters{
	def doSomething(String s="Default string", obj=new Object()){
		println s + obj
	}
}

def object = new OptionalParameters()
object.doSomething()
object.doSomething("En parameter")
object.doSomething("F�rsta parametern", "andra parametern")