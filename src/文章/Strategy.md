# 策略模式（Strategy）

## 介绍

**策略模式**是一种行为设计模式。

策略模式定义一族算法类，将每个算法分别封装起来，让它们可以**互相替换**。使算法的变化独立于使用它们的客户端。

## 适用场景

- 想使用对象中各种不同的**算法变体**，并希望能在运行时**切换算法**。
- 将类的业务逻辑与其算法**实现隔离**开。
- 有许多仅在执行某些行为时略有不同的相似类。
- 当类中使用了复杂条件运算符以在同一算法的**不同变体**中切换(冗长的分支判断)。
- ...

## 优缺点

优点：

- 可以在运行时切换对象内的算法。
- 可以将算法的实现和使用算法的代码隔离开来。
- 可以使用组合来代替继承。
- 开闭原则：无须修改上下文即可引入新策略。

缺点：

- 复杂度增加。
- 客户端必须知晓策略间的不同。

## 与其他模式的关系

- **装饰模式**可让你更改对象的外表，**策略模式**则让你能够改变其本质。
- 模板方法模式与策略模式：
    - **模板方法**基于继承机制：它允许通过扩展子类中的部分内容来改变部分算法。
    - **策略**基于组合机制：可以通过对相应行为提供不同的策略来改变对象的部分行为。
    - **模板方法**在类层次上运作，因此它是静态的。
    - **策略**在对象层次上运作，因此允许在运行时切换行为。
- **观察者模式**是解耦观察者和被观察者。**策略模式**跟两者类似，它解耦的是策略的定义、创建、使用这三部分。

## 实现方式

1. 声明该算法所有变体的通用**策略接口**。
2. 将算法逐一抽取到各自的类中，它们都必须**实现策略接口**。
3. 在上下文类中添加一个成员变量用于保存对于策略对象的引用。然后提供设置器以修改该成员变量。
   - 上下文仅可通过策略接口同策略对象进行交互。
   - 如有需要还可定义一个接口来让策略访问其数据。

> Java 8 开始支持 lambda 方法， 它可作为一种替代策略模式的简单方式。

--- 

## 示例

策略接口

```java
public interface Strategy {
    // 声明该算法所有变体的通用策略接口。
    void evict();
}
```

策略实现

```java
public class StrategyFIFO implements Strategy {
    // 将算法逐一抽取到各自的类中，它们都必须实现策略接口。
    @Override
    public void evict() {
        System.out.println("使用【先进先出】策略进行丢弃");
    }
}

public class StrategyLRU implements Strategy {
    @Override
    public void evict() {
        System.out.println("使用【最近最久未使用】策略进行丢弃");
    }
}

public class StrategyLFU implements Strategy {
    @Override
    public void evict() {
        System.out.println("使用【最近最少使用】策略进行丢弃");
    }
}
```

Context 上下文

```java
public class Cache {

    private Map cacheData = new HashMap<>();

    // 在上下文类中添加一个成员变量用于保存对于策略对象的引用。
    private Strategy evictionStrategy;

    // 提供设置器以修改该成员变量。
    public void setEvictionStrategy(Strategy evictionStrategy) {
        this.evictionStrategy = evictionStrategy;
    }

    // 通过策略接口同策略对象进行交互
    public void evict() {
        evictionStrategy.evict();
    }

    public void add(String value) {
        // ...
    }
    public String get() {
        // ...
        return null;
    }
}
```

测试代码

```java
public class StrategyTest {
    @Test
    public void test() {
        Cache cache = new Cache();
        cache.add("添加");
        cache.add("缓存");
        cache.add("数据");

        cache.setEvictionStrategy(new StrategyFIFO());
        cache.get();
        cache.evict();
        cache.evict();

        cache.setEvictionStrategy(new StrategyLRU());
        cache.evict();

        cache.setEvictionStrategy(new StrategyLFU());
        cache.evict();
    }
}
```

--- 

## 实例

### JDK

#### Comparator

java.util.Comparator 是策略接口类

Comparator.compare() 是策略方法

Collections.sort(List list, Comparator c) 调用自定义策略



