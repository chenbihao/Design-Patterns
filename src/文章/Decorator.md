# 装饰器模式（Decorator）

## 介绍

**装饰模式**是一种**结构型设计模式**，允许通过将对象放入包含行为的特殊封装对象中来为原对象绑定新的行为。

装饰器模式主要解决继承关系过于复杂的问题，通过组合来替代继承。它主要的作用是**给原始类添加增强功能**。

## 适用场景

- 无需修改代码的情况下给原始类添加增强功能。
- 类继承结构复杂、组合爆炸的情况下。
- 可以对原始类嵌套使用多个装饰器。

## 优缺点

优点：

- 无需创建新子类即可扩展对象的行为。
- 可以在运行时添加或删除对象的功能。
- 可以用多个装饰封装对象来组合几种行为。
- 单一职责原则。可以将实现了许多不同行为的一个大类拆分为多个较小的类。

缺点：

- 在封装器栈中删除特定封装器比较困难。
- 实现行为不受装饰栈顺序影响的装饰比较困难。
- 各层的初始化配置代码看上去可能会很糟糕。

## 与其他模式的关系

- **装饰模式**和**代理模式**有着相似的结构，但是其意图却非常不同。这两个模式的构建都基于组合原则，也就是说一个对象应该将部分工作委派给另一个对象。两者之间的不同之处在于**代理通常自行管理其服务对象的生命周期**，而**
  装饰的生成则总是由客户端进行控制**。

## 实现方式

1. 创建一个组件接口并在其中声明通用方法。
3. 创建一个具体组件类，并定义其基础行为。
4. 创建装饰基类，使用一个成员变量存储指向被封装对象的引用。该成员变量必须被声明为组件接口类型，从而能在运行时连接具体组件和装饰。装饰基类必须将所有工作委派给被封装的对象。
5. 装饰器实现类实现组件接口。

--- 

## 示例

装饰器

```java
// 代理模式的代码结构(下面的接口也可以替换成抽象类)
public interface IA {
    void f();
}

public class A implements IA {
    public void f() { 
        //... 
    }
}

public class AProxy implements IA {
    private IA a;

    public AProxy(IA a) {
        this.a = a;
    }

    public void f() {
        // 新添加的代理逻辑
        a.f();
        // 新添加的代理逻辑
    }
}

// 装饰器模式的代码结构(下面的接口也可以替换成抽象类)
public interface IA {
    void f();
}

public class A implements IA {
    public void f() { //...
    }
}

public class ADecorator implements IA {
    private IA a;

    public ADecorator(IA a) {
        this.a = a;
    }

    public void f() {
        // 功能增强代码
        a.f();
        // 功能增强代码
    }
}
```

具体例子：披萨

```java
// 披萨基类
public interface Pizza {
    int getPrice();
}

// 厚饼比萨（具体组件类）
public class PizzaThick implements Pizza {
    @Override
    public int getPrice() {
        return 20;
    }
}

// 薄饼比萨（具体组件类）
public class PizzaThin implements Pizza {
    @Override
    public int getPrice() {
        return 15;
    }
}

// 加料 （装饰器基类）
public class Topping implements Pizza {
    // 子装饰器类应该可以访问组件变量 设置为 protected 
    protected Pizza pizza;

    public Topping(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public int getPrice() {
        // 委派给被封装的对象
        return this.pizza.getPrice();
    }
}

// 芝士盖头（装饰器实现类）
public class ToppingCheese extends Topping {
    public ToppingCheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public int getPrice() {
        // 委派给被封装的对象 具体增强围绕着这里展开
        int price = this.pizza.getPrice();
        return price + 5;
    }
}

// 水果盖头（装饰器实现类）
public class ToppingFruits extends Topping {
    public ToppingFruits(Pizza pizza) {
        super(pizza);
    }

    @Override
    public int getPrice() {
        // 委派给被封装的对象 具体增强围绕着这里展开
        int price = this.pizza.getPrice();
        return price + 7;
    }
}
```

测试代码

