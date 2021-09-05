package Flyweight;

/**
 * @author CC
 * @Description 享元 Flyweight
 */
public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage:java Main digits");
			System.out.println("Example:java Main 122123431");
			System.exit(0);
		}
		BigString bs = new BigString(args[0]);
		bs.print();
	}

}
