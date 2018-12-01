# Design-Patterns
# 图解设计模式的练习


##设计模式的六大原则
###1、开闭原则（Open Close Principle）

开闭原则的意思是：对扩展开放，对修改关闭。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。简言之，是为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，后面的具体设计中我们会提到这点。

###2、里氏代换原则（Liskov Substitution Principle）

里氏代换原则是面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。LSP 是继承复用的基石，只有当派生类可以替换掉基类，且软件单位的功能不受到影响时，基类才能真正被复用，而派生类也能够在基类的基础上增加新的行为。里氏代换原则是对开闭原则的补充。实现开闭原则的关键步骤就是抽象化，而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。

###3、依赖倒转原则（Dependence Inversion Principle）

这个原则是开闭原则的基础，具体内容：针对接口编程，依赖于抽象而不依赖于具体。

###4、接口隔离原则（Interface Segregation Principle）

这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。它还有另外一个意思是：降低类之间的耦合度。由此可见，其实设计模式就是从大型软件架构出发、便于升级和维护的软件设计思想，它强调降低依赖，降低耦合。

###5、迪米特法则，又称最少知道原则（Demeter Principle）

最少知道原则是指：一个实体应当尽量少地与其他实体之间发生相互作用，使得系统功能模块相对独立。

###6、合成复用原则（Composite Reuse Principle）

合成复用原则是指：尽量使用合成/聚合的方式，而不是使用继承。



## 适应设计模式：

### 迭代器 Iterator	（一个一个遍历）
		
	/** 
	 * 	为什么要引入迭代器（Iterator）呢？
	 *	这里只使用了Iterator的hasNext和next方法
	 *	也就是说 不依赖于BookShelf的实现
	 *	所以不管BookShelf怎么改变，只要BookShelf的iterator()方法有效
	 *	即使不对while循环进行修改，代码也能正常运行
	 *	帮助我们编写【可复用】的代码
	 *
	 * 	将遍历功能置于Aggregate角色外是Iterator模式的一个特征
	 * 	根据这个特征可以针对一个ConcreteAggregate角色编写多个ConcreteIterator角色
	 *
	 * 	//遍历方法可以多种多样
	 * 	//不需要deleteIterator 
	 */
	
### 适配器 Adapter		（加个适配器以便于复用）
		
	/**
	* 使用现有的类时，可以使用适配模式来把这些现有的类当做【组件】使用
	* 当出现bug时我们可以很明确的知道bug不是由现有的类产生的
	* 可以不改变现有代码的前提下适配新的接口（API）
	* 可以解决新旧版本兼容性问题
	*/	 
		 
		 
		 
## 交给子类：

### 模板方法 TemplateMethod	（将具体处理交给子类）
		 
	/**
	* 模板方法模式的优点是可以在抽象父类中编写算法
	* 要求父类子类之间的协作与一致性
	* 处理的流程被定义在父类中，而具体的处理则交给了子类
	*/ 
	 	
### 工厂方法 FactoryMethod		（将实例的生成交给子类）
	 	
	/**
	* 工厂模式把框架(framework包)和具体加工(idcard包) 分离开来 进行【解耦】
	* 框架(framework包)不依赖 具体加工(idcard包)
	* 不用new关键字来生成实例，而是调用专用方法进行生成，防止父类和其他具体类耦合
	* 
	* 注意：使用模板方法模式和工厂方法模式需要理解框架，所以必须要向维护这些类的开发人员正确的传达设计意图
	*
	*
	* 多种生成实例的方法:
	* 	1.定义为抽象方法:像现在一样在Factory类中直接定义，这样子类就必须实现。
	* 		protected abstract Product createProduct(String name);
	* 	2.为其实现默认处理，这样的话如果子类没有实现该方法，将进行默认处理
	* 		public Product createProduct(String name){
	* 			return new Product(name);		
	* 		}
	* 	3.在其中抛出异常，这样子类没有实现的话可以告知程序员(异常类需编写)
	* 		public Product createProduct(String name){
	* 			throw new FactoryMethodRuntimeException();	
	* 		}
	*/
	 	
	 	
