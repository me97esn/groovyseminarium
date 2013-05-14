package emils.goodoldjava;

import java.util.ArrayList;
import java.util.List;

public class SquareEvenNumbers {
	public static void main(String[] args) {	
		List<Integer> squares = new ArrayList<Integer>();
		for (int i = 2; i <= 10; i+=2) {
			squares.add( i * i );
		}
		
		System.out.println(squares);
	}
}
