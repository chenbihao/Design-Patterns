package Memento.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Gamer {
	private int money;
	private List<String> fruits = new ArrayList<String>();
	private Random random = new Random();
	private static String[] fruitsname = { "柠檬", "百香果", "葡萄", "哈密瓜", "芒果", "牛油果" };

	public Gamer(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void bet() {// 开始游戏
		int dice = random.nextInt(6) + 1;
		if (dice == 1) {
			money += 100;
			System.out.println("钱增加了...");
		} else if (dice == 2) {
			money /= 2;
			System.out.println("钱减半了...");
		} else if (dice == 6) {
			String f = getFruit();
			System.out.println("获得了水果：(" + f + ")...");
			fruits.add(f);
		} else {
			System.out.println("什么都没发生...");
		}
	}

	public Memento createMemento() {
		Memento m = new Memento(money);
		Iterator it = fruits.iterator();
		while (it.hasNext()) {
			String f = (String) it.next();
			if (f.startsWith("好吃的")) {
				m.addFruit(f);
			}
		}
		return m;
	}

	public void restoreMemento(Memento memento) {
		this.money = memento.money;
		this.fruits = memento.getFruit();
	}

	@Override
	public String toString() {
		return "Gamer [money=" + money + ", fruits=" + fruits + "]";
	}

	private String getFruit() {
		String prefix = "";
		if (random.nextBoolean()) {
			prefix = "好吃的";
		}
		return prefix + fruitsname[random.nextInt(fruitsname.length)];
	}

}