```java
public class DecoratorTest {

    @Test
    public void test() {

        // 薄饼披萨15 + 芝士5 + 番茄7
        PizzaThin pizzaThin = new PizzaThin();
        Pizza finalPizza = new ToppingCheese(new ToppingFruits(pizzaThin));
        Assertions.assertEquals(27, finalPizza.getPrice());

        // 厚饼披萨20 + 芝士5
        PizzaThick pizzaThick = new PizzaThick();
        Pizza finalPizza2 = new ToppingCheese(pizzaThick);
        Assertions.assertEquals(25, finalPizza2.getPrice());

    }
}
```

--- 

## 实例

### JDK

#### Java IO 类库

java.io.*

```java
public abstract class InputStream {
    //...
    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        //...
    }

    public long skip(long n) throws IOException {
        //...
    }

    public int available() throws IOException {
        return 0;
    }

    public void close() throws IOException {
    }

    public synchronized void mark(int readlimit) {
    }

    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public boolean markSupported() {
        return false;
    }
}

public class BufferedInputStream extends InputStream {
    protected volatile InputStream in;

    protected BufferedInputStream(InputStream in) {
        this.in = in;
    }

    //...实现基于缓存的读数据接口...  
}

public class DataInputStream extends InputStream {
    protected volatile InputStream in;

    protected DataInputStream(InputStream in) {
        this.in = in;
    }

    //...实现读取基本类型数据的接口
}
```

#### Java IO 类库

java.util.Collections#checkedXXX()、#synchronizedXXX()、#unmodifiableXXX()

```java

public class Collections {
  private Collections() {}
    
  public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c) {
    return new UnmodifiableCollection<>(c);
  }

  static class UnmodifiableCollection<E> implements Collection<E>,   Serializable {
    private static final long serialVersionUID = 1820017752578914078L;
    final Collection<? extends E> c;

    UnmodifiableCollection(Collection<? extends E> c) {
      if (c==null)
        throw new NullPointerException();
      this.c = c;
    }

    public int size()                   {return c.size();}
    public boolean isEmpty()            {return c.isEmpty();}
    public boolean contains(Object o)   {return c.contains(o);}
    public Object[] toArray()           {return c.toArray();}
    public <T> T[] toArray(T[] a)       {return c.toArray(a);}
    public String toString()            {return c.toString();}

    public Iterator<E> iterator() {
      return new Iterator<E>() {
        private final Iterator<? extends E> i = c.iterator();

        public boolean hasNext() {return i.hasNext();}
        public E next()          {return i.next();}
        public void remove() {
          throw new UnsupportedOperationException();
        }
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
          // Use backing collection version
          i.forEachRemaining(action);
        }
      };
    }

    public boolean add(E e) {
      throw new UnsupportedOperationException();
    }
    public boolean remove(Object o) {
      throw new UnsupportedOperationException();
    }
    public boolean containsAll(Collection<?> coll) {
      return c.containsAll(coll);
    }
    public boolean addAll(Collection<? extends E> coll) {
      throw new UnsupportedOperationException();
    }
    public boolean removeAll(Collection<?> coll) {
      throw new UnsupportedOperationException();
    }
    public boolean retainAll(Collection<?> coll) {
      throw new UnsupportedOperationException();
    }
    public void clear() {
      throw new UnsupportedOperationException();
    }

    // Override default methods in Collection
    @Override
    public void forEach(Consumer<? super E> action) {
      c.forEach(action);
    }
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
      throw new UnsupportedOperationException();
    }
    @SuppressWarnings("unchecked")
    @Override
    public Spliterator<E> spliterator() {
      return (Spliterator<E>)c.spliterator();
    }
    @SuppressWarnings("unchecked")
    @Override
    public Stream<E> stream() {
      return (Stream<E>)c.stream();
    }
    @SuppressWarnings("unchecked")
    @Override
    public Stream<E> parallelStream() {
      return (Stream<E>)c.parallelStream();
    }
  }
}
```

### Spring

#### TransactionAwareCacheDecorator

