# 中介者模式（Mediator）


## 介绍

**中介者模式**是一种行为设计模式，能减少对象之间混乱无序的依赖关系。

该模式会限制对象之间的直接交互，迫使它们通过一个中介者对象进行合作（**中转与协调**）。

## 适用场景

- 对象之间**紧密耦合**导致难以对其进行修改时。
- 当组件因过于**依赖其他组件**而无法在不同应用中复用时。
- 为了在不同情景下复用一些基本行为，导致**被迫创建大量组件**子类时。
- ...


## 优缺点

优点：

- 开闭原则。
- 单一职责原则。
- 减轻组件耦合。
- 方便复用组件。

缺点：

- 中介者可能会演化成为**上帝对象**。


## 与其他模式的关系
- **外观模式**和**中介者**的职责类似：它们都尝试在大量紧密耦合的类中组织起合作。
    - **外观**为子系统中的所有对象定义了一个简单接口，但是它不提供任何新功能。子系统本身不会意识到外观的存在。子系统中的对象可以直接进行交流。
    - **中介者**将系统中组件的沟通行为中心化。各组件只知道中介者对象，无法直接相互交流。
- **责任链**模式、**命令**模式、**中介者**模式和**观察者**模式用于处理请求发送者和接收者之间的不同连接方式
    - **命令**在发送者和请求者之间建立单向连接。
    - **观察者**允许接收者动态地订阅或取消接收请求。
    - **责任链**按照顺序将请求动态传递给一系列的潜在接收者，直至其中一名接收者对请求进行处理。
    - **中介者**清除了发送者和请求者之间的直接连接，强制它们通过一个中介对象进行间接沟通。
- **中介者**和**观察者**之间的区别
    - **中介者**的主要目标是消除一系列系统组件之间的相互依赖。这些组件将依赖于同一个中介者对象。
    - **观察者**的目标是在对象之间建立动态的单向连接，使得部分对象可作为其他对象的附属发挥作用。
    - **中介者**的应用场景中，参与者之间的交互关系错综复杂，既可以是消息的发送者、也可以同时是消息的接收者。
    - **观察者**的应用场景中，参与者之间的交互比较有条理，一般都是单向的。
    - 更进一步、也可以让中介者负责对象的创建与销毁，这样的话中介者可能会与**工厂/外观**类似。

## 实现方式

1. 声明中介者接口并描述中介者和各种组件之间所需的交流接口。
2. 实现具体中介者类。该类可从自行保存其下所有组件的引用中受益。
3. 组件必须保存对于中介者对象的引用。
4. 修改组件代码使其可调用中介者的通知方法。


--- 

## 示例

这里举一个车辆呼叫的例子。

组件接口

```java
public interface Component {
    /**
     * 设置中介者引用
     */
    void setMediator(Mediator mediator);

    Type getType();
}
```

组件实现

```java
/**
 * 组件之一：用户
 */
public class ComponentUser implements Component{

    // 中介者引用
    private Mediator mediator;
    // 现金
    private int money;

    public ComponentUser(int money) {
        this.money = money;
    }

    // 设置中介者引用
    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
    
    @Override
    public Type getType() {
        return Type.USER;
    }

    public int getMoney() {
        return money;
    }
    
    // 叫车
    public String callTheCar(Type type){
        return mediator.callTheCar(type);
    }

    // 扣费 并返回余额
    public int deduct(int money){
        this.money -= money;
        return money;
    }

}
/**
 * 组件之一：小车
 */
public class ComponentCar implements Component {
    // ...   省略成员变量  mediator 和  money
    // ...   省略成员方法  setMediator 和  getType 和 getMoney
    
    // 收费 并返回余额
    public void charge() {
        mediator.charge(1);
        money += 1;
    }
}

/**
 * 组件之一：货车
 */
public class ComponentVan implements Component {
    // ...   省略成员变量  mediator 和  money
    // ...   省略成员方法  setMediator 和  getType 和 getMoney

    // 收费 并返回余额
    public void charge() {
        mediator.charge(2);
        money += 2;
    }
}

/**
 * 组件之一：货车
 */
public class ComponentTruck implements Component {
    // ...   省略成员变量  mediator 和  money
    // ...   省略成员方法  setMediator 和  getType 和 getMoney

    // 收费 并返回余额
    public void charge() {
        mediator.charge(3);
        money += 3;
    }
}
```

类型枚举

```java
public enum Type {
    // 用户、小车、面包车、货车
    USER,CAR,VAN,TRUCK;
}
```

中介者接口

```java
public interface Mediator {
    /**
     * 把组件 注册到 中介者
     */
    void registerComponent(Component component);

    // 呼叫车辆
    String callTheCar(Type type);

    // 收费
    void charge(int i);
}
```

中介者实现：车辆呼叫中心

