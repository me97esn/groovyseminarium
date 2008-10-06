package se.britech.groovySeminarie.groovy

ArrayList list0 = new ArrayList()
list0.add("Britech")
list0.add("Vinity")

// Samma sak som:
ArrayList list = ["Britech", "Vinity"]

println list.class
println list[0]

list << "Mälardalen" 

println list.reverse()

println list + ["Claremont", "Clarity"]

list.each{ println it }
list.eachWithIndex{item, i-> 
	println item + ":" +  i 
}
println list.collect{ it.toUpperCase()}

println list.findAll{it =~ /Bri.*/}