##	生成实例：
	 	
### 单例 Singleton	（只有一个实例）
		 
	/**
	*	private static Singleton singleton=new Singleton();
	* 	//必须为private 防止不小心使用new关键字创建  
	*	//并且不能先为null 否则会有线程安全问题
	*
	*	//获取唯一实例的方法通常命名为:getInstance
	*/ 	
	 	
### 原型 Prototype	（通过复制生成实例）
		 
	/**
	*	以下情况我们无法根据类来生成实例，而要根据实例来生成实例
	*	1.对象总类繁多，无法将他们整合到一个类时
	*	2.难以根据类生成实例时
	*	3.想解耦框架与生成的实例时
	*
	*	在Porduct和Manager类中的代码完全没有出现其他类，意味着我们可以独立修改这两个类，不受其他类影响
	*	这是很重要的，因为一旦在类中使用到了别的类名，就意味着该类与其他类紧密地耦合在一起
	*	
	*	作为【组件复用】：一旦在代码中出现要使用类的名字，就无法与类分离开来，也就是无法实现复用
	*	此处说的“作为组件复用”中不包含替换源代码，以JAVA来说，重要的是当手边只有.class文件时，该类能否复用
	*	即【即使没有.java文件也能复用该类】
	*
	*	//实现Clonable接口的类的实例可以调用clone方法进行复制
	*	//clone方法的返回值是复制出新的实例
	*	//没有实现Clonable接口的类调用clone方法会在运行时抛出异常（CloneNotSupportedException）
	*
	*	//clone方法定义在java.lang.Object中，因此所有类都继承了clone方法
	*	//Clonable接口只是【标记接口】，没有实现任何方法
	*	
	*	//clone方法进行的是【浅复制】
	*	//只是：将被复制的实例的字段值直接复制到新的实例中
	*	//例如：当字段中保存的是数组时，使用clone方法进行复制只会复制该数组的引用
	*	//无法满足需求时：重写clone方法，并使用super.clone()来调用父类的clone方法
	*	//注意：clone只是复制，并不会调用被复制实例的构造函数，对于需要初始化的类需要自己实现clone方法，并在内部进行初始化操作
	*	
	*/ 	
	 	
### 构建 Builder		（组装复杂的实例）
	 	
	/**
	*	Director仅使用Builder的方法即可编写文档
	*	即：Director并不关心实际编写文档的是哪个子类
	*	
	*	//谁知道什么?
	*	//Main并不知道Builder类，它只是调用了Director类的construct方法
	*	//Director类只知道Builder类，并不知道它真正的使用了哪个类
	*	//【只有不知道子类才能替换】【可替换性】
	*	//正是因为可以替换，组件才具有高价值
	*/ 	
	 	
### 抽象工厂 AbstractFactory	（将关联零件组装成产品）		
	 	
	/**
	*	易于增加具体的工厂 ：
	*	只需编写Factory，Link，Tray，Page
	*	
	*	难以增加新的零件	比如要在factory包中增加一个图片的零部件
	*	在ListFactory中加入createPicture方法，编写ListPicture类
	*	已经编写完成的具体工厂越多，修改的工作量就会越大
	*	
	*	//	java中的几种生成实例的方法
	*	//	1:new		Something obj =new Something();	//此时类名会出现在代码中（强耦合）
	*	//	2:clone	(Something)clone();
	*	//	3:newInstance someobj.getClass().newInstance();
	*	
	*/ 	
	
	
	
## 分开考虑：
	 	
