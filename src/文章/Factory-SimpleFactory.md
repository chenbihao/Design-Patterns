# 简单工厂（Simple Factory）

## 介绍

简单工厂模式描述了一个类，它拥有一个包含大量条件语句的构建方法，可根据方法的参数来选择对何种产品进行初始化并将其返回。

## 适用场景

当每个对象的创建逻辑都比较简单的时候，将多个对象的创建逻辑放到一个工厂类中。

## 优缺点

优点：
- 代码简单
- 避免耦合

## 与其他模式的关系

- 大多数情况下，简单工厂是引入工厂方法或抽象工厂模式时的一个中间步骤。


## 实现方式

1. 新建一个工厂类。
2. 新建方法，通过入参判断返回生成的对象。

---
## 示例

简单的用 if 判断参数，并生成对象给调用者。

```java
public class CollectionFactory {
    public static Collection create(String type) throws Exception {
        switch (type) {
            case "list":
                return new ArrayList();
            case "linked":
                return new LinkedList();
            case "deque":
                return new ArrayDeque();
            default:
                throw new Exception("传递的用户类型错误。");
        }
    }
}
```

如果对象是可复用的，也可以把对象事先创建好并缓存起来：

```java
public class RuleConfigParserFactory {
    private static final Map<String, RuleConfigParser> cachedParsers = new HashMap<>();
    
    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("xml", new XmlRuleConfigParser());
        cachedParsers.put("yaml", new YamlRuleConfigParser());
        cachedParsers.put("properties", new PropertiesRuleConfigParser());
    }   
    
    public static IRuleConfigParser createParser(String configFormat) {
        if (configFormat == null || configFormat.isEmpty()) {
            return null;//返回null还是IllegalArgumentException全凭你自己说了算
        }
        IRuleConfigParser parser = cachedParsers.get(configFormat.toLowerCase());
        return parser;
    }
}
```

---
## 实例

JDK类库中广泛使用了简单工厂模式，如工具类 `java.text.DateFormat`，它用于格式化一个本地日期或者时间。
```
java.text.DateFormat#getDateInstance()
java.text.DateFormat#getDateInstance(int style)
java.text.DateFormat#getDateInstance(int style,Locale locale)
```

密钥生成器：
```
javax.crypto.KeyGenerator#getInstance(String algorithm)
```

创建密码器:
```
javax.crypto.Cipher#getInstance(String transformation);
```

