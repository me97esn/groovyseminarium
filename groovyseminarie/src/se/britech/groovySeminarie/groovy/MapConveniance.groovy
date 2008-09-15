package se.britech.groovySeminarie.groovy
LinkedHashMap map = ["one":1, "two":2]

println map.one

map.three = 3
map["four"] = 4

println map.containsKey("one")

println map.containsValue(2)

map.each{key, value -> 
	println key + "=" + value	
}