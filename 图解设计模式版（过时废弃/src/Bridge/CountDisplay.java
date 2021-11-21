package Bridge;

/**
 * @author CC
 * @Description 增加了“只显示规定次数”功能的类
 */
public class CountDisplay extends Display {

	public CountDisplay(DisplayImpl impl) {
		super(impl);
	}

	public void multiDisplay(int times) {
		open();
		for (int i = 0; i < times; i++) {
			print();
		}
		close();
	}
}
