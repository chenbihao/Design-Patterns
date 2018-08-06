# Design-Patterns
# 设计模式的练习

## 迭代器Iterator
		
	/** 
	 * 	为什么要引入迭代器（Iterator）呢？
	 *	这里只使用了Iterator的hasNext和next方法
	 *	也就是说 不依赖于BookShelf的实现
	 *	所以不管BookShelf怎么改变，只要BookShelf的iterator()方法有效
	 *	即使不对while循环进行修改，代码也能正常运行
	 *	帮助我们编写“可复用”的代码
	 */
		
	/**
	 * 	将遍历功能置于Aggregate角色外是Iterator模式的一个特征
	 * 	根据这个特征可以针对一个ConcreteAggregate角色编写多个ConcreteIterator角色
	 */
		
	/**
	 * 	遍历方法可以多种多样
	 * 	不需要deleteIterator 
	 */
	
## 适配器Adapter
		
	/**
	* 使用现有的类时，可以使用适配模式来把这些现有的类当做组件使用
	* 当出现bug时我们可以很明确的知道bug不是由现有的类产生的
	* 可以不改变现有代码的前提下适配新的接口（API）
	* 可以解决新旧版本兼容性问题
	*/	 
		 
## 模板方法TemplateMethod	 
		 
	/**
	* 模板方法模式的优点是可以在抽象父类中编写算法
	* 要求父类子类之间的协作与一致性
	* 处理的流程被定义在父类中，而具体的处理则交给了子类
	*/ 
	 	
## 工厂方法FactoryMethod	
	 	
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
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
