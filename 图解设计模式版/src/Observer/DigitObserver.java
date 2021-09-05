package Observer;

/**
 * @author CC
 * @Description 数字显示数值
 */
public class DigitObserver implements Observer {

	@Override
	public void update(NumberGenerator generator) {
		System.out.println("DigitObserver:" + generator.getNumber());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
