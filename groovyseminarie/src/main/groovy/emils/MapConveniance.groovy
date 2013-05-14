package emils
LinkedHashMap map0 = new LinkedHashMap()
map0.put("one", 1)
map0.put("two", 2)
// Samma sak som:
LinkedHashMap map = ["one":1, "two":2]

println map.one

map.three = 3
// Samma sak som:
map["three"] = 3
// Samma sak som :
map.put("three", 3)

map.each{key, value -> 
	println key + "=" + value	
}

println map + ["four":4,"five":5]
