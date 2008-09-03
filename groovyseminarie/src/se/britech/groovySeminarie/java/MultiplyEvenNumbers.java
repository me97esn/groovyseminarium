package se.britech.groovySeminarie.java;

public class MultiplyEvenNumbers {
	public static void main(String[] args) {	
		long product = 1;
		for (int i = 2; i <= 10; i+=2) {
			product *= i;
		}
		
		System.out.println(product);
	}
}
