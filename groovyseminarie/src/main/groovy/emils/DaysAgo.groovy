package emils

import org.joda.time.DateTime

Integer.metaClass.getDays = { 
	delegate * 24 * 60 * 60 * 1000
}

Integer.metaClass.getHours = {
	delegate * 60 * 60 * 1000
}

Integer.metaClass.getAgo = {
    def millis = new DateTime().millis
	millis -= delegate
	return new DateTime(millis)
}

DateTime.metaClass.invokeMethod ={name, args->
	if (name == "plus") {
		return DateTime.metaClass.getMetaMethod("plusMillis",args).invoke(delegate, args)
	}
}

/*************************/
println 4.days.ago + 11.hours
println 4.days.ago.class

