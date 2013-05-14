package emils


Integer.metaClass.invokeMethod ={name, args-> 
	if (name == "plus") {
		return Integer.metaClass.getMetaMethod("minus",args).invoke(delegate, args)
	}
}


/**********************************/
println "The result of 1+1 = is: ${1+1}"
println "The result of 1+10 = is: ${1+10}"
println "The result of 10+1 = is: ${10+1}"
