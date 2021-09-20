package src.factory.simpleFactory;

import java.util.*;

/**
 * 容器工厂
 * @author: chenbihao
 * @create: 2021/9/20
 * @Description:
 * @History:
 */
public class CollectionFactory {
    public static Collection createCollection(String type) throws Exception {
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
