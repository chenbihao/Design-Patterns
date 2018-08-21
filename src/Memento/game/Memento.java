package Memento.game;

import java.util.ArrayList;
import java.util.List;

public class Memento {
	int money;
	ArrayList fruits;

	public int getMoney() { 		// narrow interface (宽接口)
		return money;
	}

	Memento(int money) { 			// wide interface (窄接口)
		this.money = money;
		this.fruits=new ArrayList();
	}

	void addFruit(String fruit) { 	// wide interface
		this.fruits.add(fruit);
	}

	List getFruit() { 				// wide interface
		return (List) fruits.clone();
	}
}
