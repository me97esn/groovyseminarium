package se.britech.groovySeminarie.groovy

Integer.metaClass.getDays = { 
	delegate * 24 * 60 * 60 * 1000
}

Integer.metaClass.getAgo = {
    def time = new Date().time
	time -= delegate
	return new Date(time:time)
} 

print 4.days.ago 

