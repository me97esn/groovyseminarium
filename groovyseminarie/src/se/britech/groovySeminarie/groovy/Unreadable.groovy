package se.britech.groovySeminarie.groovy

// Anv�nd metaClass f�r att �verlagra en metod 
Integer.metaClass.invokeMethod ={name, args-> 
	if (name == "plus") {
		print "$delegate + ${args?.getAt(0)} = "
		return Integer.metaClass.getMetaMethod("minus",args).invoke(delegate, args)
	}
}

// Nu betyder det h�r 1-1, mao �r koden inte l�ngre l�slig
print 1+1