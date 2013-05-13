package se.britech.groovySeminarie.groovy
// Metoder med closures
ArrayList list = ["Britech", "Vinity", "Clarity"]

print "list.each: "
list.each{
	print it
}

print '\nlist.findAll():'
println list.findAll{it.startsWith("B")}

print 'list.collect():'
println list.collect{it + " är ett Claremontföretag"}

