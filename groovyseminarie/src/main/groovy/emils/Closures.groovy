package emils
def pickEven(n, closure){
	for (int index = 2; index <= n; index+=2) {
		closure(index)
	}
}

def sum = 0
pickEven(10){sum += it} 
//  pickEven(10,{sum += it}) // B�gge s�tt funkar

def product = 1
pickEven(10){product *= it}

def listOfSquares = []
pickEven(10){listOfSquares << it**2}

println "sum: $sum"
println "product: $product"
println "listOfSquares: $listOfSquares"
