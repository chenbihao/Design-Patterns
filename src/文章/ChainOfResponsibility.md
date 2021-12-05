# 职责链模式（Chain of Responsibility）

## 介绍

**责任链模式**是一种行为设计模式。

责任链将请求的发送和接收解耦，让多个接收对象都有机会处理这个请求。

这些接收对象串成一条链，并沿着这条链传递这个请求，直到链上的某个接收对象能够处理它为止。

## 适用场景

- 需要按顺序执行多个处理者。
- 需要处理者及其顺序必须在运行时进行改变。
- 需要使用不同方式处理不同种类请求，而且请求类型和顺序预先未知。
- ...

## 优缺点

优点：

- 可以控制请求处理的顺序。
- 单一职责原则：可以将发起类和操作类解耦。
- 开闭原则：不改变现有代码的情况下增加处理者。

缺点：

- 部分请求可能未被处理。

## 与其他模式的关系

- **责任链模式**可以和**组合模式**结合使用。
- **责任链模式**和**装饰模式**的类结构非常相似。两者都依赖递归组合将需要执行的操作传递给一系列对象。
    - **责任链**的管理者可以相互独立地执行一切操作，可以随时停止传递请求。
    - **装饰**可以在遵循基本接口的情况下扩展对象的行为。但无法中断请求的传递。

## 实现方式

1. 可以根据**处理者接口**创建抽象处理者基类（模板方法）。
    - 需要有一个成员变量来存储指向链的下个处理者的引用。
2. 创建**具体处理者子类**并实现其处理方法。
    - 是否自行处理这个请求。
    - 是否将该请求沿着链进行传递。
3. 客户端可以自行组装链或者从其他对象处获得预先组装好的链。

> 可以使用链表来存储处理器，也可以使用数组来存储处理器。
>
> 如果链上的某个处理器能够处理这个请求，可以选择停止传递或者继续传递处理。

--- 

## 示例

处理器接口（这里使用了模板方法）

```java
public abstract class Handler {

    protected Handler next = null;

    public void setNext(Handler handler) {
        this.next = handler;
    }

    public final void handle(String msg) {
        // 执行处理
        boolean handled = doHandler(msg);

//        // 如果当前处理器处理不了，并且还有下一个节点的话，则传递处理，否则停止传递
//        if (next != null && !handled) {
//            next.handle(msg);
//        }

        // 这里选择继续传递处理
        if (next != null) {
            next.handle(msg);
        }
    }

    // 留给子类实现
    protected abstract boolean doHandler(String msg);
}
```

处理器实现

```java
public class HandlerA extends Handler {
    @Override
    protected boolean doHandler(String msg) {
        boolean handled = false;
        if (msg != null) {
            if (msg.contains("wrong")) {
                System.out.println("发现wrong，进行告警处理...");
            }
        }
        return handled;
    }
}

public class HandlerB extends Handler {
    @Override
    protected boolean doHandler(String msg) {
        boolean handled = false;
        if (msg != null) {
            if (msg.contains("error")) {
                System.out.println("发现error，紧急通知处理...");
            }
        }
        return handled;
    }
}
```

处理器链

```java
public class HandlerChain {
    private Handler head = null;
    private Handler tail = null;

    public void addHandler(Handler handler) {
        handler.setNext(null);
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setNext(handler);
        tail = handler;
    }

    public void handle(String msg) {
        if (head != null) {
            head.handle(msg);
        }
    }
}
```

测试代码

```java
public class ChainOfResponsibilityTest {
    @Test
    public void test() {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());

        chain.handle("info：balabala");
        chain.handle("wrong：asdasdasd");
        chain.handle("error：aaa!!!!");
        chain.handle("wrong and error!!!!");
    }
}
```

--- 

## 实例

### JDK

#### Filter

javax.servlet.Filter

```java
public interface Filter {
    public void init(FilterConfig filterConfig) throws ServletException;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException;

    public void destroy();
}
```

FilterChain

```java
public interface FilterChain {
    // 调用引用链的下一个filter或最终servlet服务
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException;
}
```

使用示例（代码来自王争老师专栏）:

```java

public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 在创建Filter时自动调用，
        // 其中filterConfig包含这个Filter的配置参数，比如name之类的（从配置文件中读取的）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("拦截客户端发送来的请求.");
        chain.doFilter(request, response);
        System.out.println("拦截发送给客户端的响应.");
    }

    @Override
    public void destroy() {
        // 在销毁Filter时自动调用
    }
}
```

然后在 web.xml 配置文件中如下配置：

```xml
<filter>
    <filter-name>logFilter</filter-name>
    <filter-class>com.xzg.cd.LogFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>logFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

#### Logger

java.util.logging.Logger#log()

```java
public class Logger {
    // ... 

    public void log(Level level, String msg) {
        if (!isLoggable(level)) {
            return;
        }
        LogRecord lr = new LogRecord(level, msg);
        doLog(lr);
    }

    // ... 
}
```

### Spring

#### Spring Interceptor 拦截器

Servlet Filter 是 Servlet 规范的一部分，实现依赖于 Web 容器。

Spring Interceptor 是 Spring MVC 框架的一部分，由 Spring MVC 框架来提供实现。

客户端发送的请求，会先经过 Servlet Filter，然后再经过 Spring Interceptor，最后到达具体的业务代码中。

```java
public class HandlerExecutionChain {
    private final Object handler;
    private HandlerInterceptor[] interceptors;

    public void addInterceptor(HandlerInterceptor interceptor) {
        initInterceptorList().add(interceptor);
    }

    boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerInterceptor[] interceptors = getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
            for (int i = 0; i < interceptors.length; i++) {
                HandlerInterceptor interceptor = interceptors[i];
                if (!interceptor.preHandle(request, response, this.handler)) {
                    triggerAfterCompletion(request, response, null);
                    return false;
                }
            }
        }
        return true;
    }

    void applyPostHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {
        HandlerInterceptor[] interceptors = getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
            for (int i = interceptors.length - 1; i >= 0; i--) {
                HandlerInterceptor interceptor = interceptors[i];
                interceptor.postHandle(request, response, this.handler, mv);
            }
        }
    }

    void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex)
            throws Exception {
        HandlerInterceptor[] interceptors = getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
            for (int i = this.interceptorIndex; i >= 0; i--) {
                HandlerInterceptor interceptor = interceptors[i];
                try {
                    interceptor.afterCompletion(request, response, this.handler, ex);
                } catch (Throwable ex2) {
                    logger.error("HandlerInterceptor.afterCompletion threw exception", ex2);
                }
            }
        }
    }
}
```

使用示例（代码来自王争老师专栏）：

```java
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截客户端发送来的请求.");
        return true; // 继续后续的处理
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截发送给客户端的响应.");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("这里总是被执行.");
    }
}
```

然后在 Spring MVC 配置文件中配置：

```xml
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/*"/>
        <bean class="com.xzg.cd.LogInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```

