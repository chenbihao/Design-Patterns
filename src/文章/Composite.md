# 组合模式（Composite）

## 介绍

组合模式是一种结构型设计模式，组合模式将一组对象组织（Compose）成树形结构，以表示一种”部分-整体”的层次结构。

组合模式让客户端可以统一单个对象和组合对象的处理逻辑。


## 适用场景

- 实现树状对象结构
- 以相同方式处理简单或复杂的元素
- ...


## 优缺点

优点：

- 可以利用多态和递归机制更方便地使用复杂树结构。
- 开闭原则：无须修改现有代码即可添加新元素。

缺点：

- 当差异较大时比较难划分接口。


## 与其他模式的关系

- **组合模式**通常和**责任链模式**结合使用。
- 可以在创建复杂**组合**树时使用**生成器模式**，可使其构造步骤以递归的方式运行。
- 可以使用**迭代器模式**来遍历组合树。
- 可以使用**访问者模式**对整个组合树执行操作。
- 可以使用**享元模式**实现组合树的共享叶节点以节省内存。
- 可以使用**原型模式**来复制大量使用**组合**或**装饰**的对象。



## 实现方式

1. 声明组件接口及其一系列方法。
2. 创建一个叶节点类表示简单元素。 
3. 创建一个容器类表示复杂元素。
4. 在容器中定义添加和删除子元素的方法。


--- 

## 示例

```java
/**
 * 抽象类
 */
public abstract class FileSystemNode {
    protected String name;
    public FileSystemNode(String name) {
        this.name = name;
    }
    // 文件总数
    public abstract int count();
}

/**
 * 文件
 */
public class FileNode extends FileSystemNode {

    public FileNode(String name) {
        super(name);
    }

    @Override
    public int count() {
        return 1;
    }

}

/**
 * 文件夹
 */
public class DirectoryNode extends FileSystemNode {
    // Node 列表集合
    private List<FileSystemNode> subNodes = new ArrayList<>();
    public DirectoryNode(String name) {
        super(name);
    }
    
    @Override
    public int count() {
        // 遍历统计文件数量
        int numOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            numOfFiles += fileOrDir.count();
        }
        return numOfFiles;
    }

    public void addSubNode(FileSystemNode node) {
        subNodes.add(node);
    }
    public void removeSubNode(FileSystemNode node) {
        subNodes.remove(node);
    }
    public void clear() {
        subNodes.clear();
    }
}
```

测试代码

```java
public class CompositeTest {
    @Test
    public void test() {
        DirectoryNode d1 = new DirectoryNode("目录1");
        DirectoryNode d2 = new DirectoryNode("目录2");
        DirectoryNode d3 = new DirectoryNode("目录3");
        FileNode fileA = new FileNode("文件A");
        FileNode fileB = new FileNode("文件B");
        FileNode fileC = new FileNode("文件C");

        d1.addSubNode(d2);
        d1.addSubNode(fileA);

        d2.addSubNode(d3);
        d3.addSubNode(fileB);
        d3.addSubNode(fileC);

        Assertions.assertEquals(3,d1.count());
        d3.removeSubNode(fileC);
        Assertions.assertEquals(2,d1.count());
        d2.clear();
        Assertions.assertEquals(1,d1.count());
        d1.addSubNode(fileC);
        Assertions.assertEquals(2,d1.count());
    }
}
```

--- 



