# 状态模式（State）

## 介绍

**状态模式**是一种行为设计模式

状态模式能在一个对象的内部**状态变化**时改变其行为，使其看上去就像改变了自身所属的类一样。

与**有限状态机**的概念紧密相关。

状态机由 3 个部分组成：**状态**、**事件**（转移条件）、**动作**。事件触发状态的转移及动作的执行。

## 适用场景

- 对象需要**根据当前状态进行不同行为**，同时状态的数量非常多且与状态相关的代码会频繁变更。
- 某个类需要根据成员变量的当前值**改变自身行为**，从而需要使用大量的条件语句。
- 当相似状态和基于条件的**状态机转换**中存在许多重复代码。
- ...

## 优缺点

优点：

- 开闭原则。
- 单一职责原则。
- 通过消除臃肿的状态机条件语句简化上下文代码。

缺点：

- 复杂度增加：如果状态机只有很少的几个状态，使用状态模式会很复杂。

## 与其他模式的关系

- **状态**可被视为**策略**的扩展
    - **策略模式**中的策略则几乎完全不知道其他策略的存在。
    - **状态模式**中，特定状态知道其他所有状态的存在，且能触发从一个状态到另一个状态的转换。

## 实现方式

1. **声明状态接口**。
2. 为每个实际状态创建一个**继承状态接口**的类。
3. 在上下文类中添加一个状态接口类型的**引用成员变量**，以及一个用于修改该成员变量值的**公有设置器**。
4. 为切换上下文状态，你需要创建某个状态类实例并将其**传递给上下文**。
    - 可以在上下文、各种状态或客户端中完成这项工作。无论在何处完成这项工作，该类都将依赖于其所实例化的具体类。
    - 如果状态类中不包含成员变量，则可以使用单例模式来配合使用。

> 其他实现方法：
>
> **分支逻辑法**：
> 直接利用 if 逻辑或者 switch 分支逻辑，直接写状态转移的代码。**适合简单直接**的状态机。
>
> **查表法**：
> 通过二维数组来表示状态转移图，能极大地提高代码的可读性和可维护性。**适合状态较多、转移复杂**的状态机。

--- 

## 示例

状态接口

```java
public abstract class State {

    Kettle kettle;

    public State(Kettle kettle) {
        this.kettle = kettle;
    }

    // 动作

    // 加水
    public abstract String addWater();
    // 煮水
    public abstract String boilWater();
    // 倒水
    public abstract String pourWater();
}
```

状态实现

```java
public class EmptyState extends State {

    public EmptyState(Kettle kettle) {
        super(kettle);
    }

    @Override
    public String addWater() {
        // 切换上下文状态
        kettle.changeState(new ColdState(kettle));
        return "加水成功";
    }
    @Override
    public String boilWater() {
        return "没水...";
    }
    @Override
    public String pourWater() {
        return "没水...";
    }

    @Override
    public String toString() {
        return "空的状态";
    }
}

public class ColdState extends State {

    public ColdState(Kettle kettle) {
        super(kettle);
    }

    @Override
    public String addWater() {
        return "有水了，别加了";
    }
    @Override
    public String boilWater() {
        // 切换上下文状态
        kettle.changeState(new BoilingState(kettle));
        return "煮水成功";
    }
    @Override
    public String pourWater() {
        // 切换上下文状态
        kettle.changeState(new EmptyState(kettle));
        return "倒水成功（冷水）";
    }

    @Override
    public String toString() {
        return "冷水状态";
    }
}

public class BoilingState extends State {

    public BoilingState(Kettle kettle) {
        super(kettle);
    }

    @Override
    public String addWater() {
        return "有水了，别加了";
    }
    @Override
    public String boilWater() {
        return "保温中...";
    }
    @Override
    public String pourWater() {
        // 切换上下文状态
        kettle.changeState(new EmptyState(kettle));
        return "倒水成功（开水）";
    }

    @Override
    public String toString() {
        return "热水状态";
    }
}
```

上下文（热水壶类）

```java
public class Kettle {

    // 保存一个指向表示当前状态的状态对象的引用
    private State state;

    // ... 这里也可以存放一些其他数据，如水量水温等。

    public Kettle() {
        this.state = new EmptyState(this);
    }
    
    public State getState() {
        return state;
    }

    // 公有设置器
    public void changeState(State state) {
        this.state = state;
    }

    // 将执行工作委派给当前状态 x3
    public String addWater() {
        return state.addWater();
    }
    public String boilWater() {
        return state.boilWater();
    }
    public String pourWater() {
        return state.pourWater();
    }
}
```

测试代码

```java
public class StateTest {
    @Test
    public void test() {

        Kettle kettle = new Kettle();

        Assertions.assertEquals("空的状态", kettle.getState().toString());

        Assertions.assertEquals("没水...", kettle.pourWater());
        Assertions.assertEquals("加水成功", kettle.addWater());

        Assertions.assertEquals("冷水状态", kettle.getState().toString());

        Assertions.assertEquals("有水了，别加了", kettle.addWater());
        Assertions.assertEquals("煮水成功", kettle.boilWater());

        Assertions.assertEquals("热水状态", kettle.getState().toString());

        Assertions.assertEquals("有水了，别加了", kettle.addWater());
        Assertions.assertEquals("保温中...", kettle.boilWater());
        Assertions.assertEquals("倒水成功（开水）", kettle.pourWater());

        Assertions.assertEquals("空的状态", kettle.getState().toString());
    }
}
```


