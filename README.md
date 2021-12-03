# 设计模式 Design-Patterns

[TOC]


> 文章文件夹里有详细文章（附带示例以及实例代码），
> 
> 可以点进去浏览更多内容，此MD文件只包含一些介绍内容
> 
> 同时欢迎关注我的公众号：迈向架构师


# 创建型

---

## [单例模式（Singleton Design Pattern）](src/文章/Singleton.md)   
> ↑ 点击查看详细说明与示例 ↑

### 介绍

单例模式：一个类只允许创建一个对象（或者实例），那这个类就是一个单例类，这种设计模式就叫作单例设计模式，简称单例模式。

### 适用场景
- 保证一个类只有一个实例
- 为该实例提供一个全局访问节点

### 优缺点

优点：
- 可以保证一个类只有一个实例
- 获得指向该实例的全局访问节点

缺点：
- 对 OOP 特性支持不友好
- 隐藏了类之间的依赖关系
- 扩展性、可测试性不友好
- 不支持有参数的构造函数

### 实现方式
1. 将默认构造函数设为私有， 防止其他对象使用单例类的 new 运算符。
2. 新建一个静态构建方法作为构造函数。 该函数调用私有构造函数来创建对象， 并将其保存在一个静态成员变量中。 此后所有对于该函数的调用都将返回这一缓存对象。

---

## [工厂模式（Factory）](src/文章/Factory.md)


工厂模式：将创建对象移交给工厂来处理。

> 大部分工厂类都是以“Factory”这个单词结尾的，但也不是必须的，比如 Java 中的 DateFormat、Calender。
>
> 除此之外，工厂类中创建对象的方法一般都是 create 开头，比如代码中的 createParser()，
>
> 但有的也命名为 getInstance()、createInstance()、newInstance()，
>
> 有的甚至命名为 valueOf()（比如 Java String 类的 valueOf() 函数）等等。
>
---

## [简单工厂（Simple Factory）](src/文章/Factory-SimpleFactory.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

简单工厂模式描述了一个类，它拥有一个包含大量条件语句的构建方法，可根据方法的参数来选择对何种产品进行初始化并将其返回。

### 适用场景

当每个对象的创建逻辑都比较简单的时候，将多个对象的创建逻辑放到一个工厂类中。

### 优缺点

优点：
- 代码简单
- 避免耦合

### 与其他模式的关系

- 大多数情况下，简单工厂是引入工厂方法或抽象工厂模式时的一个中间步骤。

### 实现方式

1. 新建一个工厂类。
2. 新建方法，通过入参判断返回生成的对象。

---

## [工厂方法（Factory Method）](src/文章/Factory-FactoryMethod.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

工厂方法是一种创建型设计模式，其在父类中提供一个创建对象的方法，允许子类决定实例化对象的类型。

> Head First 定义：定义了一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法让类把实例化推迟到子类

### 适用场景

当每个对象的 **创建逻辑** 都比较 **复杂** 的时候，

为了避免设计一个过于庞大的简单工厂类时，将创建逻辑拆分得更细，

让每个对象的创建逻辑独立到各自的工厂类中。

> - 当你在编写代码的过程中，如果无法预知对象确切类别及其依赖关系时，可使用工厂方法。
> - 如果你希望用户能扩展你软件库或框架的内部组件，可使用工厂方法。
> - 如果你希望复用现有对象来节省系统资源，而不是每次都重新创建对象，可使用工厂方法。

### 优缺点

优点：
- 避免耦合
- 单一职责
- 开闭原则

缺点：
- 代码变得复杂

### 与其他模式的关系

- 在许多设计工作的初期都会使用工厂方法模式（较为简单，而且可以更方便地通过子类进行定制）， 随后演化为使用抽象工厂模式、原型模式或生成器模式（更灵活但更加复杂）。

- 你可以同时使用工厂方法和迭代器模式来让子类集合返回不同类型的迭代器，并使得迭代器与集合相匹配。

- 工厂方法是模板方法模式的一种特殊形式。同时，工厂方法可以作为一个大型模板方法中的一个步骤。


### 实现方式

工厂方法模式建议使用特殊的工厂方法代替对于对象构造函数的直接调用。

对象仍将通过 new 运算符创建，只是该运算符改在工厂的方法中调用罢了。