```java

public class TransactionAwareCacheDecorator implements Cache {
  private final Cache targetCache;

  public TransactionAwareCacheDecorator(Cache targetCache) {
    Assert.notNull(targetCache, "Target Cache must not be null");
    this.targetCache = targetCache;
  }

  public Cache getTargetCache() {
    return this.targetCache;
  }

  public String getName() {
    return this.targetCache.getName();
  }

  public Object getNativeCache() {
    return this.targetCache.getNativeCache();
  }

  public ValueWrapper get(Object key) {
    return this.targetCache.get(key);
  }

  public <T> T get(Object key, Class<T> type) {
    return this.targetCache.get(key, type);
  }

  public <T> T get(Object key, Callable<T> valueLoader) {
    return this.targetCache.get(key, valueLoader);
  }

  public void put(final Object key, final Object value) {
    if (TransactionSynchronizationManager.isSynchronizationActive()) {
      TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
        public void afterCommit() {
          TransactionAwareCacheDecorator.this.targetCache.put(key, value);
        }
      });
    } else {
      this.targetCache.put(key, value);
    }
  }
  
  public ValueWrapper putIfAbsent(Object key, Object value) {
    return this.targetCache.putIfAbsent(key, value);
  }

  public void evict(final Object key) {
    if (TransactionSynchronizationManager.isSynchronizationActive()) {
      TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
        public void afterCommit() {
          TransactionAwareCacheDecorator.this.targetCache.evict(key);
        }
      });
    } else {
      this.targetCache.evict(key);
    }

  }

  public void clear() {
    if (TransactionSynchronizationManager.isSynchronizationActive()) {
      TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
        public void afterCommit() {
          TransactionAwareCacheDecorator.this.targetCache.clear();
        }
      });
    } else {
      this.targetCache.clear();
    }
  }
}
```

### Mybatis

PerpetualCache 缓存类

9 个装饰器类，用来实现功能增强。
它们分别是：FifoCache、LoggingCache、LruCache、ScheduledCache、SerializedCache、SoftCache、SynchronizedCache、WeakCache、TransactionalCache。

```java
public interface Cache {
  String getId();
  void putObject(Object key, Object value);
  Object getObject(Object key);
  Object removeObject(Object key);
  void clear();
  int getSize();
  ReadWriteLock getReadWriteLock();
}

public class PerpetualCache implements Cache {
  private final String id;
  private Map<Object, Object> cache = new HashMap<Object, Object>();

  public PerpetualCache(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public int getSize() {
    return cache.size();
  }

  @Override
  public void putObject(Object key, Object value) {
    cache.put(key, value);
  }

  @Override
  public Object getObject(Object key) {
    return cache.get(key);
  }

  @Override
  public Object removeObject(Object key) {
    return cache.remove(key);
  }

  @Override
  public void clear() {
    cache.clear();
  }

  @Override
  public ReadWriteLock getReadWriteLock() {
    return null;
  }
  //省略部分代码...
}
```

```java
public class LruCache implements Cache {
  private final Cache delegate;
  private Map<Object, Object> keyMap;
  private Object eldestKey;

  public LruCache(Cache delegate) {
    this.delegate = delegate;
    setSize(1024);
  }

  @Override
  public String getId() {
    return delegate.getId();
  }

  @Override
  public int getSize() {
    return delegate.getSize();
  }

  public void setSize(final int size) {
    keyMap = new LinkedHashMap<Object, Object>(size, .75F, true) {
      private static final long serialVersionUID = 4267176411845948333L;

      @Override
      protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
        boolean tooBig = size() > size;
        if (tooBig) {
          eldestKey = eldest.getKey();
        }
        return tooBig;
      }
    };
  }

  @Override
  public void putObject(Object key, Object value) {
    delegate.putObject(key, value);
    cycleKeyList(key);
  }

  @Override
  public Object getObject(Object key) {
    keyMap.get(key); //touch
    return delegate.getObject(key);
  }

  @Override
  public Object removeObject(Object key) {
    return delegate.removeObject(key);
  }

  @Override
  public void clear() {
    delegate.clear();
    keyMap.clear();
  }

  @Override
  public ReadWriteLock getReadWriteLock() {
    return null;
  }

  private void cycleKeyList(Object key) {
    keyMap.put(key, key);
    if (eldestKey != null) {
      delegate.removeObject(eldestKey);
      eldestKey = null;
    }
  }
}
```

本篇实例部分的代码引用自王争老师的设计模式之美专栏

