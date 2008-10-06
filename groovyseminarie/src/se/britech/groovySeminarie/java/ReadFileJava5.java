package se.britech.groovySeminarie.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFileJava5 {
	public static void main(String[] args) {
		File f = new File("C:/programmering/workspace/groovyseminarie/dokument/aFileWithText.txt");
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
