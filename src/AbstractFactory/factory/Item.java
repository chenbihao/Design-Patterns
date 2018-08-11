package AbstractFactory.factory;

/**
 * @author CC
 * @Description 该类是Link类和Tray类的父类，这样 Link类和Tray类就具有可替换性
 */
public abstract class Item {
	protected String caption;

	public Item(String caption) {
		this.caption = caption;
	}

	public abstract String makeHTML();
}
