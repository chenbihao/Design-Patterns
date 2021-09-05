package FactoryMethod.idcard;

import FactoryMethod.framework.Factory;
import FactoryMethod.framework.Product;

/**
 * @author CC
 * @Description 工厂方法模式
 */
public class Main {
	/**
	 * 工厂模式把框架(framework包)和具体加工(idcard包) 分离开来 进行解耦
	 * 框架(framework包)不依赖 具体加工(idcard包)
	 * 不用new关键字来生成实例，而是调用专用方法进行生成，防止父类和其他具体类耦合
	 * 
	 * 注意：使用模板方法模式和工厂方法模式需要理解框架，所以必须要向维护这些类的开发人员正确的传达设计意图
	 */
	
	/**
	 * 多种生成实例的方法:
	 * 1.定义为抽象方法:像现在一样在Factory类中直接定义，这样子类就必须实现。
	 * 		protected abstract Product createProduct(String name);
	 * 2.为其实现默认处理，这样的话如果子类没有实现该方法，将进行默认处理
	 * 		public Product createProduct(String name){
	 * 			return new Product(name);		
	 * 		}
	 * 3.在其中抛出异常，这样子类没有实现的话可以告知程序员(异常类需编写)
	 * 		public Product createProduct(String name){
	 * 			throw new FactoryMethodRuntimeException();	
	 * 		}
	 */
	public static void main(String[] args) {
		Factory factory = new IDCardFactory();
		Product card1 = factory.create("小A");
		Product card2 = factory.create("小B");
		Product card3 = factory.create("小C");
		card1.use();
		card2.use();
		card3.use();
	}

}
