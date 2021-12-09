# 访问者模式（Visitor）


## 介绍

**访问者模式**是一种行为设计模式。

它允许一个或者多个操作应用到一组对象上，**解耦操作和对象本身**。

访问者主要目的在于：在单分派的语言中实现**双分派**的功能 （Java是单分派的）。

## 适用场景

- **将对象与操作解耦**，将这些业务操作抽离出来。
- 针对复杂对象结构中的**所有元素**执行某些操作。
- 访问者模式可以用来梳理**辅助行为**的业务逻辑。
- 当某个行为仅在类层次结构中的一些类中有意义，而在其他类中没有意义时，可使用该模式。
- ...

## 优缺点

优点：

- **开闭原则**：需要添加新的操作时只需增加新的访问者类。
- **单一职责原则**：可将同一行为的不同版本移到同一个类中。
- 访问者对象可以在与各种对象交互时收集一些有用的信息。当你想要遍历一些复杂的对象结构（例如对象树），并在结构中的每个对象上应用访问者时，这些信息可能会有所帮助。

缺点：

- 代码**复杂度**暴增。
- 每次在元素层次结构中添加或移除一个类时，你都要**更新所有访问者**。
- 在访问者同某个元素进行**交互时**，它们可能没有访问元素**私有**成员变量和方法的必要**权限**。

## 与其他模式的关系

- 可以使用**访问者**对整个**组合模式**树执行操作。
- 可以同时使用**访问者**和**迭代器**模式来遍历复杂数据结构，并对其中的元素执行所需操作，即使这些元素所属的类完全不同。

## 实现方式

1. 在访问者接口中**声明一组 “访问” 方法**，分别对应每个具体元素类。
2. **声明元素接口**。
    - 该方法必须接受访问者对象作为参数。
3. 在所有具体元素类中**实现接收方法**。
    - 这些方法必须将调用重定向到当前元素对应的访问者对象中的访问者方法上。
4. 为每个无法在元素层次结构中实现的行为创建一个具体访问者类并**实现所有的访问者方法**。
5. 客户端必须创建访问者对象并**通过 “接收” 方法**将其**传递**给元素。

- 元素类只能通过访问者接口与访问者进行交互。
- 访问者必须知晓所有的具体元素类，因为这些类在访问者方法中都被作为参数类型引用。

--- 

## 示例

访问者的示例可能比较绕，所以按照实现步骤来。

假设现在我有个图形类接口和各种图形（点、线、面）的实现类，这些类是已经实现好了的：

```java
// 接口
public interface Shape {
    // 已经实现了的功能 [获取类型]
    String getShapeType();
    // 已经实现了的功能 [获取坐标]
    List<String> getCoordinates();
}

// 点 (单纯一个点坐标，没有线段)
public class ShapePoint implements Shape {
    // 坐标
    private String coordinate;
    // 构造器
    public ShapePoint(String coordinate) {
        this.coordinate = coordinate;
    }
    // 已经实现的方法
    @Override
    public String getShapeType() {
        return "point";
    }
    // 已经实现的方法
    @Override
    public List<String> getCoordinates() {
        return new ArrayList() {{add(coordinate);}};
    }
}

// 段 (多个坐标，连成不闭合的线段)
public class ShapeSection implements Shape {
    private List<String> coordinates;
    public ShapeSection(List<String> coordinates) {
        this.coordinates = coordinates;
    }
    @Override
    public String getShapeType() {
        return "section";
    }
    @Override
    public List<String> getCoordinates() {
        return coordinates;
    }
}

// 多边形 (多个坐标，连成闭合的线段)
public class ShapePolygon implements Shape {
    private List<String> coordinates;
    public ShapePolygon(List<String> coordinates) {
        this.coordinates = coordinates;
    }
    @Override
    public String getShapeType() {
        return "polygon";
    }
    @Override
    public List<String> getCoordinates() {
        return coordinates;
    }
}
```

现在有第三方想要添加功能，分别是：

- 获取中间值的坐标
- 获取边数

第三方可以实现的方式：

- 直接在 Shape 接口添加他们要的功能。（作为库的维护者，并不希望他们乱动我们现有的代码）
- 自己编辑维护一个行为逻辑去判断。（无法利用接口的优势、需要进行显示的检查类型，并且获取运行时类型可能对性能有影响）
- **使用访问者模式**

如何改成访问者模式？

访问者接口类:

```java
/**
 * 对应步骤 1. 在访问者接口中声明一组 “访问” 方法，分别对应每个具体元素类。
 * 这里利用访问者的方法，来识别调用的是什么类型的 Shape
 */
public interface Visitor {
    Object visitPoint(ShapePoint shapePoint);
    Object visitSection(ShapeSection shapeSection);
    Object visitPolygon(ShapePolygon shapePolygon);
}
```

