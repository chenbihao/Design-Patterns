package FactoryMethod.idcard;

import FactoryMethod.framework.Product;

public class IDCard extends Product {

	private String owner;
	private int serial;

	// 没加public是因为想强制外部类通过IDCardFactory来生成，外部类没法直接new IDCard();
	IDCard(String owner, int serial) {
		System.out.println("制作" + owner + "(" + serial + ")" + "的ID卡.");
		this.owner = owner;
		this.serial = serial;
	}

	@Override
	public void use() {
		System.out.println("使用" + owner + "(" + serial + ")" + "的ID卡.");
	}

	public String getOwner() {
		return owner;
	}
	public int getSerial(){
		return serial;
	}

}
