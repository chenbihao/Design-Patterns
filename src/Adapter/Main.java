package Adapter;

/**
 * @author CC
 * @Description 类 适配器模式 
 */
public class Main {

	public static void main(String[] args) {
		
		Print p = new PrintBanner("test");
		p.printWeak();
		p.printStrong();
		
	}

}
