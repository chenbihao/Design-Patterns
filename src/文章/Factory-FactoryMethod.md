# 工厂方法（Factory Method）

## 介绍

工厂方法是一种创建型设计模式， 其在父类中提供一个创建对象的方法， 允许子类决定实例化对象的类型。

## 适用场景

当每个对象的 **创建逻辑** 都比较 **复杂** 的时候，

为了避免设计一个过于庞大的简单工厂类时，将创建逻辑拆分得更细，

让每个对象的创建逻辑独立到各自的工厂类中。

> 当你在编写代码的过程中， 如果无法预知对象确切类别及其依赖关系时， 可使用工厂方法。

> 如果你希望用户能扩展你软件库或框架的内部组件， 可使用工厂方法。

> 如果你希望复用现有对象来节省系统资源， 而不是每次都重新创建对象， 可使用工厂方法。

## 实现方式

工厂方法模式建议使用特殊的工厂方法代替对于对象构造函数的直接调用。 

对象仍将通过 new 运算符创建，只是该运算符改在工厂的方法中调用罢了。

> 1. 让所有产品都遵循同一接口。 该接口必须声明对所有产品都有意义的方法。
> 2. 在创建类中添加一个空的工厂方法。 该方法的返回类型必须遵循通用的产品接口。
> 3. 在创建者代码中找到对于产品构造函数的所有引用。 将它们依次替换为对于工厂方法的调用， 同时将创建产品的代码移入工厂方法。 你可能需要在工厂方法中添加临时参数来控制返回的产品类型。
> 5. 现在， 为工厂方法中的每种产品编写一个创建者子类， 然后在子类中重写工厂方法， 并将基本方法中的相关创建代码移动到工厂方法中。 
> 6. 如果应用中的产品类型太多， 那么为每个产品创建子类并无太大必要， 这时你也可以在子类中复用基类中的控制参数。 
> 7. 如果代码经过上述移动后， 基础工厂方法中已经没有任何代码， 你可以将其转变为抽象类。 如果基础工厂方法中还有其他语句， 你可以将其设置为该方法的默认行为。


## 优缺点

优点：
- 避免耦合
- 单一职责
- 开闭原则

缺点：
- 代码变得复杂


## 示例

工厂接口与工厂

```java
public interface ICollectionFactory {
    Collection createCollection();
}
public class ListFactory implements ICollectionFactory {
    @Override
    public Collection createCollection() {
        // 可以把复杂的初始化过程放在这里
        return new ArrayList();
    }
}
public class LinkedFactory implements ICollectionFactory {
    @Override
    public Collection createCollection() {
        // 可以把复杂的初始化过程放在这里
        return new LinkedList();
    }
}
public class DequeFactory implements ICollectionFactory {
    @Override
    public Collection createCollection() {
        // 可以把复杂的初始化过程放在这里
        return new ArrayDeque();
    }
}
```

工厂的工厂

```java
public class CollectionFactoryMap {
    private static final Map<String, ICollectionFactory> cachedFactories = new HashMap<>();

    static {
        cachedFactories.put("list", new ListFactory());
        cachedFactories.put("linked", new LinkedFactory());
        cachedFactories.put("deque", new DequeFactory());
    }

    public static ICollectionFactory getCollectionFactory(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        ICollectionFactory factory = cachedFactories.get(type.toLowerCase());
        return factory;
    }
}
```

## 实例




