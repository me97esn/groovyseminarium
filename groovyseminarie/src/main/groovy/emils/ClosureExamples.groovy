package emils
List list = new ArrayList()
list.add("hej")
list.add("hopp")

// Loopa igenom listan
list.each{
	println it
}

println list.findAll{ it.startsWith("he") && it.size() >= 3 }