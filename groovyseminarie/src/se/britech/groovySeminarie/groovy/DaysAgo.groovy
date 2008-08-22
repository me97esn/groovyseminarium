package se.britech.groovySeminarie.groovy

Integer.metaClass.propertyMissing ={name-> 
	if (name == "days") {
		return delegate * 24 * 60 * 60 * 1000
	}else if(name == "ago"){
		def time = new Date().time
		time -= delegate
		return new Date(time:time)
	}
}

println 4.days.ago 
