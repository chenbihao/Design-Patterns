package Singleton;

/**
 * @author CC
 * @Description 单例模式 Singleton
 */
public class Main {
	public static void main(String[] args) {
		System.out.println(".start");
		Singleton obj1 = Singleton.getInstance();
		Singleton obj2 = Singleton.getInstance();
		if (obj1 == obj2) {
			System.out.println("obj1和obj2是同一个实例");
		} else {
			System.out.println("obj1和obj2不是同一个实例");
		}
		System.out.println(".end");
	}
}