> 1. 让所有产品都遵循同一接口。该接口必须声明对所有产品都有意义的方法。
> 2. 在创建类中添加一个空的工厂方法。该方法的返回类型必须遵循通用的产品接口。
> 3. 在创建者代码中找到对于产品构造函数的所有引用。将它们依次替换为对于工厂方法的调用，同时将创建产品的代码移入工厂方法。你可能需要在工厂方法中添加临时参数来控制返回的产品类型。
> 5. 现在，为工厂方法中的每种产品编写一个创建者子类，然后在子类中重写工厂方法，并将基本方法中的相关创建代码移动到工厂方法中。
> 6. 如果应用中的产品类型太多，那么为每个产品创建子类并无太大必要，这时你也可以在子类中复用基类中的控制参数。
> 7. 如果代码经过上述移动后，基础工厂方法中已经没有任何代码，你可以将其转变为抽象类。如果基础工厂方法中还有其他语句，你可以将其设置为该方法的默认行为。



---
## [抽象工厂（Abstract Factory）](src/文章/Factory-AbstractFactory.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

抽象工厂是一种创建型设计模式，它能创建一系列相关或相互依赖的对象，而无需指定其具体类。

> Head First 定义：提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类

### 适用场景

- 如果代码需要与多个不同系列的相关产品交互，但是由于无法提前获取相关信息，或者出于对未来扩展性的考虑，你不希望代码基于产品的具体类进行构建，在这种情况下，你可以使用抽象工厂。
- 如果你有一个基于一组抽象方法的类，且其主要功能因此变得不明确，那么在这种情况下可以考虑使用抽象工厂模式。
- 如果你的程序中并不涉及产品系列的话，那就不需要抽象工厂。

### 优缺点

优点：
- 可以确保同一工厂生成的产品相互匹配。
- 可以避免客户端和具体产品代码的耦合。
- 单一职责原则。你可以将产品生成代码抽取到同一位置，使得代码易于维护。
- 开闭原则。向应用程序中引入新产品变体时，你无需修改客户端代码。

缺点：
- 引入众多的接口和类，代码可能会比之前更加复杂。


### 与其他模式的关系

- 抽象工厂模式通常基于**一组工厂方法**，但你也可以使用原型模式来生成这些类的方法。

- 在许多设计工作的初期都会使用工厂方法模式（较为简单，而且可以更方便地通过子类进行定制），
  随后演化为使用抽象工厂模式、原型模式或生成器模式（更灵活但更加复杂）。

- 你可以同时使用工厂方法和迭代器模式来让子类集合返回不同类型的迭代器，并使得迭代器与集合相匹配。


### 实现方式

1. 以不同的产品类型与产品变体为维度绘制矩阵。
2. 为所有产品声明抽象产品接口。然后让所有具体产品类实现这些接口。
3. 声明抽象工厂接口，并且在接口中为所有抽象产品提供一组构建方法。
4. 为每种产品变体实现一个具体工厂类。
5. 在应用程序中开发初始化代码。该代码根据应用程序配置或当前环境，对特定具体工厂类进行初始化。然后将该工厂对象传递给所有需要创建产品的类。
6. 找出代码中所有对产品构造函数的直接调用，将其替换为对工厂对象中相应构建方法的调用。


---

### 工厂模式与 DI 容器

DI 容器：依赖注入容器（Dependency Injection Container）。

一个工厂类只负责某个类对象或者某一组相关类对象的创建，而 DI 容器负责的是整个应用中所有类对象的创建。

> DI 容器底层最基本的设计思路就是基于工厂模式的。
>
> DI 容器相当于一个大的工厂类，负责在程序启动的时候，根据配置（要创建哪些类对象，每个类对象的创建需要依赖哪些其他类对象）事先创建好对象。
>
> 当应用程序需要使用某个类对象的时候，直接从容器中获取即可。
>
> 正是因为它持有一堆对象，所以这个框架才被称为“容器”。


#### DI 容器的核心功能

- 配置解析
- 对象创建
- 对象生命周期管理

---

## [建造者模式（Builder）](src/文章/Builder.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

建造者模式（又叫生成器模式、构建者模式）：

建造者模式是一种创建型设计模式， 使你能够分步骤创建复杂对象。 该模式允许你使用相同的创建代码生成不同类型和形式的对象。

### 适用场景

- 避免重叠构造函数（telescopic constructor）
- 希望创建不同形式的产品或分步骤构造产品
- 类属性有依赖或约束关系时（如单个set无法满足多个值的校验）
- 创建不可变对象（构建前赋值）
- ...


