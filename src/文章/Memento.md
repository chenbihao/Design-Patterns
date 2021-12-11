# 备忘录模式（Memento）

## 介绍

**备忘录模式**是一种行为设计模式，

它允许在不暴露对象实现细节（封装原则）的情况下保存和恢复对象之前的状态。

## 适用场景

- 需要创建**对象状态快照**来恢复其之前的状态时。
- 直接访问对象的成员变量、获取器或设置器会导致**封装被破坏**时。
- ...


## 优缺点

优点：

- 你可以在不破坏对象封装情况的前提下创建对象状态快照。
- 你可以通过让负责人维护原发器状态历史记录来简化原发器代码。

缺点：

- 频繁的创建快照会消耗大量内存。
- 大部分动态编程语言无法确保备忘录中的状态不被修改。
- 负责人必须完整跟踪原发器的生命周期才能销毁弃用的备忘录。


## 与其他模式的关系

- 有时候**原型模式**可以作为**备忘录**的一个简化版本。
- 可以同时使用**备忘录**和**迭代器模式**来获取当前迭代器的状态，并且在需要的时候进行回滚。


## 实现方式

1. 创建备忘录类，将备忘录类设为不可变。
2. 在原发器中添加创建备忘录与恢复自身状态的方法。
3. 创建负责人类，负责人需要知道何时向原发器请求新的备忘录、如何存储备忘录以及何时使用特定备忘录来对原发器进行恢复。


可以灵活的各种实现，只要不破坏原有封装即可，如：
- 基于嵌套类的实现
- 基于中间接口的实现
- 封装更加严格的实现

也可以针对备忘录进行优化，如利用**“增量备份”**的方式来节省内存消耗。

> 实现时要注意深度拷贝的问题。

--- 

## 示例

原发器 （Originator） 类
```java
public class StringUtil {

    private StringBuilder text = new StringBuilder();

    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);
    }

    /**
     * 生成自身状态的快照
     */
    public Snapshot createSnapshot() {
        return new Snapshot(text.toString());
    }

    /**
     * 通过快照恢复自身状态
     */
    public void restoreSnapshot(Snapshot snapshot) {
        this.text.replace(0, this.text.length(), snapshot.getText());
    }
}
```

备忘录 （Memento）
```java
public class Snapshot {

    private String text;

    /**
     * 备忘录（Memento）是原发器状态快照的值对象（value object）。
     * 通常做法是将备忘录设为不可变的，并通过构造函数一次性传递数据。
     */
    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
```

负责人 （Caretaker）
```java
public class SnapshotHolder {

    /**
     * 也可以叫做 history ，负责人通过保存备忘录栈来记录原发器的历史状态。
     */
    private Stack<Snapshot> snapshots = new Stack<>();

    /**
     * 负责人通过保存备忘录栈来记录原发器的历史状态。
     */
    public void save(StringUtil stringUtil){
        snapshots.push(stringUtil.createSnapshot());
    }

    /**
     * 当原发器需要回溯历史状态时，负责人将从栈中获取最顶部的备忘录，并将其传递给原发器的恢复方法。
     */
    public void undo(StringUtil stringUtil){
        stringUtil.restoreSnapshot(snapshots.pop());
    }
}
```

测试代码

```java
public class MementoTest {
    @Test
    public void test() {
        StringUtil stringUtil = new StringUtil();
        SnapshotHolder snapshotHolder = new SnapshotHolder();

        stringUtil.append("张三");
        snapshotHolder.save(stringUtil);
        Assertions.assertEquals("张三",stringUtil.getText());

        stringUtil.append("是法外狂徒");
        Assertions.assertEquals("张三是法外狂徒",stringUtil.getText());
        // 发现输错了 撤销一下
        snapshotHolder.undo(stringUtil);
        Assertions.assertEquals("张三",stringUtil.getText());

        stringUtil.append("李四");
        snapshotHolder.save(stringUtil);
        Assertions.assertEquals("张三李四",stringUtil.getText());
    }
}
```

--- 



