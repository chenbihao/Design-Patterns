# 抽象工厂（Abstract Factory）

## 介绍

抽象工厂是一种创建型设计模式，它能创建一系列相关或相互依赖的对象，而无需指定其具体类。

> Head First 定义：提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类

## 适用场景

- 如果代码需要与多个不同系列的相关产品交互，但是由于无法提前获取相关信息，或者出于对未来扩展性的考虑，你不希望代码基于产品的具体类进行构建，在这种情况下，你可以使用抽象工厂。
- 如果你有一个基于一组抽象方法的类，且其主要功能因此变得不明确，那么在这种情况下可以考虑使用抽象工厂模式。
- 如果你的程序中并不涉及产品系列的话，那就不需要抽象工厂。

## 实现方式

1. 以不同的产品类型与产品变体为维度绘制矩阵。
2. 为所有产品声明抽象产品接口。然后让所有具体产品类实现这些接口。
3. 声明抽象工厂接口，并且在接口中为所有抽象产品提供一组构建方法。
4. 为每种产品变体实现一个具体工厂类。
5. 在应用程序中开发初始化代码。该代码根据应用程序配置或当前环境，对特定具体工厂类进行初始化。然后将该工厂对象传递给所有需要创建产品的类。
6. 找出代码中所有对产品构造函数的直接调用，将其替换为对工厂对象中相应构建方法的调用。

## 优缺点

优点：
- 可以确保同一工厂生成的产品相互匹配。
- 可以避免客户端和具体产品代码的耦合。
- 单一职责原则。你可以将产品生成代码抽取到同一位置，使得代码易于维护。
- 开闭原则。向应用程序中引入新产品变体时，你无需修改客户端代码。

缺点：
- 引入众多的接口和类，代码可能会比之前更加复杂。


## 与其他模式的关系

- 抽象工厂模式通常基于**一组工厂方法**，但你也可以使用原型模式来生成这些类的方法。

- 在许多设计工作的初期都会使用工厂方法模式（较为简单，而且可以更方便地通过子类进行定制），
随后演化为使用抽象工厂模式、原型模式或生成器模式（更灵活但更加复杂）。

- 你可以同时使用工厂方法和迭代器模式来让子类集合返回不同类型的迭代器，并使得迭代器与集合相匹配。


---
## 示例

王争老师举的例子：

前面简单工厂提到的可复用写法 RuleConfigParserFactory，通过不同文件类型加载解析器。

如果多出了分类，如系统解析类、规则解析类等，增加的工厂数量将倍增：

```
针对规则配置的解析器：基于接口IRuleConfigParser
JsonRuleConfigParser
XmlRuleConfigParser
YamlRuleConfigParser
PropertiesRuleConfigParser

针对系统配置的解析器：基于接口ISystemConfigParser
JsonSystemConfigParser
XmlSystemConfigParser
YamlSystemConfigParser
PropertiesSystemConfigParser
```

让一个工厂负责创建多个不同类型的对象（IRuleConfigParser、ISystemConfigParser 等），而不是只创建一种 parser 对象。这样就可以有效地减少工厂类的个数。具体的代码实现如下所示：

```java
public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();
    ISystemConfigParser createSystemParser();
    //此处可以扩展新的parser类型，比如IBizConfigParser
}

public class JsonConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}

public class XmlConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}

// 省略YamlConfigParserFactory和PropertiesConfigParserFactory代码
```

---

这里是我参考 refactoringguru 实现的例子：

refactoringguru 文中示例的是 “跨平台 UI 组件”，目的为适配多平台下 UI 组件的渲染，结构为：

- GUIFactory
    - WinFactory
    - MacFactory
- Button
    - WinButton
    - MacButton
- Checkbox
    - WinCheckbox
    - MacCheckbox
- Application
- ApplicationConfigurator

而我的文件结构为：
- PrintFactory
    - PrinterHTMLFactory
    - PrinterPDFFactory
- MyArrayList
    - MyArrayListHTML
    - MyArrayListPDF
- MyLinkedList
    - MyLinkedListHTML
    - MyLinkedListPDF
