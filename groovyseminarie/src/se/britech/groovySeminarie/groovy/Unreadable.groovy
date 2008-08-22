package se.britech.groovySeminarie.groovy

// Använd metaClass för att överlagra en metod 
Integer.metaClass.invokeMethod ={name, args-> 
	if (name == "plus") {
		print "$delegate + ${args?.getAt(0)} = "
		return Integer.metaClass.getMetaMethod("minus",args).invoke(delegate, args)
	}
}

// Nu betyder det här 1-1, mao är koden inte längre läslig
print 1+1