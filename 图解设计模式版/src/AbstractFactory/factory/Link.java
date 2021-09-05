package AbstractFactory.factory;

/**
 * @author CC
 * @Description 没有实现父类的方法，所以也是抽象类
 */
public abstract class Link extends Item {
	protected String url;

	public Link(String caption, String url) {
		super(caption);
		this.url = url;
	}

}
