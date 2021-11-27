# 门面模式（Facade）

## 介绍

门面模式（外观模式）是一种结构型设计模式，能为程序库、框架或其他复杂类提供一个简单的接口。


## 适用场景

- 提供一组更简单易用、更高层的接口，隐藏系统的复杂性。
  - 可以解决易用性问题
  - 可以解决多次调用的性能问题
  - 可以解决简单的分布式事务问题
- ...


## 优缺点

优点：

- 代码独立于子系统
- 接口隔离原则
- 最少知识原则（迪米特法则）

缺点：

- 外观可能成为与程序中所有类都耦合的上帝对象


## 与其他模式的关系

- **适配器**是做接口转换，解决的是原接口和目标接口不匹配的问题。**门面模式**做接口整合，解决的是多接口调用带来的问题。
- 只需对客户端隐藏创建过程的话，可以用**抽象工厂模式**来代替**门面模式**。
- 一般只要一个**门面**，可以转换为**单例**。
- **门面**与**代理**的相似之处在于它们都缓存了一个复杂实体并自行对其进行初始化。**代理**与其服务对象遵循同一接口使得自己和服务对象可以互换。


## 实现方式

1. 在一个新的外观类中声明并实现该接口。（如果客户端代码没有对子系统进行初始化，也没有对其后续生命周期进行管理，那么外观必须完成此类工作）
2. 客户端代码仅通过外观来与子系统进行交互。（此后客户端代码将不会受到任何由子系统代码修改而造成的影响）
3. 如果外观变得过于臃肿，可以考虑将其部分行为抽取为一个新的专用外观类。


--- 

## 示例

```java
public class Facade {
    /**
     * 传入数据，生成 PDF 文件
     */
    public static DataPdf getPdfData(Data data) {
        // 数据通过模板引擎生成 word
        // 即数据通过模板引擎，套用模板文件（模板.docx）生成出一个新的word文件
        DataWord dataWord = new DataWord(data, "./xxx/xxxx/模板.docx");
        // word 转换成 PDF
        DataPdf dataPdf = new DataPdf(dataWord);
        // PDF 进行加密处理
        dataPdf.encrypt("asd123!@#");

        return dataPdf;
    }
}

public class Data {
    private String title;
    private String content;
    // ... 省略 get set
}

public class DataWord {
    private String title;
    private String content;
    private String template;

    public DataWord(Data data,String template) {
        this.title = data.getTitle();
        this.content = data.getContent();
        this.template = template;
    }
    //... 省略 get
}

public class DataPdf {

    private String title;
    private String content;
    private String pwd;

    public DataPdf(DataWord word) {
        this.title = word.getTitle();
        this.content = word.getContent();
    }

    public void encrypt(String pwd) {
        this.pwd = pwd;
        System.out.println("对当前文档进行加密，密码：" + pwd);
    }

    //... 省略 get
}

```

测试代码

```java
public class FacadeTest {

    @Test
    public void test() {

        Data data = new Data();
        data.setTitle("标题");
        data.setContent("内容");

        DataPdf pdfData = Facade.getPdfData(data);

        Assertions.assertEquals("标题",pdfData.getTitle());
        Assertions.assertEquals("内容",pdfData.getContent());
        Assertions.assertEquals("asd123!@#",pdfData.getPwd());
    }
}
```

--- 