### 优缺点

优点：

- 可以分步创建 或延缓创建 或递归创建
- 生成不同形式的产品时可以复用代码
- 单一职责

缺点：

- 需要新增类，复杂度增加

### 与其他模式的关系

- 工厂模式是用来创建不同但是相关的对象，建造者模式是用来创建一种类型的复杂对象


### 实现方式

- 创建一个Builder类（原对象内部类或者独立的类都可以）
- 实现其构造步骤（每个构造器的set）
- 实现build方法（包括校验逻辑与 **创建逻辑** ）
- 实现原对象的构造函数（参数为Builder，即创建方法）

--- 


## [原型模式（Prototype）](src/文章/Prototype.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

原型模式是一种**创建型设计模式**， 使你能够复制已有对象， 而又无需使代码依赖它们所属的类。

如果对象的**创建成本比较大**，而同一个类的不同对象之间差别不大（大部分**字段都相同**），
在这种情况下，我们可以利用对已有对象（原型）进行**复制**（或者叫拷贝）的方式来创建新对象，以达到**节省创建时间**的目的。

### 适用场景

- 对象**创建成本较大**（比如需要计算or排序等），且同一个类的不同对象直接差别不大（大部分字段相同）时。
- 需要**复制对象**，同时也希望代码能独立于对象所属的具体类时。
- 子类直接的区别仅在于其初始化的方式时，可以用该模式减少子类的数量（别人创建这些子类的目的可能是为了创建特定类型的对象）。
- ...

### 优缺点

优点：

- 克隆对象，代码不耦合。
- 可以克隆预生成对象，避免反复初始化。
- 更方便的生成复杂对象。
- 可以用继承以外的方式来处理复杂对象的不同配置。

缺点：

- 克隆包含循环引用的复杂对象可能会非常麻烦。

### 与其他模式的关系

- 在许多设计工作的初期都会使用**工厂方法模式**（较为简单，而且可以更方便地通过子类进行定制），随后演化为使用**抽象工厂模式**、**原型模式**或**生成器模式**（更灵活但更加复杂）。
- **抽象工厂模式**通常基于一组工厂方法，但也可以使用**原型模式**来生成这些类的方法。
- **原型**并不基于继承，但原型需要对被复制对象进行复杂的初始化。**工厂方法**基于继承，但是它不需要初始化步骤。
- **抽象工厂**、**生成器**和**原型**都可以用**单例模式**来实现。


### 实现方式

1. 原型类必须添加一个**以该类对象为参数的构造函数**。
2. **声明并实现 clone 方法**， clone 方法一般使用 new 关键字调用第一步的构造函数。
3. 还可以新建一个工厂类来当注册表用。

两种具体实现方式：
- **深拷贝**（完全独立的新对象）
- **浅拷贝**（有数据被修改的风险）

--- 


# 结构型

## [代理模式（Proxy）](src/文章/Proxy.md)
> ↑ 点击查看详细说明与示例 ↑


### 介绍

代理模式是一种**结构型**设计模式，让你能够提供对象的替代品或其占位符。

代理控制着对于原对象的访问，并允许在将请求提交给对象前后进行一些处理。


### 适用场景

- **非功能性需求开发**（增强代理，比如：监控、统计、鉴权、限流、事务、幂等、日志、缓存等）
- **本地执行远程服务**（远程代理，如 RPC 框架）
- **访问控制**（保护代理）
- **延迟初始化、智能引用**（虚拟代理：如果是重量级对象，可以实现延迟初始化、监控无使用则销毁等）
- ...

### 优缺点

优点：

- 对客户端透明
- 可以进行生命周期的管理
- 即使对象还没准备好，代理类也可以工作
- 开闭原则，可以不对服务和客户端修改的情况下创建新代理

缺点：

- 代码变复杂
- 服务响应可能延迟

### 与其他模式的关系

后续讲到对应的模式时再写


### 实现方式

1. 代理类和原始类需要**实现相同的接口**，如果是无法修改的第三方类可以**采用继承**的方式。
2. **创建代理类**，其中必须包含一个存储指向服务的引用的成员变量。
3. 根据需求**实现代理方法**。

以上为**静态代理**，

还有**动态代理**的实现方式：

