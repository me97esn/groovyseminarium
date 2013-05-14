package emils.goodoldjava;

import java.util.ArrayList;
import java.util.List;

public class ClosureInJava {
	public interface Calculator {
		public void calc(int i);
		public Object result();
	}
	
	static Object pickEven(int n, Calculator calculator) {
		for (int i = 2; i <= n; i += 2) {
			calculator.calc(i);
		}
		return calculator.result();
	}

	public static void main(String[] args) {
		Calculator add = new Calculator() {
			int i = 0;
			public void calc(int i2) {
				i += i2;
			}
			public Object result() {
				return i;
			}
		};

		Calculator multiply = new Calculator() {
			long l = 1;
			public void calc(int i2) {
				l *= i2;
			}
			public Object result() {
				return l;
			}
		};

		Calculator square = new Calculator() {
			List<Integer> list = new ArrayList<Integer>();
			public void calc(int i) {
				list.add(i * i);
			}
			public Object result() {
				return list;
			}
		};

		System.out.println(pickEven(100, add));
		System.out.println(pickEven(10, multiply));
		System.out.println(pickEven(10, square));
	}


}
