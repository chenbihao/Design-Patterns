package Bridge;

/**
 * @author CC
 * @Description 桥接 Bridge
 */
public class Main {

	public static void main(String[] args) {
		Display d1=new Display(new StringDisplayImpl("Display"));
		Display d2=new CountDisplay(new StringDisplayImpl("CountDisplay"));
		CountDisplay d3=new CountDisplay(new StringDisplayImpl("CountDisplay"));
		RandomDisplay d4=new RandomDisplay(new StringDisplayImpl("RandomDisplay"));
		
		d1.display();
		d2.display();
		d3.display();
		d3.multiDisplay(5);
		d4.randomDisplay(6);
	}

}