- **jdk 动态代理**（通过反射实例化代理对象）
- **cglib 动态代理**（借助 asm 字节码技术：直接生成新的 .class 字节码文件）
- **Aspectj 动态代理**（通过织入的方式修改目标类：编译时织入/编译后织入/加载时织入）
- **instrumentation 动态代理**（修改目标类的字节码：类装载的时候动态拦截去修改）
- ...


--- 

## [桥接模式（Bridge）](src/文章/Bridge.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

#### 将抽象和实现解耦，让它们可以独立变化。

在 GoF 的《设计模式》中，桥接模式是这样定义的：“将抽象和实现解耦，让它们可以独立变化。”

独立的概念可能是：抽象/平台，域/基础设施，前端/后端或接口/实现。

**抽象部分**（也被称为接口）是一些实体的高阶控制层，该层自身不完成任何具体的工作，它需要将工作委派给**实现部分层**（也被称为平台）。

这里的抽象和实现是广义上的，而非特指抽象类、实现类。

例子：JDBC 驱动

- JDBC API 对应 **抽象**
- 数据库的 Driver 对应 **实现**

#### 多维度通过组合使可以独立扩展

很多书籍资料中还有另外一种理解方式： “一个类存在两个（或多个）独立变化的维度，通过组合的方式，让这些维度可以独立进行扩展。”

例子：slf4j

> slf4j 其中有三个核心概念，logger，appender 和 encoder。
>
> 分别指这个日志记录器负责哪个类的日志，日志打印到哪里以及日志打印的格式。
>
> 三个纬度上可以有不同的实现，使用者可以在每一纬度上定义多个实现。


### 适用场景

- 运行时切换不同实现方法
- 从几个独立维度上扩展一个类
- 拆分或重组一个具有多重功能的庞杂类
- ...

### 优缺点

优点：

- 可以创建与平台无关的类和程序。
- 客户端代码仅与高层抽象部分进行互动，不会接触到实现平台。
- 开闭原则：可以独立新增抽象或实现部分。
- 单一职责：抽象专注于高层逻辑处理，实现专注于实现细节。

缺点：

- 对高内聚的类使用该模式可能会让代码更加复杂。

### 与其他模式的关系

- 可以将抽象工厂模式和桥接搭配使用。
- 可以结合使用生成器模式和桥接模式：主管类负责抽象工作，各种不同的生成器负责实现工作。


### 实现方式

1. 在抽象基类（高阶控制层）中定义客户端的业务需求。
4. 在抽象类中添加指向实现类型的引用成员变量。
2. 在通用实现接口（实现平台层）中声明抽象部分所需的业务。
3. 创建实现类。
5. 如果高层逻辑有多个变体，则可通过扩展抽象基类为每个变体创建一个精确抽象。
6. 客户端代码必须将实现对象传递给抽象部分的构造函数才能使其能够相互关联。此后，客户端只需与抽象对象进行交互，无需和实现对象打交道。

--- 

## [装饰器模式（Decorator）](src/文章/Decorator.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

**装饰模式**是一种**结构型设计模式**，允许通过将对象放入包含行为的特殊封装对象中来为原对象绑定新的行为。

装饰器模式主要解决继承关系过于复杂的问题，通过组合来替代继承。它主要的作用是**给原始类添加增强功能**。

### 适用场景

- 无需修改代码的情况下给原始类添加增强功能。
- 类继承结构复杂、组合爆炸的情况下。
- 可以对原始类嵌套使用多个装饰器。

### 优缺点

优点：

- 无需创建新子类即可扩展对象的行为。
- 可以在运行时添加或删除对象的功能。
- 可以用多个装饰封装对象来组合几种行为。
- 单一职责原则。可以将实现了许多不同行为的一个大类拆分为多个较小的类。

缺点：

- 在封装器栈中删除特定封装器比较困难。
- 实现行为不受装饰栈顺序影响的装饰比较困难。
- 各层的初始化配置代码看上去可能会很糟糕。

### 与其他模式的关系

- **装饰模式**和**代理模式**有着相似的结构，但是其意图却非常不同。这两个模式的构建都基于组合原则，也就是说一个对象应该将部分工作委派给另一个对象。两者之间的不同之处在于**代理通常自行管理其服务对象的生命周期**，而**
  装饰的生成则总是由客户端进行控制**。

### 实现方式

1. 创建一个组件接口并在其中声明通用方法。
3. 创建一个具体组件类，并定义其基础行为。
4. 创建装饰基类，使用一个成员变量存储指向被封装对象的引用。该成员变量必须被声明为组件接口类型，从而能在运行时连接具体组件和装饰。装饰基类必须将所有工作委派给被封装的对象。
5. 装饰器实现类实现组件接口。

