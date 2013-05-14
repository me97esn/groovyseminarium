package emils
void aMethod(String aParameter) {
	if (aParameter ) {
		println "Parametern var:" + aParameter
	}else{
		println "Parameter var null eller en tom str�ng"
	}
}

aMethod( null )
aMethod( "" )
aMethod "Hej" 