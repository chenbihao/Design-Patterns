# [工厂模式（Factory）：](Factory.md)
将创建对象移交给工厂来处理

> 大部分工厂类都是以“Factory”这个单词结尾的，但也不是必须的，比如 Java 中的 DateFormat、Calender。
>
> 除此之外，工厂类中创建对象的方法一般都是 create 开头，比如代码中的 createParser()，
>
> 但有的也命名为 getInstance()、createInstance()、newInstance()，

有的甚至命名为 valueOf()（比如 Java String 类的 valueOf() 函数）等等。
---

## [简单工厂（Simple Factory）](Factory-SimpleFactory.md)
↑ 点击查看详细说明与示例 ↑

### 介绍
简单工厂模式描述了一个类，它拥有一个包含大量条件语句的构建方法，可根据方法的参数来选择对何种产品进行初始化并将其返回。

---

## [工厂方法（Factory Method）](Factory-FactoryMethod.md)
↑ 点击查看详细说明与示例 ↑

### 介绍
工厂方法在父类中提供一个创建对象的方法，允许子类决定实例化对象的类型。



---
## [抽象工厂（Abstract Factory）](Factory-AbstractFactory.md) 
↑ 点击查看详细说明与示例 ↑

### 介绍
抽象工厂能创建一系列相关或相互依赖的对象，而无需指定其具体类。

---


# 差别总结







---

# 实例







