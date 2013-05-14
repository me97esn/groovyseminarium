package emils.goodoldjava;

public class OptionalParameters {
	public void doSomething(){
		doSomething("Default string", new Object());
	}

	public void doSomething(String string) {
		doSomething(string, new Object());
	}

	private void doSomething(String string, Object object) {
		System.out.println(string + object);
	}
}
