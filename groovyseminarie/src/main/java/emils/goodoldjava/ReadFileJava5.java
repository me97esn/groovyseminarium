package emils.goodoldjava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFileJava5 {
	static File f = new File("C:/text.txt");
	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
	}
}