### 桥接 Bridge		（将类的功能层次结构与实现层次结构分离）
		 
	/**	
	*	类的层次结构的两个作用：
	*	-希望增加新功能时
	*	--父类具有基本功能
	*	--在子类中增加新的功能	（以上被称作类的功能层次结构）
	*	-希望增加新的实现时
	*	--父类通过声明抽象方法来定义接口（API）
	*	--子类通过实现具体方法来实现接口（API）	（以上被称作类的实现层次结构）
	*	
	*	类的层次结构的混杂与分离：
	*	只有一层时，类的功能层次结构与实现层次结构是混杂在一起的
	*	使类的结构变得复杂，也难以透彻地理解类的层次结构
	*	因此，我们要将类的功能层次结构与实现层次结构分离，并且搭建一座桥梁使他们有联系
	*	
	*	//分开后更容易扩展：
	*	//只需要在类的功能层次结构一侧增加类即可，而且增加后的功能可以被“所有的实现”使用
	*	
	*	//继承是强关联，委托是弱关联
	*	//Display类中使用了委托，这样类的任务就发生了转移
	*	//impl.rawOpen();
	*
	*/  	
	 	
	 	
### 策略 Strategy	（整体地替换算法）
		 
	/**
	*	为什么需要特意编写Strategy角色：
	*	使用委托这种弱关联关系可以很方便地整体替换算法
	*	程序运行中也可以切换策略
	*	或者利用某种算法去验证另外一种算法
	*/  		
	 	
	 	
	 	
	 	
## 一致性：
	 	
### 组合 Composite		（容器与内容的一致性）
		 
	/**
	*	使容器与内容具有一致性，创造出递归结构
	*
	*	//多个和单个的一致性：
	*	//即将多个对象结合在一起，当作一个对象进行处理
	*/  	
	 	
### 装饰器 Decorator		（装饰边框与被装饰物的一致性）
		 
	/**
	*	通过继承，装饰边框与被装饰物具有相同的方法（一致性）
	*	接口（API）的透明性：即使装饰物被装饰边框装饰起来了，其他类依旧可以调用方法
	*	在不改变被装饰物的前提下增加功能，并且使用了委托：return display.getRows();
	*	只需要一些装饰物就可以添加许多功能
	*	java.io包中也使用了Decorator模式
	*	
	*	缺点：会导致增加许多很小的类
	*/  		
	 	
	 	
	 	
	 	
	 	
## 访问数据结构：
	 	
### 访问者 Visitor		（访问数据结构并处理数据）
		 
	/**
	*	（使用了组合 Composite的例子）
	*	在Visitor模式中，数据结构与处理被分离开来
	*	
	*	//双重分发：
	*	//element.accept(visitor);
	*	//visitor.visit(element);
	*	//
	*	//复杂的目的:
	*	//将处理从数据结构中分离开来，提高了File类和Directory类作为组件的【独立性】
	*	//
	*	//【开闭原则】：对扩展开放，对修改关闭
	*	//在不修改现有代码的前提下进行扩展
	*	//
	*	//易增加visitor难增加Element
	*	//
	*	//工作条件：访问者必须从数据结构中获取足够多的信息后才能工作
	*	//缺点是：如果公开的不应当公开的信息，将对数据结构的改良会变得非常困难
	*/  	
	 	
### 职责链 Chain of Responsibility		（推卸责任）
		 
	/**
	*	将多个对象组成一条职责链，然后按照他们在职责链上的顺序一个一个地找出到底谁来负责处理
	*
	*	//专注于自己的工作
	*	//可以动态的改变职责链
	*	//弱化了发出请求和处理请求之间的关系
	*/  		
	 	
	 
	 	
## 简单化：
	 	
### 外观 Facade		（简单窗口）
		 
	/**
	*	//API(接口)变少了,意味着程序与外部的关联关系弱化了，使我们的类更容易作为组件被复用
	*	//可以递归地使用Facade模式
	*/  	
	 	
### 中介者 Mediator		（只有一个仲裁者）
		 
	/**
	*	要调整多个对象之间的关系时，就需要用到Mediator模式。
	*	将逻辑处理交给仲裁者负责
	*	
	*	//组员可以复用但仲裁者难复用
	*/  		
	 	
	 	
	 	
	 	
## 管理状态：
	 	
