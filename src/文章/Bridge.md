# 桥接模式（Bridge）

## 介绍

### 将抽象和实现解耦，让它们可以独立变化。

在 GoF 的《设计模式》中，桥接模式是这样定义的：“将抽象和实现解耦，让它们可以独立变化。”

独立的概念可能是：抽象/平台，域/基础设施，前端/后端或接口/实现。

**抽象部分**（也被称为接口）是一些实体的高阶控制层，该层自身不完成任何具体的工作，它需要将工作委派给**实现部分层**（也被称为平台）。

这里的抽象和实现是广义上的，而非特指抽象类、实现类。

例子：JDBC 驱动

- JDBC API 对应 **抽象**
- 数据库的 Driver 对应 **实现**

### 多维度通过组合使可以独立扩展

很多书籍资料中还有另外一种理解方式： “一个类存在两个（或多个）独立变化的维度，通过组合的方式，让这些维度可以独立进行扩展。”

例子：slf4j

> slf4j 其中有三个核心概念，logger，appender 和 encoder。
>
> 分别指这个日志记录器负责哪个类的日志，日志打印到哪里以及日志打印的格式。
>
> 三个纬度上可以有不同的实现，使用者可以在每一纬度上定义多个实现。


## 适用场景

- 运行时切换不同实现方法
- 从几个独立维度上扩展一个类
- 拆分或重组一个具有多重功能的庞杂类
- ...

## 优缺点

优点：

- 可以创建与平台无关的类和程序。
- 客户端代码仅与高层抽象部分进行互动，不会接触到实现平台。
- 开闭原则：可以独立新增抽象或实现部分。
- 单一职责：抽象专注于高层逻辑处理，实现专注于实现细节。

缺点：

- 对高内聚的类使用该模式可能会让代码更加复杂。

## 与其他模式的关系

- 可以将抽象工厂模式和桥接搭配使用。
- 可以结合使用生成器模式和桥接模式：主管类负责抽象工作，各种不同的生成器负责实现工作。


## 实现方式

1. 在抽象基类（高阶控制层）中定义客户端的业务需求。
4. 在抽象类中添加指向实现类型的引用成员变量。
2. 在通用实现接口（实现平台层）中声明抽象部分所需的业务。 
3. 创建实现类。 
5. 如果高层逻辑有多个变体，则可通过扩展抽象基类为每个变体创建一个精确抽象。 
6. 客户端代码必须将实现对象传递给抽象部分的构造函数才能使其能够相互关联。此后，客户端只需与抽象对象进行交互，无需和实现对象打交道。

--- 

## 示例

‘抽象’基类：
```java
// 电脑
public interface Computer {
    /**
     * 1.定义业务需求：打印
     */
    void print();
    /**
     * 2.指向实现类型的引用：设置打印机
     */
    void setPrinter(Printer printer);
}
```
‘抽象’实现
```java
// Mac 电脑
public class ComputerMac implements Computer{
    private Printer printer;
    @Override
    public void print() {
        System.out.println("mac 请求打印：");
        printer.printFile();
    }
    @Override
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
// Windows 电脑
public class ComputerWindows implements Computer{
    private Printer printer;
    @Override
    public void print() {
        System.out.println("windows 请求打印：");
        printer.printFile();
    }
    @Override
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
```

‘实现’基类：
```java
// 打印机
public interface Printer {
    /**
     * 打印实现
     */
    void printFile();
}
```
‘实现’实现：
```java
// 爱普生打印机
public class PrinterEpson implements Printer{
    @Override
    public void printFile() {
        System.out.println("爱普生打印机正在打印...");
    }
}
// 惠普打印机
public class PrinterHp implements Printer{
    @Override
    public void printFile() {
        System.out.println("惠普打印机正在打印...");
    }
}
```

测试代码

```java
public class BridgeTest {

    @Test
    public void test() {

        Printer hp = new PrinterHp();
        Printer epson = new PrinterEpson();

        Computer mac = new ComputerMac();
        Computer windows = new ComputerWindows();

        // 我们可以在运行时更改抽象的实现（即计算机的打印机），因为抽象是指通过接口实现的。
        // 在调用mac.print() 或 windows.print() 时，它将请求分派给printer.printFile()。
        // 充当了桥梁并提供了两者之间的松散耦合。
        
        mac.setPrinter(hp);
        mac.print();
        mac.setPrinter(epson);
        mac.print();

        windows.setPrinter(hp);
        windows.print();
        windows.setPrinter(epson);
        windows.print();
    }
}
```
输出
``` shell
mac 请求打印
惠普打印机正在打印...
mac 请求打印
爱普生打印机正在打印...

windows 请求打印
惠普打印机正在打印...
windows 请求打印
爱普生打印机正在打印...
```

--- 

## 实例

### JDK

#### JDBC 驱动

用法：

```java
Class.forName("com.mysql.jdbc.Driver");//加载及注册JDBC驱动程序
String url = "jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password";
Connection con = DriverManager.getConnection(url);
Statement stmt = con.createStatement()；
String query = "select * from test";
ResultSet rs=stmt.executeQuery(query);
while(rs.next()) {
  rs.getString(1);
  rs.getInt(2);
}
```

com.mysql.jdbc.Driver:

```java
package com.mysql.jdbc;
import java.sql.SQLException;

public class Driver extends NonRegisteringDriver implements java.sql.Driver {
  static {
    try {
      java.sql.DriverManager.registerDriver(new Driver());
    } catch (SQLException E) {
      throw new RuntimeException("Can't register driver!");
    }
  }

  /**
   * Construct a new driver and register it with DriverManager
   * @throws SQLException if a database error occurs.
   */
  public Driver() throws SQLException {
    // Required for Class.forName().newInstance()
  }
}
```

DriverManager：

```java
public class DriverManager {
  private final static CopyOnWriteArrayList<DriverInfo> registeredDrivers = new CopyOnWriteArrayList<DriverInfo>();

  //...
  static {
    loadInitialDrivers();
    println("JDBC DriverManager initialized");
  }
  //...

  public static synchronized void registerDriver(java.sql.Driver driver) throws SQLException {
    if (driver != null) {
      registeredDrivers.addIfAbsent(new DriverInfo(driver));
    } else {
      throw new NullPointerException();
    }
  }

  public static Connection getConnection(String url, String user, String password) throws SQLException {
    java.util.Properties info = new java.util.Properties();
    if (user != null) {
      info.put("user", user);
    }
    if (password != null) {
      info.put("password", password);
    }
    return (getConnection(url, info, Reflection.getCallerClass()));
  }
  //...
}
```

#### SLF4J

如前面所示：

> slf4j 其中有三个核心概念，logger，appender 和 encoder。
>
> 分别指这个日志记录器负责哪个类的日志，日志打印到哪里以及日志打印的格式。
>
> 三个纬度上可以有不同的实现，使用者可以在每一纬度上定义多个实现。

...





