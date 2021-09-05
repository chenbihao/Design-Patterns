package Prototype;

import Prototype.framework.Product;

public class UnderlinePen implements Product {
	private char unchar;

	public UnderlinePen(char unchar) {
		this.unchar = unchar;
	}

	@Override
	public void use(String s) {
		int length = s.getBytes().length;
		System.out.println("\"" + s + "\"");
		System.out.print(" ");
		for (int i = 0; i < length; i++) {
			System.out.print(unchar);
		}
		System.out.println("");
	}

	@Override
	public Product createClone() {
		Product p = null;
		try {
			p = (Product) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return p;
	}

}