原本的类需要一点小改动：

```java
public interface Shape {
    // 已经实现了的功能 [获取类型]
    String getShapeType();
    // 已经实现了的功能 [获取坐标]
    List<String> getCoordinates();
    /**
     *  对应步骤 2. 声明元素接口。该方法必须接受访问者对象作为参数。
     *  这个是为了访问者加的接口方法，在单分派的语言中实现双分派的功能 （Java是单分派的）
     */
    Object accept(Visitor visitor);
}

public class ShapePoint implements Shape {
    // 坐标
    private String coordinate;
    // 构造器
    public ShapePoint(String coordinate) {
        this.coordinate = coordinate;
    }
    // 之前实现的方法
    @Override
    public String getShapeType() {
        return "point";
    }
    // 之前实现的方法
    @Override
    public List<String> getCoordinates() {
        return new ArrayList() {{add(coordinate);}};
    }

    /**
     *  对应步骤 3. 在所有具体元素类中实现接收方法。 
     *  这些方法必须将调用重定向到当前元素对应的访问者对象中的访问者方法上。
     *  这个是为了访问者加的方法，可以在单分派的语言中实现双分派的功能 （Java是单分派的）
     */
    @Override
    public Object accept(Visitor visitor) {
        return visitor.visitPoint(this);
    }
}

// ...   ShapeSection 与 ShapePolygon 一同实现 accept 方法，这里省略
```

访问者实现：

```java
/**
 *  访问者实现类： 获取中间值
 *  对应步骤 4. 为每个无法在元素层次结构中实现的行为创建一个具体访问者类并实现所有的访问者方法。
 */
public class VisitorMiddleCoordinates implements Visitor {
    @Override
    public Object visitPoint(ShapePoint shapePoint) {
        // 直接返回点坐标
        return shapePoint.getCoordinates().get(0);
    }
    @Override
    public Object visitSection(ShapeSection shapeSection) {
        // 返回线段的中间点
        List<String> coordinates = shapeSection.getCoordinates();
        int middle = coordinates.size() / 2;
        return coordinates.get(middle);
    }
    @Override
    public Object visitPolygon(ShapePolygon shapePolygon) {
        // 返回线段的起点（也是终点）
        return shapePolygon.getCoordinates().get(0);
    }
}

/**
 *  访问者实现类： 获取 边数
 *  对应步骤 4. 为每个无法在元素层次结构中实现的行为创建一个具体访问者类并实现所有的访问者方法。
 */
public class VisitorNumSides implements Visitor {
    @Override
    public Object visitPoint(ShapePoint shapePoint) {
        // 点 没有边数
        return 0;
    }
    @Override
    public Object visitSection(ShapeSection shapeSection) {
        // 段 边数=坐标数-1
        return shapeSection.getCoordinates().size() - 1;
    }
    @Override
    public Object visitPolygon(ShapePolygon shapePolygon) {
        // 段 边数=坐标数
        return shapePolygon.getCoordinates().size();
    }
}
```

测试代码

```java
public class VisitorTest {
    @Test
    public void test() {
        String pointCoordinates = "坐标点1";
        ArrayList<String> sectionCoordinates = new ArrayList<>(Arrays.asList("坐标点1", "坐标点2", "坐标点3"));
        ArrayList<String> polygonCoordinates = new ArrayList<>(Arrays.asList("坐标点1", "坐标点2", "坐标点3"));

        ShapePoint point = new ShapePoint(pointCoordinates);
        ShapeSection section = new ShapeSection(sectionCoordinates);
        ShapePolygon polygon = new ShapePolygon(polygonCoordinates);

        /**
         *  对应步骤 5. 客户端必须创建访问者对象并通过 “接收” 方法将其传递给元素。
         */
        
        // 获取中间点
        VisitorMiddleCoordinates visitorMiddleCoordinates = new VisitorMiddleCoordinates();
        Assertions.assertEquals("坐标点1", point.accept(visitorMiddleCoordinates));
        Assertions.assertEquals("坐标点2", section.accept(visitorMiddleCoordinates));
        Assertions.assertEquals("坐标点1", polygon.accept(visitorMiddleCoordinates));

        // 获取边数
        VisitorNumSides visitorNumSides = new VisitorNumSides();
        Assertions.assertEquals(0, point.accept(visitorNumSides));
        Assertions.assertEquals(2, section.accept(visitorNumSides));
        Assertions.assertEquals(3, polygon.accept(visitorNumSides));
    }
}
```

## 实例

```
java.nio.file 的 FileVisitor 与 SimpleFileVisitor

javax.lang.model.type 的 TypeMirror 与 TypeVisitor

javax.lang.model.element 的 Element 与 ElementVisitor

javax.lang.model.element 的 AnnotationValue 与 AnnotationValueVisitor
```



