package AbstractFactory.listfactory;

import AbstractFactory.factory.Factory;
import AbstractFactory.factory.Link;
import AbstractFactory.factory.Page;
import AbstractFactory.factory.Tray;

/**
 * @author CC
 * @Description 当只有一个具体工厂时没有必要划分抽象工厂和具体工厂，所以可以编写其他的工厂：xxxfactory
 */
public class ListFactory extends Factory {

	@Override
	public Link createLink(String caption, String url) {
		return new ListLink(caption, url);
	}

	@Override
	public Tray createTray(String caption) {
		return new ListTray(caption);
	}

	@Override
	public Page createPage(String title, String author) {
		return new ListPage(title, author);
	}

}