--- 


## [适配器模式（Adapter）](src/文章/Adapter.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

适配器模式是一种结构型设计模式，它能使接口不兼容的对象能够相互合作。

### 适用场景

- 封装有缺陷的接口设计。
- 统一多个类的接口设计。
- 替换依赖的外部系统。
- 兼容隔离老版本接口。
- 适配不同格式的数据。
- ...

### 优缺点

优点：

- 单一职责：将转换代码从业务逻辑中剥离。
- 开闭原则：不修改原有代码的情况下添加适配器。

缺点：

- 代码整体复杂度增加

### 与其他模式的关系

- **桥接模式**通常用于将接口与实现的分离，各自独立。另一方面，**适配器模式**通常在已有程序中使用，让相互不兼容的类能很好地合作。
- **适配器**可以对已有对象的接口进行修改，**装饰**则能在不改变对象接口的前提下强化对象功能并且支持递归组合。
- **适配器**能为被封装对象提供不同的接口，**代理**能为对象提供相同的接口，**装饰**则能为对象提供加强的接口。
- **外观模式**为现有对象定义了一个新接口，**适配器**则会试图运用已有的接口。**适配器**通常只封装一个对象，**外观**通常会作用于整个对象子系统上。

### 实现方式

有两种实现方式，一种是**类适配器**（通过继承），一种是**对象适配器**（通过组合）

1. 创建遵循客户端接口的适配器类。
2. 在适配器类中添加一个成员变量用于保存对于服务对象的引用（对象适配器）或者直接继承目标类（类适配器）。
3. 依次实现适配器类客户端接口的所有方法。适配器会**将实际工作委派给服务对象**，自身只负责接口或数据格式的转换。
4. 客户端必须通过客户端接口使用适配器。这样可以在不影响客户端代码的情况下修改或扩展适配器。

> 如果接口很多，并且适配器与目标接口定义大部分**相同**，推荐使用**类适配器**，复用多，代码量少。
>
> 如果接口很多，并且适配器与目标接口定义大部分**不同**，推荐使用**对象适配器**，组合结构更加灵活。

--- 

## [门面模式（Facade）](src/文章/Facade.md)
> ↑ 点击查看详细说明与示例 ↑


### 介绍

门面模式（外观模式）是一种结构型设计模式，能为程序库、框架或其他复杂类提供一个简单的接口。


### 适用场景

- 提供一组更简单易用、更高层的接口，隐藏系统的复杂性。
    - 可以解决易用性问题
    - 可以解决多次调用的性能问题
    - 可以解决简单的分布式事务问题
- ...


### 优缺点

优点：

- 代码独立于子系统
- 接口隔离原则
- 最少知识原则（迪米特法则）

缺点：

- 外观可能成为与程序中所有类都耦合的上帝对象


### 与其他模式的关系

- **适配器**是做接口转换，解决的是原接口和目标接口不匹配的问题。**门面模式**做接口整合，解决的是多接口调用带来的问题。
- 只需对客户端隐藏创建过程的话，可以用**抽象工厂模式**来代替**门面模式**。
- 一般只要一个**门面**，可以转换为**单例**。
- **门面**与**代理**的相似之处在于它们都缓存了一个复杂实体并自行对其进行初始化。**代理**与其服务对象遵循同一接口使得自己和服务对象可以互换。


### 实现方式

1. 在一个新的外观类中声明并实现该接口。（如果客户端代码没有对子系统进行初始化，也没有对其后续生命周期进行管理，那么外观必须完成此类工作）
2. 客户端代码仅通过外观来与子系统进行交互。（此后客户端代码将不会受到任何由子系统代码修改而造成的影响）
3. 如果外观变得过于臃肿，可以考虑将其部分行为抽取为一个新的专用外观类。


--- 


## [组合模式（Composite）](src/文章/Composite.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

组合模式是一种结构型设计模式，组合模式将一组对象组织（Compose）成树形结构，以表示一种”部分-整体”的层次结构。

组合模式让客户端可以统一单个对象和组合对象的处理逻辑。


### 适用场景

- 实现树状对象结构
- 以相同方式处理简单或复杂的元素
- ...


### 优缺点

优点：

- 可以利用多态和递归机制更方便地使用复杂树结构。
- 开闭原则：无须修改现有代码即可添加新元素。

