# 命令模式（Command）

## 介绍

**命令模式**是一种行为设计模式，

它可以将请求转换为一个包含**与请求相关**的所有信息的**独立对象**。

转换让你能根据不同的请求**将方法参数化**，并且能够支持排队、延迟执行、记录日志、撤销等**附加控制功能**。


## 适用场景

- 实现参数化对象。
- 实现将操作序列化功能。
    - 比如放入队列中或者远程执行操作。
- 实现操作回滚功能。
    - 如果部分状态私有，可配合备忘录模式进行实现。
    - 备份会占据大量内存，如果可能，可使用反向操作来进行优化。
- ...


## 优缺点

优点：

- 开闭原则。
- 单一职责原则：可以解耦触发和执行操作的类。
- 可以实现操作的延迟执行。
- 可以实现撤销和恢复功能。
- 可以将一组简单命令组合成一个复杂命令。

缺点：

- 代码复杂度增加：相当于在发送者和接受者中间多加了一层。


## 与其他模式的关系


- **原型**模式可用于保存**命令**的历史记录。
- 可以同时使用**命令**和**备忘录**模式来实现 “撤销”。
- 可以将**访问者**模式视为**命令**模式的加强版本，其对象可对不同类的多种对象执行操作。
- **责任链**的管理者可使用**命令**模式实现。
    - 可以对由请求代表的同一个上下文对象执行许多不同的操作。
    - 也可以是请求自身就是一个命令对象。
- **命令**和**策略**模式看上去很像但它们的意图有非常大的不同。
    - 在命令模式中，不同的命令具有不同的目的，对应不同的处理逻辑，并且互相之间不可替换。
    - 在策略模式中，不同的策略具有相同的目的、不同的实现、互相之间可以替换。
- **责任链**模式、**命令**模式、**中介者**模式和**观察者**模式用于处理请求发送者和接收者之间的不同连接方式
    - 责任链按照顺序将请求动态传递给一系列的潜在接收者，直至其中一名接收者对请求进行处理。
    - 命令在发送者和请求者之间建立单向连接。
    - 中介者清除了发送者和请求者之间的直接连接，强制它们通过一个中介对象进行间接沟通。
    - 观察者允许接收者动态地订阅或取消接收请求。

## 实现方式

1. 声明仅有一个执行方法的**命令接口**。
2. 抽取请求并使之成为**实现命令接口**的具体命令类。
    - 每个类都必须有一组成员变量来保存**请求参数**和**实际接收者**对象的引用。
    - 所有变量的数值都必须通过命令构造函数进行初始化。
3. 找到**担任发送者**职责的类。
    - 在这些类中添加保存命令的成员变量。发送者只能通过命令接口与其命令进行交互。
    - 发送者自身通常并不创建命令对象，而是通过客户端代码获取。

--- 

## 示例

命令抽象类

```java
public abstract class Command {
    /**
     *  实际接收者。   作为示例，这里忽略了请求的参数
     */
    protected GameEngine gameEngine;

    public Command(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    /**
     *  大部分命令只处理如何将请求传递到接收者的细节
     *  如有需要，也可以在这个类里添加一些附加功能，如撤销、队列、延迟等。
     */
    public abstract String execute();
}
```

具体命令

```java
public class CommandQ extends Command{
    public CommandQ(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public String execute() {
        // 将请求传递到接收者的细节
        return gameEngine.attack();
    }
}

public class CommandW extends Command{
    public CommandW(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public String execute() {
        return gameEngine.vertigo();
    }
}

public class CommandE extends Command{
    public CommandE(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public String execute() {
        return gameEngine.sleep();
    }
}

public class CommandR extends Command {
    public CommandR(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public String execute() {
        // 将请求传递到接收者的细节，也可以做些别的操作
        return gameEngine.attack() + gameEngine.fear();
    }
}
```

接收者 （Receiver）

```java
public class GameEngine {
    /**
     * 接收者自己会完成实际的工作。
     */
    public String attack() {
        return "敌方扣血";
    }
    public String vertigo() {
        return "敌方眩晕";
    }
    public String sleep() {
        return "敌方沉睡";
    }
    public String fear() {
        return "敌方恐惧";
    }
}
```

测试代码

```java
public class CommandTest {
    @Test
    public void test() {
        // 游戏引擎，负责底层逻辑操作
        GameEngine gameEngine = new GameEngine();

        Assertions.assertEquals("敌方扣血",new CommandQ(gameEngine).execute());
        Assertions.assertEquals("敌方眩晕",new CommandW(gameEngine).execute());
        Assertions.assertEquals("敌方沉睡",new CommandE(gameEngine).execute());
        Assertions.assertEquals("敌方扣血敌方恐惧",new CommandR(gameEngine).execute());

        /**
         *  利用命令模式，可以轻松的实现队列功能（这里未实现）
         *  例如客户端输入一个连招命令 “WQER” 的文本，触发者将命令解码后按照顺序执行
         *  撤销重做、延迟、记录历史等同理。
         */
    }
}
```

--- 

## 实例

### JDK Runnable 系列

Runnable 为命令抽象类， 只要是实现了 Runnable 接口的类都为具体命令角色。

Thread 就是调用者（Invoker），它提供了start，join，interrupt 等方法来控制“命令”也就是 Runnable 的执行。

```java
// 相当于 Command
public interface Runnable {
    // 相当于 execute()
    public abstract void run();
}
```

更具体点的例子是 ThreadPoolExecutor。

ThreadPoolExecutor 它本身是一个调用者，它持有一个命令队列，客户端可以向他提交要执行的命令。

```java
public interface Executor {
    void execute(Runnable command);
}
```


```java
public class ThreadPoolExecutor extends AbstractExecutorService {
    //...

    /**
     * Runnable：任务抽象，也就是“命令”；
     * BlockingQueue：任务阻塞队列，它实际上就是模式中的“命令队列”。
     */
    private final BlockingQueue<Runnable> workQueue;

    //...
}
```
