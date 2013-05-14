package emils.goodoldjava;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
public class ReadFileJava14 {
	static FileInputStream fin = null;
	public static void main(String args[]) {
		try {
			fin = new FileInputStream("C:/text.txt");
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