缺点：

- 当差异较大时比较难划分接口。


### 与其他模式的关系

- **组合模式**通常和**责任链模式**结合使用。
- 可以在创建复杂**组合**树时使用**生成器模式**，可使其构造步骤以递归的方式运行。
- 可以使用**迭代器模式**来遍历组合树。
- 可以使用**访问者模式**对整个组合树执行操作。
- 可以使用**享元模式**实现组合树的共享叶节点以节省内存。
- 可以使用**原型模式**来复制大量使用**组合**或**装饰**的对象。



### 实现方式

1. 声明组件接口及其一系列方法。
2. 创建一个叶节点类表示简单元素。
3. 创建一个容器类表示复杂元素。
4. 在容器中定义添加和删除子元素的方法。


--- 

## [享元模式（Flyweight）](src/文章/Flyweight.md)
> ↑ 点击查看详细说明与示例 ↑


### 介绍

**享元模式**是一种结构型设计模式。

享元模式顾名思义就是被共享的单元，意图是**复用对象**，**节省内存**。


### 适用场景

- 存在大量重复对象（重复状态）且没有足够的内存容量时使用享元模式。
- ...


### 优缺点

优点：

- 节省大量内存。

缺点：

- 代码复杂度提升。
- 如果对象有不同的情景数据（外在状态），调用者需要耗费时间来重新计算。


### 与其他模式的关系

- 可以使用**享元模式**实现**组合模式**树的共享叶节点以节省内存。
- 如果能将对象的所有共享状态简化为一个享元对象，那么**享元**就和**单例**类似了。但这两个模式有两个根本性的不同。
    - **单例**只会有一个单例实体。
    - **享元**可以有多个实体，各实体的内在状态也可以不同。
    - **单例**对象可以是**可变**的。
    - **享元**对象是**不可变**的。


### 实现方式

1. 将享元对象划分为2部分：
    - **内在状态**（不变的部分）
    - **外在状态**（改变的部分）
2. 保留类中表示内在状态的成员变量，并将其设置为**不可修改**。
3. 客户端必须存储和计算**外在状态**（情景）的数值，因为只有这样才能调用享元对象的方法。
    - 为了使用方便，外在状态和引用享元的成员变量可以移动到单独的**情景类**中。
4. 可以创建**工厂类**来管理**享元缓存池**，如果使用了工厂，那么客户端就只能通过工厂来获取享元对象。

--- 


# 行为型



## [观察者模式（Observer）](src/文章/Observer.md)
> ↑ 点击查看详细说明与示例 ↑

### 介绍

**观察者模式**是一种行为型设计模式。

可以用来定义一种**订阅机制**，可在对象事件发生时**通知**多个“观察”该对象的其他对象。


### 适用场景

- 当应用中的一些对象必须**观察**其他对象时，可使用该模式。
- 当一个对象状态的改变需要**改变**其他对象，或实际对象是事先未知的或动态变化的时，可使用该模式。
- ...


### 优缺点

优点：

- 开闭原则：无须修改发布者代码即可引入新的订阅类，反之亦然。
- 可以在运行时建立对象之间的联系。

缺点：

- 通知顺序随机。


### 与其他模式的关系

后面讲到再写。


### 实现方式

1. 声明**订阅者接口**，该接口至少应声明一个 update 方法。
2. 声明**发布者接口**，并定义添加和删除订阅对象接口。
3. 创建**具体发布者类**，每次发布者发生了重要事件时都必须通知所有的订阅者。
4. 创建**具体订阅者类**，实现通知更新的方法。

> 观察者模式有不同的代码实现方式：
>
> 有**同步阻塞**/**异步非阻塞**的实现方式；
>
> 有**进程内**/**跨进程**的实现方式。

- 基于消息队列（跨进程）：
    - 需要引入一个新的系统（消息队列），增加了维护成本，但被观察者和观察者解耦更加彻底。


--- 



## [模板木事（Template Method）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑


### Todo...




## [策略模式（Strategy）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑




## [职责链模式（Chain of Responsibility）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑




## [状态模式（State）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑




## [迭代器模式（Iterator）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑




## [访问者模式（Visitor）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑




## [备忘录模式（Memento）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑




## [命令模式（Command）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑




## [解释器模式（Interpreter）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑




## [中介模式（Mediator）](src/文章/模板.md)
> ↑ 点击查看详细说明与示例 ↑



















