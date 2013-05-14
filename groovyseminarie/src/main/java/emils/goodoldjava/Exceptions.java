package emils.goodoldjava;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Exceptions {
	private void aMethod() {
		try {
			String s = URLEncoder.encode("hello world","UTF-8");
			System.out.println(s);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
