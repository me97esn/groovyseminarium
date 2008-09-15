package se.britech.groovySeminarie.java;

public class Nullsafe {
	private void aMethod(String aParameter) {
		if(aParameter.length() > 0){
			// Risk att detta 
			// kastar nullpointer
		}
		
		// Säkrare är:
		if (aParameter != null && 
				aParameter.length() > 0) {
			// ...
		}else{
			System.out.println(
					"Felaktig input");
		}
	}
}
