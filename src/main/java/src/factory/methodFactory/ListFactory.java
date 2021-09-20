package src.factory.methodFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 工厂
 * @author: chenbihao
 * @create: 2021/9/20
 * @Description:
 * @History:
 */
public class ListFactory implements ICollectionFactory {
    @Override
    public Collection createCollection() {
        // 可以把复杂的初始化过程放在这里
        return new ArrayList();
    }
}
