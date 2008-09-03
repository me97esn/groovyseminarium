package se.britech.groovySeminarie.java;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFileJava14 {

	public static void main(String args[]) {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(
					"C:/programmering/workspace/groovyseminarie/dokument/aFileWithText.txt");

			DataInputStream dataInputStream = new DataInputStream(fin);

			while (dataInputStream.available() > 0) {
				System.out.println(dataInputStream.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
