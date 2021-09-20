package src.factory.methodFactory;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 工厂
 * @author: chenbihao
 * @create: 2021/9/20
 * @Description:
 * @History:
 */
public class LinkedFactory implements ICollectionFactory {
    @Override
    public Collection createCollection() {
        // 可以把复杂的初始化过程放在这里
        return new LinkedList();
    }
}
