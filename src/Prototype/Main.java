package Prototype;

import Prototype.framework.Manager;
import Prototype.framework.Product;

/**
 * @author CC
 * @Description 原型模式 Prototype
 */
public class Main {

	public static void main(String[] args) {
		// 初始化
		Manager manager = new Manager();
		UnderlinePen upen = new UnderlinePen('~');
		MessageBox mbox = new MessageBox('*');
		MessageBox sbox = new MessageBox('/');
		manager.register("strong message", upen);
		manager.register("warning box", mbox);
		manager.register("slash box", sbox);
		// 生成
		Product p1 = manager.create("strong message");
		p1.use("hello,world.");
		Product p2 = manager.create("warning box");
		p2.use("hello,world.");
		Product p3 = manager.create("slash box");
		p3.use("hello,world.");

	}

}
