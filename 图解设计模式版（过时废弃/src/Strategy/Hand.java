package Strategy;

/**
 * @author CC
 * @Description 出拳的类
 */
public class Hand {
	public static final int HANDVALUE_GUU = 0;
	public static final int HANDVALUE_CHO = 1;
	public static final int HANDVALUE_PAA = 2;
	public static final Hand[] hand = { // 猜拳的实例
			new Hand(HANDVALUE_GUU), 
			new Hand(HANDVALUE_CHO), 
			new Hand(HANDVALUE_PAA), 
	};
	
	private static final String[] name = { "石头", "剪刀", "布" }; // 实例对应的字符串
	
	private int handvalue; // 猜拳中出的手势

	private Hand(int handvalue) {
		this.handvalue = handvalue;
	}

	public static Hand getHand(int handvalue) {
		return hand[handvalue];
	}

	public boolean isStrongerThan(Hand h) { // this赢了h则返回true
		return fight(h) == 1;
	}

	public boolean isWeakerThan(Hand h) { // this输了h则返回true
		return fight(h) == -1;
	}

	private int fight(Hand h) { // 计分
		if (this == h) {
			return 0;
		} else if ((this.handvalue + 1) % 3 == h.handvalue) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return name[handvalue];
	}

}