- Application
- ApplicationConfigurator

大体思路是：

算法题中有很多数据结构类型，比如数组，链表，树等结构。这边假设要实现一个可视化的数据结构。

上面简单工厂实现了一个简单容器工厂 CollectionFactory，用来创建不同的数据类型。

到了这里，结构有了，想要输出成 html 动态显示，或者输出成 pdf 打印版，于是利用抽象工厂来实现：

```java
/**
 * 【抽象工厂接口】声明了一组能返回不同抽象产品的方法。
 * 这些产品属于同一个系列且在高层主题或概念上具有相关性。
 * 同系列的产品通常能相互搭配使用。
 * 系列产品可有多个变体，但不同变体的产品不能搭配使用。
 */
public interface PrinterFactory {
    MyArrayList createArrayList();
    MyLinkedList createLinkedList();
    //  这里可以扩展类型
}

/**
 * 【具体工厂】可生成属于同一变体的系列产品。
 * 工厂会确保其创建的产品能相互搭配使用。
 * 具体工厂方法签名会返回一个抽象产品，但在方法内部则会对具体产品进行实例化。
 */
public class PrinterHTMLFactory implements PrinterFactory {
    @Override
    public MyArrayList createArrayList() {
        return new MyArrayListHTML();
    }
    @Override
    public MyLinkedList createLinkedList() {
        return new  MyLinkedListHTML();
    }
    //  这里可以扩展类型
}

/**
 * 每个【具体工厂】中都会包含一个相应的产品变体。
 */
public class PrinterPDFFactory implements PrinterFactory {
    @Override
    public MyArrayList createArrayList() {
        return new MyArrayListPDF();
    }
    @Override
    public MyLinkedList createLinkedList() {
        return new MyLinkedListPDF();
    }
    //  这里可以扩展类型
}

/**
 * 系列产品中的特定产品必须有一个基础【产品接口】。所有产品变体都必须实现这个接口。
 */
public interface MyArrayList {
    void print();
}

/**
 * 【具体产品】由相应的具体工厂创建。
 */
public class MyArrayListHTML implements MyArrayList{
    @Override
    public void print() {
        System.out.println("打印ArrayList样式的HTML");
    }
}

/**
 * 这是另一个产品。
 * 所有产品都可以互动，但是只有相同具体变体的产品之间才能够正确地进行交互。
 */
public class MyArrayListPDF implements MyArrayList{
    @Override
    public void print() {
        System.out.println("打印ArrayList样式的PDF");
    }
}

// MyLinkedList 同上，略
// MyLinkedListHTML 同上，略
// MyLinkedListPDF 同上，略

/**
 * 【客户端】代码仅通过抽象类型（PrinterFactory、arrayList 和 LinkedLlist）使用工厂和产品。
 * 这让你无需修改任何工厂或产品子类就能将其传递给客户端代码。
 */
public class Application {

    private PrinterFactory factory;

    private MyArrayList arrayList;
    private MyLinkedList linkedList;

    public Application(PrinterFactory factory) {
        this.factory = factory;
    }

    public void create(){
        arrayList = factory.createArrayList();
        linkedList = factory.createLinkedList();
    }

    public void print() {
        arrayList.print();
        linkedList.print();
    }
}

/**
 * 【程序】会根据当前配置或环境设定选择工厂类型，并在运行时创建工厂（通常在初始化阶段）。
 */
public class ApplicationConfigurator {

    public static void main(String[] args) throws Exception {

        PrinterFactory factory;

        if ("pdf".equals(args[0])) {
            factory = new PrinterPDFFactory();
        } else if ("html".equals(args[0])) {
            factory = new PrinterHTMLFactory();
        } else {
            throw new Exception("未知的输出方式");
        }
        Application application = new Application(factory);
        application.create();
        application.print();
    }

}
```

---
## 实例

以下是来自核心 Java 程序库的一些示例：
```
javax.xml.parsers.DocumentBuilderFactory#newInstance()
javax.xml.transform.TransformerFactory#newInstance()
javax.xml.xpath.XPathFactory#newInstance()
```






