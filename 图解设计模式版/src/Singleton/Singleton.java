package Singleton;

public class Singleton {
	// 必须为private 防止不小心使用new关键字创建
	// 并且不能为null否则会有线程安全问题
	private static Singleton singleton = new Singleton();

	private Singleton() {
		System.out.println("初始化Singleton...");
	}

	// 获取唯一实例的方法通常命名为:getInstance
	public static Singleton getInstance() {
		return singleton;
	}
}
