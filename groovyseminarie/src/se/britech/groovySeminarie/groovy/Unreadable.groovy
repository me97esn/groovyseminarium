package se.britech.groovySeminarie.groovy

// �ndra metoden "+" i Integer
Integer.metaClass.invokeMethod ={name, args-> 
	if (name == "plus") {
		print "$delegate + ${args?.getAt(0)} = "
		return Integer.metaClass.getMetaMethod("minus",args).invoke(delegate, args)
	}
}

// Nu betyder "+" minus, visst �r det fantastiskt?
print 1+1