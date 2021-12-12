# 解释器模式（Interpreter）

## 介绍

**解释器模式**是一种行为设计模式。

解释器模式为某个语言定义它的**语法表示**，并定义一个解释器用来处理这个语法。

## 适用场景

- 构建一个“语言”解释器。
- 实现编译器、规则引擎、正则表达式等功能。
- 将一些重复出现的问题用简单的语法来进行表达。
- 将一个需要解释执行的语言中的句子表示为一个抽象语法树。
- ...

## 优缺点

优点：

- 开闭原则。
- 单一职责原则。

缺点：

- 可以利用的场景比较少。
- 如果文法复杂的话可能较难维护。

## 与其他模式的关系

- ...

## 实现方式

解释器模式代码实现的核心思想就是将语法解析的工作拆分到各个小类中，以此来避免大而全的解析类。

解释器模式的代码**实现比较灵活**，**没有固定的模板**，在我的示例中的做法大体可以拆分为：

1. 定义表达式接口。
2. 实现表达式具体类。
3. 实现解释器上下文类，作为“启动器”，负责调用表达式进行。
4. 客户端通过解释器上下文进行调用。

--- 


## 举例

我这里的示例代码主要实现一个类似 poi-tl **模板引擎**的功能。

什么是 poi-tl 呢？

poi-tl是一个基于 Apache POI 的 Word 模板引擎，给它一个 word 模板文件以及数据内容，即可生成一个基于模板文件更新的 word 文件。

举个使用的例子：

``` java
// 新建一个模板文件：template.docx ，并且在里面添加内容：{{title}}
// poi-tl 的所有的标签都是以 {{ 开头，以 }} 结尾
{{title}}
```

```java
XWPFTemplate template=XWPFTemplate.compile("template.docx").render(
    new HashMap<String, Object>(){{
        put("title","Hi, poi-tl Word模板引擎");
}});
template.writeAndClose(new FileOutputStream("output.docx")); 
```

```java
// 输出的 output.docx 文件内容：
Hi,poi-tl Word模板引擎
```

## 示例

模仿 poi-tl 功能的实现：

表达式抽象类：

```java
public interface Expression {
    String interpret(String sourceStr, Map<String, String> data);
}
```

表达式具体实现类：

```java
/**
 * 表达式实现：文本表达式
 */
public class ExpressionStr implements Expression {

    private static String prefix = "{{";
    private static String suffix = "}}";

    @Override
    public String interpret(String sourceStr, Map<String, String> data) {

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String targerStr = prefix + entry.getKey() + suffix;
            sourceStr = StrUtil.replace(sourceStr, targerStr, entry.getValue());
        }
        return sourceStr;
    }
}

/**
 * 表达式实现：表达式实现：图片表达式
 */
public class ExpressionPic implements Expression {

    private static String prefix = "{{@";
    private static String suffix = "}}";

    @Override
    public String interpret(String sourceStr, Map<String, String> data) {

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String targerStr = prefix + entry.getKey() + suffix;
            String imageStr = "![" + entry.getKey() + "](assets/" + entry.getValue() + "]";
            sourceStr = StrUtil.replace(sourceStr, targerStr, imageStr);
        }
        return sourceStr;
    }
}
```

解释器上下文：

```java
public class Interpreter {

    // 源文本
    private String sourceStr;
    // 表达式
    private Map<Expression, Map<String, String>> expressions = new HashMap<>();

    public Interpreter(String sourceStr) {
        this.sourceStr = sourceStr;
    }

    /**
     * 添加表达式
     */
    public void addExpression(Expression expression, Map<String, String> data) {
        expressions.put(expression, data);
    }

    /**
     * 执行解释
     */
    public String interpreter() {

        expressions.forEach((expression, data) -> {
            sourceStr = expression.interpret(sourceStr, data);
        });

        return sourceStr;
    }
}
```

测试代码

```java
public class InterpreterTest {
    @Test
    public void test() {

        String content = "这是一篇文章，文章标题为{{title}}，内容主要是讲{{subject}}，" +
                "目前评分{{score}}分，附带题图{{@picture}}，内容图{{@picture2}}";

        Interpreter interpreter = new Interpreter(content);

        // 套用文字模板进行渲染
        ExpressionStr expressionStr = new ExpressionStr();
        interpreter.addExpression(expressionStr, new HashMap() {{
            put("title", "解释器模式");
            put("subject", "模仿poi-tl的渲染方式展示解释器模式");
            put("score", "3.7");
        }});

        // 套用图片模板进行渲染
        ExpressionPic expressionPic = new ExpressionPic();
        interpreter.addExpression(expressionPic, new HashMap() {{
            put("picture", "/picture/1.jpg");
            put("picture2", "/picture/2.jpg");
        }});

        // 进行解释
        String result = interpreter.interpreter();
        Assertions.assertEquals("这是一篇文章，文章标题为解释器模式，" +
                        "内容主要是讲模仿poi-tl的渲染方式展示解释器模式，" +
                        "目前评分3.7分，附带题图![picture](assets//picture/1.jpg]，" +
                        "内容图![picture2](assets//picture/2.jpg]"
                , result);
    }
}
```

--- 

## 实例

### Mybatis SqlNode

利用解释器模式来解析动态 SQL

表达式抽象类：

```java
public interface SqlNode {
    /**
     * 将解析出来的值应用到context中
     */
    boolean apply(DynamicContext context);
}
```

解释器上下文：

```java
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }

    /**
     * 通过遍历所有的SqlNode，获取sql
     */
    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

}
```

SqlNode的具体实现，

例如在 `scripting/xmltags` 文件夹下的 MixedSqlNode、StaticTextSqlNode、IfSqlNode 等：

```java
/**
 * 保存一个 CRUD 节点下所含有的所有 SqlNode 集合，方便统一管理以及解析
 */
public class MixedSqlNode implements SqlNode {
    // 记录sql节点中的所有SQL片段
    private final List<SqlNode> contents;

    public MixedSqlNode(List<SqlNode> contents) {
        this.contents = contents;
    }

    @Override
    public boolean apply(DynamicContext context) {
        // 循环调用contents集合中的所有SqlNode对象的apply方法
        contents.forEach(node -> node.apply(context));
        return true;
    }
}

/**
 * 最简单的SqlNode，功能仅仅就是将自身记录的text拼接到context上下文中
 */
public class StaticTextSqlNode implements SqlNode {
    private final String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(DynamicContext context) {
        context.appendSql(text);
        return true;
    }
}

/**
 * 最常用的 if 标签，满足条件则合并到 contents 中，不满足则抛弃
 */
public class IfSqlNode implements SqlNode {
    private final ExpressionEvaluator evaluator;
    private final String test;
    private final SqlNode contents;

    public IfSqlNode(SqlNode contents, String test) {
        this.test = test;
        this.contents = contents;
        this.evaluator = new ExpressionEvaluator();
    }

    @Override
    public boolean apply(DynamicContext context) {
        if (evaluator.evaluateBoolean(test, context.getBindings())) {
            contents.apply(context);
            return true;
        }
        return false;
    }
}

// ... 其他略
```



