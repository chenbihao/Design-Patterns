package Decorator;

public class StringDisplay extends Display {
	private String str;

	public StringDisplay(String str) {
		super();
		this.str = str;
	}

	@Override
	public int getColumns() {
		return str.length();
	}

	@Override
	public int getRows() {
		return 1;
	}

	@Override
	public String getRowText(int row) {
		if (row == 0) {
			return str;
		} else {
			return null;
		}
	}

}
