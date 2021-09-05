package FactoryMethod.idcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import FactoryMethod.framework.Factory;
import FactoryMethod.framework.Product;

public class IDCardFactory extends Factory {

	//private List owners = new ArrayList();
	private HashMap database=new HashMap();
	private int serial=100;
	
	@Override		
	protected synchronized Product createProduct(String owner) {
		return new IDCard(owner,serial++);
	}

	@Override
	protected void registerProduct(Product product) {
		//owners.add(((IDCard) product).getOwner());
		IDCard card=(IDCard) product;
		database.put(new Integer(card.getSerial()),card.getOwner());
	}

	public HashMap getDatabase() {
		return database;
	}
}
