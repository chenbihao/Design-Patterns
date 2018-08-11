package AbstractFactory;

import AbstractFactory.factory.Factory;
import AbstractFactory.factory.Link;
import AbstractFactory.factory.Page;
import AbstractFactory.factory.Tray;

/**
 * @author CC
 * @Description 抽象工厂 AbstractFactory
 */
public class Main {

	public static void main(String[] args) {
		if(args.length!=1){
			System.out.println("Usage:java Main class.name.of.ConcreteFactory");
			System.out.println("Example 1:java Main AbstractFactory.listfactory.ListFactory");
			System.out.println("Example 2:java Main AbstractFactory.xxxfactory.xxxFactory");
			System.exit(0);
		}
		Factory factory=Factory.getFactory(args[0]);
		
		Link zh=factory.createLink("知乎","zhihu.com");
		Link git=factory.createLink("github","github.com");
		Link bd=factory.createLink("百度","baidu.com");
		Link gg=factory.createLink("谷歌","google.com");

		Tray trayz=factory.createTray("杂");
		trayz.add(zh);
		trayz.add(git);
		Tray trayss=factory.createTray("搜索引擎");
		trayss.add(bd);
		trayss.add(gg);
		
		Page page=factory.createPage("LinkPage","cc");
		page.add(trayz);
		page.add(trayss);
		page.output();
	}

}
