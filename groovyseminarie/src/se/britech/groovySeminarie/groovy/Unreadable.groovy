package se.britech.groovySeminarie.groovy

// �verlagra metoden "+" i Integer
Integer.metaClass.invokeMethod ={name, args-> 
	if (name == "plus") {
		print "$delegate + ${args?.getAt(0)} = "
		return Integer.metaClass.getMetaMethod("minus",args).invoke(delegate, args)
	}
}

// Nu betyder det h�r 1-1, koden �r inte l�ngre l�slig
print 1+1