package Observer;

/**
 * @author CC
 * @Description 观察者 Observer
 */
public class Main {

	public static void main(String[] args) {
		NumberGenerator ig =new IncrementalNumberGenerator(10,50,5);
		NumberGenerator ng = new RandomNumberGenerator();
		Observer o1 = new DigitObserver();
		Observer o2 = new GraphObserver();
		//ng.addObserver(o1);
		//ng.addObserver(o2);
		//ng.execute();
		ig.addObserver(o1);
		ig.addObserver(o2);
		ig.execute();
	}

}
