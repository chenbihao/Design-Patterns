# 迭代器模式（Iterator）

## 介绍

**迭代器模式**是一种行为设计模式，

也叫作**游标模式**（Cursor Design Pattern）

能在不暴露集合底层表现形式（数组、链表、树、图、跳表等）的情况下**遍历集合**中所有的元素。

## 适用场景

- 减少程序中重复的遍历代码。
- 对客户端隐藏数据结构复杂性。
- 遍历不同甚至无法预知的数据结构。
- ...

## 优缺点

优点：

- 开闭原则。
- 单一职责原则。
- 可以并行遍历同一集合。
- 可以暂停遍历并在需要时继续。
- 针对某一数据结构类型可以实现多种不同的迭代方式。

缺点：

- 可能比直接遍历效率低。
- 增加复杂度，简单集合遍历用迭代器小题大做。

## 与其他模式的关系

- 可以使用**迭代器模式**来遍历**组合模式**树。
- 可以同时使用**工厂方法模式**和**迭代器**来让子类集合返回不同类型的迭代器，并使得迭代器与集合相匹配。
- 可以同时使用**访问者模式**和**迭代器**来遍历复杂数据结构，并对其中的元素执行所需操作，即使这些元素所属的类完全不同。

## 实现方式

1. 声明迭代器接口
    - 至少提供获取下一个元素的方法
2. 声明集合接口并描述一个获取迭代器的方法
3. 实现具体迭代器类
    - 必须与单个集合实体组合
4. 在集合类中实现集合接口，提供创建迭代器的快捷方式。

--- 

## 实例

### JDK

#### java.util.Iterator

使用例子

```java
public class IteratorTest {
    @Test
    public void test() {

        List<String> bookList = new ArrayList<>();
        bookList.add("重构2.0");
        bookList.add("设计模式之美");
        bookList.add("数据结构与算法之美");

        /**
         *  第一种遍历方式：for循环
         */
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i));
        }

        /**
         *   第二种遍历方式：foreach循环
         *
         *   语法糖，底层是基于迭代器来实现的
         */
        for (String name : bookList) {
            System.out.println(name);
        }

        /**
         *   第三种遍历方式：迭代器遍历
         *
         *   迭代器主要有2种定义方式：
         *      1. public interface Iterator { boolean hasNext(); void next(); E currentItem();}
         *      2. public interface Iterator { boolean hasNext(); E next();}
         *
         *   Java 中的迭代器接口是第二种定义方式，next()既移动游标又返回数据。
         *   第一种方式的优点是可以重复获取，第二种方式的优点是一行代码就可以搞定迭代。
         */
        Iterator<String> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
```

迭代器接口

```java
public interface Iterator<E> {

    /**
     * 下一个不为空
     */
    boolean hasNext();

    /**
     * 返回迭代中的下一个元素
     */
    E next();

    /**
     * 默认抛异常，由实现类重写
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * 处理剩余的元素，等同于 while 循环+处理。
     * JDK8 的 Lambda 表达式写法
     */
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

容器接口

```java
public interface List<E> extends Collection<E> {
    // ... 省略

    // 提供创建迭代器的快捷方式
    Iterator<E> iterator();

    // ... 省略
}
```

迭代器与容器的具体实现

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    // ... 省略

    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;       // 要返回的下一个元素的索引
        int lastRet = -1; // 返回的最后一个元素的索引-如果没有，则为1
        int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            final int size = ArrayList.this.size;
            int i = cursor;
            if (i < size) {
                final Object[] es = elementData;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && modCount == expectedModCount; i++)
                    action.accept(elementAt(es, i));
                // update once at end to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /**
     * ArrayList 里还有另一个基于ListIterator 的 ListItr 实现 :
     */
    private class ListItr extends Itr implements ListIterator<E> {
        // ... 省略
    }

    public ListIterator<E> listIterator(int index) {
        rangeCheckForAdd(index);
        return new ListItr(index);
    }
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }
    
    // ... 省略
}

```
