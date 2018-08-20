package Observer;

/**
 * @author CC
 * @Description 按照步长增长的数值
 */
public class IncrementalNumberGenerator extends NumberGenerator{
	private int number;
	private int end;
	private int inc;
	
	
	public IncrementalNumberGenerator(int number, int end, int inc) {
		super();
		this.number = number;
		this.end = end;
		this.inc = inc;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void execute() {
		for (int i = 0; i <=end; i+=inc) {
			number=i;
			notifyObservers();
		}
	}

}
