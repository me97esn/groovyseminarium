package se.britech.groovySeminarie.groovy
class Nullsafe {
	void aMethod(String aParameter) {
		if (aParameter?.length() > 0) {
			// ...
		}
	}
}