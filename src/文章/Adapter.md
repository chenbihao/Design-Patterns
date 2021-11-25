# 适配器模式（Adapter）

## 介绍

适配器模式是一种结构型设计模式，它能使接口不兼容的对象能够相互合作。

## 适用场景

- 封装有缺陷的接口设计。
- 统一多个类的接口设计。
- 替换依赖的外部系统。
- 兼容隔离老版本接口。
- 适配不同格式的数据。
- ...

## 优缺点

优点：

- 单一职责：将转换代码从业务逻辑中剥离。
- 开闭原则：不修改原有代码的情况下添加适配器。

缺点：

- 代码整体复杂度增加

## 与其他模式的关系

- **桥接模式**通常用于将接口与实现的分离，各自独立。另一方面，**适配器模式**通常在已有程序中使用，让相互不兼容的类能很好地合作。
- **适配器**可以对已有对象的接口进行修改，**装饰**则能在不改变对象接口的前提下强化对象功能并且支持递归组合。
- **适配器**能为被封装对象提供不同的接口，**代理**能为对象提供相同的接口，**装饰**则能为对象提供加强的接口。
- **外观模式**为现有对象定义了一个新接口，**适配器**则会试图运用已有的接口。**适配器**通常只封装一个对象，**外观**通常会作用于整个对象子系统上。

## 实现方式

有两种实现方式，一种是**类适配器**（通过继承），一种是**对象适配器**（通过组合）

1. 创建遵循客户端接口的适配器类。
2. 在适配器类中添加一个成员变量用于保存对于服务对象的引用（对象适配器）或者直接继承目标类（类适配器）。
3. 依次实现适配器类客户端接口的所有方法。适配器会**将实际工作委派给服务对象**，自身只负责接口或数据格式的转换。
4. 客户端必须通过客户端接口使用适配器。这样可以在不影响客户端代码的情况下修改或扩展适配器。

> 如果接口很多，并且适配器与目标接口定义大部分**相同**，推荐使用**类适配器**，复用多，代码量少。
>
> 如果接口很多，并且适配器与目标接口定义大部分**不同**，推荐使用**对象适配器**，组合结构更加灵活。

--- 

## 示例

```java
// 电压
public class Volt {
    private int volts;

    public Volt(int v) {
        this.volts = v;
    }

    public int getVolts() {
        return volts;
    }

    public void setVolts(int volts) {
        this.volts = volts;
    }
}

// 插座（目标类，只提供120V电压）
public class Socket {
    public Volt getVolt() {
        return new Volt(120);
    }
}

// 适配器接口 （也可以是抽象类）（提供转换电压）
public interface SocketAdapter {
    Volt get120Volt();

    Volt get12Volt();

    Volt get3Volt();
}
```

第一种实现方式：**类适配器**：

```java
// 类适配器
public class SocketClassAdapterImpl extends Socket implements SocketAdapter {
    @Override
    public Volt get120Volt() {
        return getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt v = getVolt();
        return convertVolt(v, 10);
    }

    @Override
    public Volt get3Volt() {
        Volt v = getVolt();
        return convertVolt(v, 40);
    }

    private Volt convertVolt(Volt v, int i) {
        return new Volt(v.getVolts() / i);
    }
}
```

第二种实现方式：**对象适配器**：

```java
// 对象适配器，利用组合的方式
public class SocketObjectAdapterImpl implements SocketAdapter {

    // 通常情况下会通过构造函数对该成员变量进行初始化，但有时在调用其方法时将该变量传递给适配器会更方便。 
    private Socket sock = new Socket();

    @Override
    public Volt get120Volt() {
        return sock.getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt v = sock.getVolt();
        return convertVolt(v, 10);
    }

    @Override
    public Volt get3Volt() {
        Volt v = sock.getVolt();
        return convertVolt(v, 40);
    }

    private Volt convertVolt(Volt v, int i) {
        return new Volt(v.getVolts() / i);
    }
}
```

测试代码

```java
public class AdapterTest {

    @Test
    public void test() {

        SocketAdapter objectAdapter = new SocketObjectAdapterImpl();
        Assertions.assertEquals(3, objectAdapter.get3Volt().getVolts());
        Assertions.assertEquals(12, objectAdapter.get12Volt().getVolts());
        Assertions.assertEquals(120, objectAdapter.get120Volt().getVolts());

        SocketAdapter classAdapter = new SocketClassAdapterImpl();
        Assertions.assertEquals(3, classAdapter.get3Volt().getVolts());
        Assertions.assertEquals(12, classAdapter.get12Volt().getVolts());
        Assertions.assertEquals(120, classAdapter.get120Volt().getVolts());
    }
}
```

--- 

## 实例

### JDK

#### Arrays

java.util.Arrays#asList()

```java
public class Arrays {
    // ...

    /**
     * 返回指定数组支持的固定大小列表。（对返回列表的更改“直写”到数组。）
     * 此方法与collection.toArray相结合，充当基于数组和基于集合的API之间的桥梁。
     */
    public static <T> List<T> asList(T... a) {
        return new ArrayList<>(a);
    }

    // ...
}
```

#### Collections

java.util.Collections#list()、#enumeration()

```java
public class Collections {
    // ...

    public static <T> ArrayList<T> list(Enumeration<T> e) {
        ArrayList<T> l = new ArrayList<>();
        while (e.hasMoreElements())
            l.add(e.nextElement());
        return l;
    }

    public static <T> Enumeration<T> enumeration(final Collection<T> c) {
        return new Enumeration<T>() {
            private final Iterator<T> i = c.iterator();

            public boolean hasMoreElements() {
                return i.hasNext();
            }

            public T nextElement() {
                return i.next();
            }
        };
    }

    // ...
}
```

### Slf4j

slf4j统一的接口定义

```java
package org.slf4j;
public interface Logger {
    public boolean isTraceEnabled();

    public void trace(String msg);

    public void trace(String format, Object arg);

    public void trace(String format, Object arg1, Object arg2);

    public void trace(String format, Object[] argArray);

    public void trace(String msg, Throwable t);

    public boolean isDebugEnabled();

    public void debug(String msg);

    public void debug(String format, Object arg);

    public void debug(String format, Object arg1, Object arg2)

    public void debug(String format, Object[] argArray)

    public void debug(String msg, Throwable t);

    //...省略info、warn、error等一堆接口
}
```
log4j 适配器
```java
// log4j日志框架的适配器
// Log4jLoggerAdapter实现了LocationAwareLogger接口，
// 其中LocationAwareLogger继承自Logger接口，
// 也就相当于Log4jLoggerAdapter实现了Logger接口。
package org.slf4j.impl;
public final class Log4jLoggerAdapter extends MarkerIgnoringBase
        implements LocationAwareLogger, Serializable {
    final transient org.apache.log4j.Logger logger; // log4j

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void debug(String msg) {
        logger.log(FQCN, Level.DEBUG, msg, null);
    }

    public void debug(String format, Object arg) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    public void debug(String format, Object arg1, Object arg2) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    public void debug(String format, Object[] argArray) {
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    public void debug(String msg, Throwable t) {
        logger.log(FQCN, Level.DEBUG, msg, t);
    }
    //...省略一堆接口的实现...
}
```


