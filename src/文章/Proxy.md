# 代理模式（Proxy）

## 介绍

代理模式是一种**结构型**设计模式，让你能够提供对象的替代品或其占位符。

代理控制着对于原对象的访问，并允许在将请求提交给对象前后进行一些处理。


## 适用场景

- **非功能性需求开发**（增强代理，比如：监控、统计、鉴权、限流、事务、幂等、日志、缓存等）
- **本地执行远程服务**（远程代理，如 RPC 框架）
- **访问控制**（保护代理）
- **延迟初始化、智能引用**（虚拟代理：如果是重量级对象，可以实现延迟初始化、监控无使用则销毁等）
- ...

## 优缺点

优点：

- 对客户端透明
- 可以进行生命周期的管理
- 即使对象还没准备好，代理类也可以工作
- 开闭原则，可以不对服务和客户端修改的情况下创建新代理

缺点：

- 代码变复杂
- 服务响应可能延迟

## 与其他模式的关系

后续讲到对应的模式时再写

## 实现方式

1. 代理类和原始类需要**实现相同的接口**，如果是无法修改的第三方类可以**采用继承**的方式。
2. **创建代理类**，其中必须包含一个存储指向服务的引用的成员变量。
3. 根据需求**实现代理方法**。

以上为**静态代理**，

还有**动态代理**的实现方式：

- **jdk 动态代理**（通过反射实例化代理对象）
- **cglib 动态代理**（借助 asm 字节码技术：直接生成新的 .class 字节码文件）
- **Aspectj 动态代理**（通过织入的方式修改目标类：编译时织入/编译后织入/加载时织入）
- **instrumentation 动态代理**（修改目标类的字节码：类装载的时候动态拦截去修改）
- ...

--- 

## 示例

接口：

```java
public interface CommandExecutor {
    void runCommand(String cmd) throws Exception;
}
```

被代理类（原来的实现类）：

```java
public class CommandExecutorImpl implements CommandExecutor{
    @Override
    public void runCommand(String cmd) throws Exception {

        // ... （执行对应操作）

        System.out.println("'" + cmd + "' command executed.");
    }
}
```

代理类：

```java
public class CommandExecutorProxy implements CommandExecutor{

    /**
     * 指向原服务的引用
     */
    private CommandExecutor executor;
    private boolean isAdmin;

    public CommandExecutorProxy(String user, String pwd){
        if("admin".equals(user) && "abcABC123!@#".equals(pwd)) {
            isAdmin=true;
        }
        executor = new CommandExecutorImpl();
    }

    /**
     * 代理类和原始类需要实现相同的接口
     */
    @Override
    public void runCommand(String cmd) throws Exception {
        if(isAdmin){
            executor.runCommand(cmd);
        }else{
            if(cmd.trim().startsWith("rm")){
                throw new Exception("rm command is not allowed for non-admin users.");
            }else{
                executor.runCommand(cmd);
            }
        }
    }
}
```

测试代码

```java
public class ProxyTest {

    @Test
    public void test() throws Exception {
        CommandExecutor executor = new CommandExecutorProxy("admin", "abcABC123!@#");
        executor.runCommand("ls");
        executor.runCommand("rm -rf *");
    }

    @Test
    public void testException() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            CommandExecutor executor = new CommandExecutorProxy("admin", "123456");
            executor.runCommand("ls");
            executor.runCommand("rm -rf *");
        });
        Assertions.assertEquals("rm command is not allowed for non-admin users.", exception.getMessage());
    }
}
```

--- 

## 实例

### JDK

#### java.lang.reflect.Proxy

Proxy 提供用于创建动态代理类和实例的静态方法

#### java.rmi.* （RMI 远程方法调用）

...

#### javax.inject.Inject （依赖注入器）

> CDI 托管 bean 实例本质上是一个自动生成类的可序列化代理实例，它扩展了原始支持 bean 类并通过公共方法将所有公共方法进一步委托给实际实例
>
> ```java
> public CDIManagedBeanProxy extends ActualManagedBean implements Serializable {
>
>     public String getSomeProperty() {
>         ActualManagedBean instance = CDI.resolveItSomehow();
>         return instance.getSomeProperty();
>     }
>
>     public void setSomeProperty(String someProperty) {
>         ActualManagedBean instance = CDI.resolveItSomehow();
>         instance.setSomeProperty(someProperty);
>     }
> }
> ```

### Spring

Spring AOP
（JDK 动态代理和 cglib 动态代理）

### Mybatis

利用职责链模式和动态代理模式来实现 MyBatis Plugin

这个等后续讲到职责链再写



