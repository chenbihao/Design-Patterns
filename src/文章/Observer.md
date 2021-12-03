# 观察者模式（Observer）

## 介绍

**观察者模式**是一种行为型设计模式。

可以用来定义一种**订阅机制**，可在对象事件发生时**通知**多个“观察”该对象的其他对象。


## 适用场景

- 当应用中的一些对象必须**观察**其他对象时，可使用该模式。
- 当一个对象状态的改变需要**改变**其他对象，或实际对象是事先未知的或动态变化的时，可使用该模式。
- ...


## 优缺点

优点：

- 开闭原则：无须修改发布者代码即可引入新的订阅类，反之亦然。
- 可以在运行时建立对象之间的联系。

缺点：

- 通知顺序随机。


## 与其他模式的关系

后面讲到再写。


## 实现方式

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

## 示例

接口定义：
```java
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String msg);
}
public interface Observer {
    void update(String msg);
}
```

RSS 服务端 （被观察者主体）
```java
public class RssSubject implements Subject{

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String msg) {
        // 通知每一个订阅者
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }
}
```

RSS 订阅对象（观察者）
```java
public class RssObserver implements Observer {

    // 当前订阅者用户名
    private String user;
    
    // 记录最后信息，方便测试用
    private String lastMessage;
    // 记录消息总数，方便测试用
    private int count;

    public RssObserver(String user) {
        this.user = user;
    }

    // 收到推送的执行方法
    @Override
    public void update(String msg) {
        System.out.println("当前用户[" + user + "]收到推送信息：" + msg);
        lastMessage = msg;
        count++;
    }

    // 方便测试用
    public String getLastMessage() {
        return lastMessage;
    }
    public int getCount() {
        return count;
    }
}
```

测试代码
```java
public class ObserverTest {
    
    @Test
    public void test() {
        
        // RSS 服务端 （被观察者主体）
        RssSubject rssSubject = new RssSubject();
        
        // rss 订阅对象（观察者）
        RssObserver observer1 = new RssObserver("用户A");
        RssObserver observer2 = new RssObserver("用户B");
        RssObserver observer3 = new RssObserver("用户C");
        RssObserver observer4 = new RssObserver("用户D");

        // 把观察者注册到主体（相当于订阅了通知，有新推送则会收到）
        rssSubject.registerObserver(observer1);
        rssSubject.registerObserver(observer2);
        rssSubject.registerObserver(observer3);

        // 推送信息
        rssSubject.notifyObservers("早上推送文章《论观察者模式1》");

        Assertions.assertEquals(1,observer1.getCount());
        Assertions.assertEquals(0,observer4.getCount());

        rssSubject.removeObserver(observer3);
        rssSubject.registerObserver(observer4);

        rssSubject.notifyObservers("晚上推送文章《论观察者模式2》");
        Assertions.assertEquals(2,observer1.getCount());
        Assertions.assertEquals(1,observer3.getCount());
        Assertions.assertEquals(1,observer4.getCount());

        Assertions.assertEquals("早上推送文章《论观察者模式1》",observer3.getLastMessage());
    }
}
```

--- 

## 实例

### JDK

java.util.**Observer**、**Observable** （已过时)

java.util.**EventListener**（广泛存在于 Swing 组件中）

### Guava 

#### EventBus

EventBus：同步阻塞的观察者模式

```java
public class EventBus {
    
    private final String identifier;
    private final Executor executor;
    private final SubscriberExceptionHandler exceptionHandler;

    private final SubscriberRegistry subscribers = new SubscriberRegistry(this);
    private final Dispatcher dispatcher;

    // ... 省略
    
    EventBus(
            String identifier,
            Executor executor,
            Dispatcher dispatcher,
            SubscriberExceptionHandler exceptionHandler) {
        this.identifier = checkNotNull(identifier);
        this.executor = checkNotNull(executor);
        this.dispatcher = checkNotNull(dispatcher);
        this.exceptionHandler = checkNotNull(exceptionHandler);
    }
    
    public void register(Object object) {
        subscribers.register(object);
    }
    public void unregister(Object object) {
        subscribers.unregister(object);
    }

    // 利用 @Subscribe 注解可以实现 发送给匹配类型的观察者，而不是发送全部
    public void post(Object event) {
        Iterator<Subscriber> eventSubscribers = subscribers.getSubscribers(event);
        if (eventSubscribers.hasNext()) {
            dispatcher.dispatch(event, eventSubscribers);
        } else if (!(event instanceof DeadEvent)) {
            // the event had no subscribers and was not itself a DeadEvent
            post(new DeadEvent(this, event));
        }
    }
    
    // ... 省略
}
```

AsyncEventBus：异步非阻塞的观察者模式
```java
public class AsyncEventBus extends EventBus {

  public AsyncEventBus(String identifier, Executor executor) {
    super(identifier, executor, Dispatcher.legacyAsync(), LoggingHandler.INSTANCE);
  }
  public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
    super("default", executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
  }
  public AsyncEventBus(Executor executor) {
    super("default", executor, Dispatcher.legacyAsync(), LoggingHandler.INSTANCE);
  }
}
```
