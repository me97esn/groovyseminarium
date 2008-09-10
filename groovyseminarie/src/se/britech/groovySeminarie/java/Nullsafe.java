package se.britech.groovySeminarie.java;

public class Nullsafe {
	private void aMethod(String aParameter) {
		if (aParameter != null && 
				aParameter.length() > 0) {
			// ...
		}else{
			System.out.println(
					"Felaktig input");
		}
	}
}
