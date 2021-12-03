# 模板方法模式（Template Method）

## 介绍

**模板方法模式**是一种行为设计模式，它在一个超类中定义一个**算法骨架**，并将某些步骤推迟到子类中实现。

模板方法可以让子类在不改变算法整体结构的情况下，重新定义算法中的某些步骤。

## 适用场景

- 希望客户端扩展某个**特定算法步骤**。
- 多个类的算法相似时，可将实现步骤提取到超类中。
- ...

## 优缺点

优点：

- 允许重写一个大型算法中的特定部分。
- 重复代码被提取到一个超类中。

缺点：

- 受骨架框架限制。
- 违反里氏替换原则。
- 步骤越多维护越难。

## 与其他模式的关系

- **工厂方法**模式是**模板方法**模式的一种特殊形式。
- 同时**工厂方法**也可以作为一个大型**模板方法**中的一个步骤。

## 实现方式

1. 创建抽象基类并**声明一个模板方法**和代表算法步骤的一系列抽象方法。
    - 可用`final`修饰模板方法防止子类重写。
2. 将步骤设为抽象方法(或者通过报错的方式强制子类实现)，或者提供默认实现。
3. 为每个算法变体新建一个具体子类，子类必须实现抽象步骤，也可以重写默认实现。


> 回调（Callback）也能起到跟模板模式相同的作用。
>
> 同步回调看起来更像模板模式，异步回调看起来更像观察者模式。

- 回调：注册函数到某个类中。
    - 同步回调：函数返回之前执行注册的函数。
    - 异步回调（延迟回调）：函数返回之后执行注册的函数。



--- 

## 示例

模板方法类

```java
public abstract class Computer {

    private String mainboard = "自带主板";
    protected String cpu;
    protected String gpu;
    protected String ram;
    protected String rom;

    // final 防止子类重写
    public final void buildComputer() {
        installCpu();
        installGpu();
        installRam();
        installRom();
    }

    // 抽象方法，给子类实现
    protected abstract void installCpu();

    // 抽象方法，给子类实现
    protected abstract void installGpu();

    // 默认实现
    protected void installRam() {
        this.ram = "8G";
    }

    // 默认实现
    protected void installRom() {
        this.rom = "512G固态";
    }

    public String show() {
        return "Computer{" +
                "mainboard='" + mainboard + '\'' +
                ", cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", ram='" + ram + '\'' +
                ", rom='" + rom + '\'' +
                '}';
    }
}
```

实现类

```java
public class OfficeComputer extends Computer {

    @Override
    protected void installCpu() {
        this.cpu = "intel i5 ";
    }
    @Override
    protected void installGpu() {
        this.gpu = "intel i5 集成显卡";
    }
}

public class GamingComputer extends Computer {

    @Override
    protected void installCpu() {
        this.cpu = "amd 5900x";
    }
    @Override
    protected void installGpu() {
        this.gpu = "nvidia 3090 super";
    }
    @Override
    protected void installRam() {
        this.ram = "16G";
    }
    @Override
    protected void installRom() {
        this.rom = "1T 固态";
    }
}

public class ProgrammingComputer extends Computer {

    @Override
    protected void installCpu() {
        this.cpu = "amd 5900x";
    }
    @Override
    protected void installGpu() {
        this.gpu = "nvidia 2070 super";
    }
    @Override
    protected void installRam() {
        this.ram = "64G";
    }
    @Override
    protected void installRom() {
        this.rom = "2T 固态";
    }
}
```

测试代码

```java
public class TemplateTest {

    @Test
    public void test() {

        Computer officeComputer = new OfficeComputer();
        Computer gamingComputer = new GamingComputer();
        Computer programmingComputer = new ProgrammingComputer();

        officeComputer.buildComputer();
        System.out.println(officeComputer.show());

        gamingComputer.buildComputer();
        System.out.println(gamingComputer.show());

        programmingComputer.buildComputer();
        System.out.println(programmingComputer.show());
    }
}
```

--- 

## 实例

### JDK

#### java.io.InputStream、OutputStream、Reader、Writer

```java

public abstract class InputStream implements Closeable {
    //...省略其他代码...

    public int read(byte b[], int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte) c;

        int i = 1;
        try {
            for (; i < len; i++) {
                c = read();
                if (c == -1) {
                    break;
                }
                b[off + i] = (byte) c;
            }
        } catch (IOException ee) {
        }
        return i;
    }

    public abstract int read() throws IOException;
}

public class ByteArrayInputStream extends InputStream {
    //...省略其他代码...

    @Override
    public synchronized int read() {
        return (pos < count) ? (buf[pos++] & 0xff) : -1;
    }
}
```

#### java.util.AbstractList、AbstractSet、AbstractMap

```java
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        boolean modified = false;
        for (E e : c) {
            add(index++, e);
            modified = true;
        }
        return modified;
    }

    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }
}
```

#### javax.servlet.http.HttpServlet

自己的实现
```java
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello World.");
    }
}
```

模板类
```java
public abstract class HttpServlet extends GenericServlet implements Serializable {

    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_TRACE = "TRACE";
    private static final String HEADER_IFMODSINCE = "If-Modified-Since";
    private static final String HEADER_LASTMOD = "Last-Modified";
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");

    /**
     * 将请求及响应 转换成 HttpServletRequest 及 HttpServletResponse，
     * 再调用 service() 的重载方法
     */
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request;
        HttpServletResponse response;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) res;
        } catch (ClassCastException e) {
            throw new ServletException("non-HTTP request or response");
        }
        service(request, response);
    }

    /**
     * 对 HTTP 协议各种类型的请求分别进行处理 (模板方法)
     */
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("GET")) {
            long lastModified = getLastModified(req);
            if (lastModified == -1L) {
                doGet(req, resp);
            } else {
                long ifModifiedSince = req.getDateHeader("If-Modified-Since");
                if (ifModifiedSince < (lastModified / 1000L) * 1000L) {
                    maybeSetLastModified(resp, lastModified);
                    doGet(req, resp);
                } else {
                    resp.setStatus(304);
                }
            }
        } else if (method.equals("HEAD")) {
            long lastModified = getLastModified(req);
            maybeSetLastModified(resp, lastModified);
            doHead(req, resp);
        } else if (method.equals("POST"))
            doPost(req, resp);
        else if (method.equals("PUT"))
            doPut(req, resp);
        else if (method.equals("DELETE"))
            doDelete(req, resp);
        else if (method.equals("OPTIONS"))
            doOptions(req, resp);
        else if (method.equals("TRACE")) {
            doTrace(req, resp);
        } else {
            String errMsg = lStrings.getString("http.method_not_implemented");
            Object errArgs[] = new Object[1];
            errArgs[0] = method;
            errMsg = MessageFormat.format(errMsg, errArgs);
            resp.sendError(501, errMsg);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1"))
            resp.sendError(405, msg);
        else
            resp.sendError(400, msg);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_post_not_supported");
        if (protocol.endsWith("1.1"))
            resp.sendError(405, msg);
        else
            resp.sendError(400, msg);
    }

    protected long getLastModified(HttpServletRequest req) {
        return -1L;
    }

    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NoBodyResponse response = new NoBodyResponse(resp);
        doGet(req, response);
        response.setContentLength();
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ...
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ...
    }

    private Method[] getAllDeclaredMethods(Class c) {
        // ...
    }

    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Method methods[] = getAllDeclaredMethods(getClass());
        // ...
    }

    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ...
    }

    private void maybeSetLastModified(HttpServletResponse resp, long lastModified) {
        // ...
    }
}
```