### 观察者 Observer		（发送状态变化通知）
		 
	/**
	*	Observer的顺序 ：     注意 不能因为update方法的调用顺序发生改变而产生问题
	*	当Observer的行为会对Subject产生影响时：可能会导致方法被循环调用
	*	传递更新信息的方式：
	*	void update(Generator g);			（1）
	*	void update(Generator g,int i);		（2）
	*	void update(int i);				（3）
	*	从“观察”变为“通知”
	*	
	*	java.util.Observer接口和java.util.Observable接口
	*	Observable接口：public void update(Observable obj,Object arg)
	*	update方法的参数则接收到：Observable类的实例是被观察的Subject角色；Object类的实例是附加信息，与（2）类似
	*	缺点:必须继承，传递给Observable接口的Object对象必须为Observer的子类
	*	
	*	
	*	//利用抽象类和接口从具体类中抽出抽象方法
	*	//将实例作为参数传递至类中，或者在类的字段中保存实例时，不使用具体类型而是使用抽象类和接口
	*	
	*/  	
	 	
### 备忘录 Memento		（保存对象状态）
		 
	/**
	*	想能恢复实例，必须有能自由访问实例内部结构的权限，这样会导致破坏【封装性】
	*	Memento可以在保存和恢复实例时防止对象的封装性遭到破坏。
	*	可以有多个Memento
	*	有效期限：如果把Memento保存成文件，那么后续系统的升级可能导致兼任问题
	*	
	*	职责分担：更变为可以多次撤销；更变为不仅可以撤销，还可以将现在的状态保存在文本中
	*	
	*	//两种接口（API）和可见性	// narrow interface (宽接口)	// wide interface (窄接口)
	*/  		
	 	
### 状态 State		（用类表示状态）
		 
	/**
	*	表示状态的类：白天，黑夜（使用单例模式）
	*	接收时间切换状态，即【状态迁移】
	*	
	*	//分而治之	不会自相矛盾	
	*	//依赖与状态的处理：定义接口，声明抽象方法；定义多个类，实现具体方法；
	*	//
	*	//应该由谁来管理状态迁移：实际调用该方法是具体状态类，我们将状态迁移看做依赖于状态的处理
	*	//缺点：每个具体状态类都需要知道其他状态类，各个类的依赖关系比较强
	*	//如果要将所有状态迁移交给Context来负责，可以使用仲裁者模式
	*	//当然，或也可以用状态迁移表来设计程序
	*	//
	*	//易于增加新的状态：但增加新的依赖与状态的处理是很困难的（但也可以，直接写在接口，编译器会提醒）
	*/  	
	 	
	
	 	
	 	
	 	
## 避免浪费：
	 	
### 享元 Flyweight		（共享对象，避免浪费）
		 
	/**
	*	Intrinsic与Extrinsic:
	*	Intrinsic信息: 不依赖于位置与状况，可以共享
	*	Extrinsic信息: 依赖于位置与状况，不可以共享
	*	
	*	注意：不要让被共享的实例被CG回收了
	*
	*	//public 【synchronized】 BigChar getBigChar
	*/  	
	 	
### 代理 Proxy		（只在必要时生成实例）
		 
	/**
	*	无论get,set方法被调用多少次，都不会生成真身的实例，只在必要时生成实例
	*	真身类并不知道代理类的存在，而代理类中却显式地写出了
	*	解耦方法:修改代理类使让其不知道真类名：
	*	private Printable real;
	*	private String className;
	*	.......(构造函数中加入className)
	*	real=(Printable)Class.forName(className).newInstance();
	*	
	*	//使用代理人来提升处理速度
	*	//透明性：通过接口，main可以不用在意调用的是代理类还是真身
	*	//各种代理模式：
	*	//虚拟代理（本例）；远程代理（不必在意真身是否在远程网络上）；Access Proxy（权限访问）
	*/  		
	 	
	 	
	 	
	 	
## 用类来表现：
	 	
### 命令 Command		（命令也是类）
		 
	/**
	*	用对象表示命令来保存命令的历史纪录和重复执行命令
	*
	*	//java匿名内部类与设配器结合能更轻松地编写代码
	*/  	
	 	
### 解释器 Interpreter		（语法规则也是类）
		 
	/**
	*	
	*	//
	*/  		
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
