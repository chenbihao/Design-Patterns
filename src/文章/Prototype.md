# 原型模式（Prototype）

## 介绍

原型模式是一种**创建型设计模式**， 使你能够复制已有对象， 而又无需使代码依赖它们所属的类。

如果对象的**创建成本比较大**，而同一个类的不同对象之间差别不大（大部分**字段都相同**），
在这种情况下，我们可以利用对已有对象（原型）进行**复制**（或者叫拷贝）的方式来创建新对象，以达到**节省创建时间**的目的。

## 适用场景

- 对象**创建成本较大**（比如需要计算or排序等），且同一个类的不同对象直接差别不大（大部分字段相同）时。
- 需要**复制对象**，同时也希望代码能独立于对象所属的具体类时。
- 子类直接的区别仅在于其初始化的方式时，可以用该模式减少子类的数量（别人创建这些子类的目的可能是为了创建特定类型的对象）。
- ...

## 实现方式

1. 原型类必须添加一个**以该类对象为参数的构造函数**。
2. **声明并实现 clone 方法**， clone 方法一般使用 new 关键字调用第一步的构造函数。
3. 还可以新建一个工厂类来当注册表用。

两种具体实现方式：
- **深拷贝**（完全独立的新对象）
- **浅拷贝**（有数据被修改的风险）

## 优缺点

优点：

- 克隆对象，代码不耦合。
- 可以克隆预生成对象，避免反复初始化。
- 更方便的生成复杂对象。
- 可以用继承以外的方式来处理复杂对象的不同配置。

缺点：

- 克隆包含循环引用的复杂对象可能会非常麻烦。

## 与其他模式的关系

- 在许多设计工作的初期都会使用**工厂方法模式**（较为简单，而且可以更方便地通过子类进行定制），随后演化为使用**抽象工厂模式**、**原型模式**或**生成器模式**（更灵活但更加复杂）。
- **抽象工厂模式**通常基于一组工厂方法，但也可以使用**原型模式**来生成这些类的方法。
- **原型**并不基于继承，但原型需要对被复制对象进行复杂的初始化。**工厂方法**基于继承，但是它不需要初始化步骤。
- **抽象工厂**、**生成器**和**原型**都可以用**单例模式**来实现。

--- 

## 示例

### 自己实现的原型类

```java
public class SimplePrototype {

    private String a;
    private String b;
    // 假设这里N多字段以及有需要计算的字段

    // ... 省略 get、set

    public SimplePrototype() {
    }

    /**
     * 1. 以该类对象为参数的构造函数
     */
    public SimplePrototype(SimplePrototype self) {
        if (self != null) {
            this.a = self.a;
            this.b = self.b;
        }
    }

    /**
     * 2. 克隆方法
     * 直接返回 new 调用参数为 self 的构造函数
     */
    @Override
    public SimplePrototype clone() {
        return new SimplePrototype(this);
    }

    // 重写 equals 和 hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePrototype that = (SimplePrototype) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
```

### 直接实现 java.lang.Cloneable 接口

```java
public class ObjectPrototype implements Cloneable{

    private String a;
    private String b;
    // 假设这里N多字段以及有需要计算的字段

    // ... 省略 get、set

    /**
     * 重写 clone
     * 默认被 protected 修饰，改为 public
     */
    @Override
    public ObjectPrototype clone() throws CloneNotSupportedException {
        return (ObjectPrototype)super.clone();
    }

    // 重写 equals 和 hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectPrototype that = (ObjectPrototype) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
```

测试代码

```java
public class PrototypeTest {

    @Test
    public void testPrototype() {

        // 自己实现的原型类
        SimplePrototype simplePrototype = new SimplePrototype();
        simplePrototype.setA("asdasdasdasdasdasd");
        simplePrototype.setB("123123124134123123");

        SimplePrototype clonePrototype = simplePrototype.clone();
        Assertions.assertTrue(simplePrototype.equals(clonePrototype));

        clonePrototype.setB("asd");
        Assertions.assertFalse(simplePrototype.equals(clonePrototype));

        // 实现 Cloneable 接口的原型对象
        ObjectPrototype objectPrototype = new ObjectPrototype();
        objectPrototype.setA("asdasdasdasdasdasd");
        objectPrototype.setB("123123124134123123");

        ObjectPrototype clone=null;
        try {
            clone = objectPrototype.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(objectPrototype.equals(clone));

    }
}
```

### 王争老师举的例子

需求：获取10W条数据，并且需要对数据进行计算与排序。每隔一段时间需要刷新。

这时便可以使用原型模式，直接克隆这个List，
然后只从数据库获取更新过的数据，在克隆的List上增量操作，
最后用克隆List替换掉原有List。

这样既保证了数据一直可用，又避免了中间状态的存在。也减少了创建对象时的成本。

--- 

## 实例

### JDK

#### Object类

java.lang.Object#clone()

```java
public class Object {

    private static native void registerNatives();
    static {
        registerNatives();
    }

    @HotSpotIntrinsicCandidate
    public Object() {}

    @HotSpotIntrinsicCandidate
    public final native Class<?> getClass();

    @HotSpotIntrinsicCandidate
    public native int hashCode();

    public boolean equals(Object obj) {
        return (this == obj);
    }

    /**
     * Object 类的 clone()方法是一个 native 方法，
     * native 方法的效率一般来说都是远高于 Java 中的非 native 方法。
     */
    @HotSpotIntrinsicCandidate
    protected native Object clone() throws CloneNotSupportedException;

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    // ...
}
```

