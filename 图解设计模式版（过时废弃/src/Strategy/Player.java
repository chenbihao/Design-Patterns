package Strategy;

public class Player {
	private String name;
	private Strategy strategy;
	private int wincount;
	private int losecount;
	private int gamecount;

	public Player(String name, Strategy strategy) {
		this.name = name;
		this.strategy = strategy;
	}

	public Hand nextHand() {// 根据决策决定下一局要出的手势
		return strategy.nextHand();
	}

	public void win() {
		strategy.study(true);
		wincount++;
		gamecount++;
	}

	public void lose() {
		strategy.study(false);
		losecount++;
		gamecount++;
	}

	public void even() {
		gamecount++;
	}

	@Override
	public String toString() {
		return name + ":gamecount=" + gamecount + ", wincount=" + wincount + ", losecount=" + losecount + "]";
	}

}
