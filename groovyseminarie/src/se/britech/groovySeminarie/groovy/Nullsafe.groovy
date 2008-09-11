package se.britech.groovySeminarie.groovy
void aMethod(String aParameter) {
	if (aParameter?.length() > 0) {
		println "Parametern var:" + aParameter
	}else{
		println "Parameter var null eller en tom sträng"
	}
}

aMethod( null )
aMethod( "" )
aMethod "Hej" 