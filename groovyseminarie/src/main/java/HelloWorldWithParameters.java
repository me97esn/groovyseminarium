package se.britech.groovySeminarie.java;

public class HelloWorldWithParameters {
	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			String s = args[0];
			// ...
		}else{
			System.out.println("Usage: HelloWorldWithParameters parameter");
		}
	}
}
