# 单例模式（Singleton Design Pattern）

## 介绍

单例模式：一个类只允许创建一个对象（或者实例），那这个类就是一个单例类，这种设计模式就叫作单例设计模式，简称单例模式。


## 适用场景

- 保证一个类只有一个实例
- 为该实例提供一个全局访问节点


## 优缺点

优点：
- 可以保证一个类只有一个实例
- 获得指向该实例的全局访问节点

缺点：
- 对 OOP 特性支持不友好
- 隐藏了类之间的依赖关系
- 扩展性、可测试性不友好
- 不支持有参数的构造函数


## 实现方式

1. 将默认构造函数设为私有， 防止其他对象使用单例类的 new 运算符。
2. 新建一个静态构建方法作为构造函数。 该函数调用私有构造函数来创建对象， 并将其保存在一个静态成员变量中。 此后所有对于该函数的调用都将返回这一缓存对象。


## 示例


### 饿汉式

```java
public class HungrySingleton {
  /**
   * 线程安全，但不支持延迟加载
   */
  private static HungrySingleton instance = new HungrySingleton();

  /**
   * 必须为 private 防止不小心使用 new 关键字创建
   */
  private HungrySingleton() {
    System.out.println("初始化..");
  }

  public static HungrySingleton getInstance() {
    return instance;
  }
}
```

### 懒汉式

```java
public class LazySingleton {
  private static LazySingleton instance;

  private LazySingleton() {
    System.out.println("初始化..");
  }

  /**
   * 必须要有 synchronized 来确保线程安全，并发度为1，性能弱
   */
  public static synchronized LazySingleton getInstance() {
    if (instance == null) {
      instance = new LazySingleton();
    }
    return instance;
  }
}
```

### 双重检测

```java
public class DoubleLockingSingleton {
    /**
     * 此处加 volatile 是为了禁止指令重排（通过加内存屏障来禁止）
     * 高版本 Java 可以不需要加 volatile 关键字，因为 JDK 内部已经把 new 操作和初始化操作定义为原子操作（存疑）
     */
    private static volatile DoubleLockingSingleton instance;

    private DoubleLockingSingleton() {
        System.out.println("初始化..");
    }

    /**
     * 这里相比懒汉式，去掉了 synchronized 关键字，因为进行了两次 (instance == null)的判断，所以叫双重校验
     */
    public static DoubleLockingSingleton getInstance() {
        if (instance == null) {
            // 此处为类级别的锁
            synchronized (DoubleLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleLockingSingleton();
                }
            }
        }
        return instance;
    }
}
```



### 静态内部类

```java
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
        System.out.println("初始化..");
    }

    /**
     * 类加载时，不会创建静态内部类实例，当调用 getInstance() 时才会创建 instance
     * 相当于让 JVM 来确保 instance 的线程安全与唯一性
     */
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
```



### 枚举
```java
public enum EnumSingleton {
    /**
     * Java规范字规定，每个枚举类型及其定义的枚举变量在JVM中都是唯一的，保证了实例创建的线程安全性和实例的唯一性
     */
    INSTANCE;

    public static void doSomething() {
        //do something
    }
}
```

### 其他

实现单例的方式有很多，感兴趣的可以自己找下资料，例如以下的实现方式：

- 静态初始化
- 反射破坏
- 序列化


### 线程唯一

```java
public class ThreadSingleton {

    private static final ConcurrentHashMap<Long, ThreadSingleton> instances = new ConcurrentHashMap<>();

    private ThreadSingleton() {}

    /**
     * 前面的单例实例都是属于 “进程唯一” ，这个版本属于 “线程唯一”
     * 相同线程只有一个对象，而不同线程之间对象有多个
     * 相当于 ThreadLocal 工具类
     */
    public static ThreadSingleton getInstance() {
        Long currentThreadId = Thread.currentThread().getId();
        instances.putIfAbsent(currentThreadId, new ThreadSingleton());
        return instances.get(currentThreadId);
    }
}
```

### 集群唯一

前面的单例实例都是属于 “进程唯一”，而集群单例也就是 “进程间也唯一”

具体实现方法为：

- 把这个单例对象序列化并存储到外部共享存储区（比如文件、缓存等）。

- 进程在使用这个单例对象的时候，需要先从外部共享存储区中将它读取到内存，并反序列化成对象，然后再使用，使用完成之后还需要再存储回外部共享存储区。

- 为了保证任何时刻，在进程间都只有一份对象存在，一个进程在获取到对象之后，需要对对象加锁，避免其他进程再将其获取。在进程使用完这个对象之后，还需要显式地将对象从内存中删除，并且释放对对象的加锁。

### 多例

```java
public class MultipleSingleton {

    private long id;
    private String msg;

    private static final int COUNT = 3;
    private static final Map<Long, MultipleSingleton> instances = new HashMap<>();

    private MultipleSingleton(long id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    static {
        instances.put(1L, new MultipleSingleton(1L, "多例-1"));
        instances.put(2L, new MultipleSingleton(2L, "多例-2"));
        instances.put(3L, new MultipleSingleton(3L, "多例-3"));
    }

    public static MultipleSingleton getInstance(long id) {
        return instances.get(id);
    }

    public static MultipleSingleton getRandomInstance() {
        SecureRandom r = new SecureRandom();
        int i = r.nextInt(COUNT) + 1;
        return instances.get(i);
    }
}
```

## 实例

### JDK

java.lang.Runtime#getRuntime()

```java
     public class Runtime {
        private static final Runtime currentRuntime = new Runtime();

        public static Runtime getRuntime() {
            return currentRuntime;
        }

        /** Don't let anyone else instantiate this class */
        private Runtime() {}
          //......
     }
```



### Mybatis

ErrorContext#instance()

这里的 ThreadLocal 相当于【线程唯一单例】里的 ConcurrentHashMap

```java
    public class ErrorContext {
        private static final String LINE_SEPARATOR = System.getProperty("line.separator","\n");
        private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<ErrorContext>();

        private ErrorContext stored;
        private String resource;
        private String activity;
        private String object;
        private String message;
        private String sql;
        private Throwable cause;

        private ErrorContext() {
        }

        public static ErrorContext instance() {
            ErrorContext context = LOCAL.get();
            if (context == null) {
                context = new ErrorContext();
                LOCAL.set(context);
            }
            return context;
        }
    }
```