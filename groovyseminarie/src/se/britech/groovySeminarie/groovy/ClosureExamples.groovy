package se.britech.groovySeminarie.groovy
List list = new ArrayList()
list.add("hej")
list.add("hopp")

// Loopa igenom listan
list.each{
	println it
}

println list.findAll{ it.startsWith("h") }