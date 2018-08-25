package Flyweight;

public class BigString {
	private BigChar[] bigchars;

	public BigString(String str) {
		bigchars = new BigChar[str.length()];
		BigCharFactory factory = BigCharFactory.getInstance();
		for (int i = 0; i < bigchars.length; i++) {
			bigchars[i] = factory.getBigChar(str.charAt(i));
		}
	}

	public void print() {
		for (int i = 0; i < bigchars.length; i++) {
			bigchars[i].print();
		}
	}
}