```java
public class MediatorCenter implements Mediator {

    // 保存其下所有组件的引用
    private ComponentUser user;
    private ComponentCar car;
    private ComponentVan van;
    private ComponentTruck truck;

    // 注册组件
    @Override
    public void registerComponent(Component component) {
        switch (component.getType()) {
            case USER:
                user = (ComponentUser) component;
                break;
            case CAR:
                car = (ComponentCar) component;
                break;
            case VAN:
                van = (ComponentVan) component;
                break;
            case TRUCK:
                truck = (ComponentTruck) component;
                break;
            default:
        }
    }

    // 呼叫车辆
    @Override
    public String callTheCar(Type type) {
        switch (type) {
            case CAR:
                if (car != null) {
                    car.charge();
                    return "呼叫小车成功";
                }
            case VAN:
                if (van != null) {
                    van.charge();
                    return "呼叫面包车成功";
                }
            case TRUCK:
                if (truck != null) {
                    truck.charge();
                    return "呼叫货车成功";
                }
            default:
                return "暂无车辆可调度";
        }
    }

    // 付费方法
    @Override
    public void charge(int paymentAmount) {
        user.deduct(paymentAmount);
    }

    // 获取余额
    public int getMoneyByType(Type type){
        switch (type) {
            case USER:
                return user.getMoney();
            case CAR:
                return car.getMoney();
            case VAN:
                return van.getMoney();
            case TRUCK:
                return truck.getMoney();
            default:
                return 999;
        }
    }
}
```

测试代码

```java
public class MediatorTest {
    @Test
    public void test() {
        // 创建中介者
        Mediator mediatorCenter = new MediatorCenter();

        // 创建组件
        ComponentUser componentUser = new ComponentUser(10);
        ComponentCar componentCar = new ComponentCar();
        ComponentVan componentVan = new ComponentVan();
        ComponentTruck componentTruck = new ComponentTruck();

        // 设置中介引用
        componentUser.setMediator(mediatorCenter);
        componentCar.setMediator(mediatorCenter);
        componentVan.setMediator(mediatorCenter);
        componentTruck.setMediator(mediatorCenter);

        // 把组件注册进中介者
        mediatorCenter.registerComponent(componentUser);
        mediatorCenter.registerComponent(componentCar);
        mediatorCenter.registerComponent(componentVan);

        Assertions.assertEquals("呼叫小车成功", mediatorCenter.callTheCar(Type.CAR));
        Assertions.assertEquals("暂无车辆可调度", mediatorCenter.callTheCar(Type.TRUCK));
        Assertions.assertEquals("呼叫小车成功", mediatorCenter.callTheCar(Type.CAR));
        Assertions.assertEquals("呼叫面包车成功", mediatorCenter.callTheCar(Type.VAN));

        mediatorCenter.registerComponent(componentTruck);
        Assertions.assertEquals("呼叫货车成功", mediatorCenter.callTheCar(Type.TRUCK));

        Assertions.assertEquals(3, componentUser.getMoney());
        Assertions.assertEquals(2, componentCar.getMoney());
        Assertions.assertEquals(2, componentVan.getMoney());
        Assertions.assertEquals(3, componentTruck.getMoney());
    }
}
```

--- 

## 实例

### java.util.Timer


```java
public class Timer {

    private final TaskQueue queue = new TaskQueue();
    private final TimerThread thread = new TimerThread(queue);
    
    public void schedule(TimerTask task, long delay) {
        if (delay < 0)
            throw new IllegalArgumentException("Negative delay.");
        sched(task, System.currentTimeMillis()+delay, 0);
    }

    public void schedule(TimerTask task, Date time) {
        sched(task, time.getTime(), 0);
    }

    private void sched(TimerTask task, long time, long period) {
        if (time < 0)
            throw new IllegalArgumentException("Illegal execution time.");

        // Constrain value of period sufficiently to prevent numeric
        // overflow while still being effectively infinitely large.
        if (Math.abs(period) > (Long.MAX_VALUE >> 1))
            period >>= 1;

        synchronized(queue) {
            if (!thread.newTasksMayBeScheduled)
                throw new IllegalStateException("Timer already cancelled.");

            synchronized(task.lock) {
                if (task.state != TimerTask.VIRGIN)
                    throw new IllegalStateException(
                            "Task already scheduled or cancelled");
                task.nextExecutionTime = time;
                task.period = period;
                task.state = TimerTask.SCHEDULED;
            }

            queue.add(task);
            if (queue.getMin() == task)
                queue.notify();
        }
    }

    public void cancel() {
        synchronized(queue) {
            thread.newTasksMayBeScheduled = false;
            queue.clear();
            queue.notify();  // In case queue was already empty.
        }
    }
    
    // ... 忽略
}
```

Timer 是中介者，TimerTask 是抽象组件类，

我们只要实现 TimerTask，再使用 schedule 方法注册进中介者即可实现定时器功能。


### java.util.concurrent.Executor

```java
public interface Executor {
    void execute(Runnable command);
}
```

### java.util.concurrent.ExecutorService

```java
public interface ExecutorService extends Executor {

    void shutdown();
    List<Runnable> shutdownNow();
    boolean isShutdown();
    boolean isTerminated();
    boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;

    <T> Future<T> submit(Callable<T> task);

    <T> Future<T> submit(Runnable task, T result);

    Future<?> submit(Runnable task);

    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException;

    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException;

    <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;

    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
}
```




