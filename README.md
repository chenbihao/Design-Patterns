# Design-Patterns
图解设计模式的练习

#迭代器Iterator
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
	
#适配器Adapter
		/**
		* 使用现有的类时，可以使用适配模式来把这些现有的类当做组件使用
		* 当出现bug时我们可以很明确的知道bug不是由现有的类产生的
		* 可以不改变现有代码的前提下适配新的接口（API）
		* 可以解决新旧版本兼容性问题
		*/	 
		 