import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.state.Kettle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
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
