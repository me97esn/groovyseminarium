package se.britech.groovySeminarie.groovy
Map map = ["one":1, "two":2]
print "map.getClass():"
println map.getClass()
println map

print "map.one:"
println map.one

map.three = 3
map["four"] = 4
println map

print 'map.containsKey("one"):'
println map.containsKey("one")

print 'map.containsValue(2):'
println map.containsValue(2)

map.each{key, value -> 
	println key + "=" + value	
}