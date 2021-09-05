package Bridge;

import java.util.Random;

/**
 * @author CC
 * @Description 增加了“显示0~times之间的随机次数”功能的类
 */
public class RandomDisplay extends CountDisplay {
	private Random r=new Random();
	public RandomDisplay(DisplayImpl impl) {
		super(impl);
	}
	public void randomDisplay(int times){
		multiDisplay(r.nextInt(times));
	}
}
