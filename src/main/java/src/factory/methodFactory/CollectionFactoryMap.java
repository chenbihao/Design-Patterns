package src.factory.methodFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂的工厂
 *
 * @author: chenbihao
 * @create: 2021/9/20
 * @Description:
 * @History:
 */
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
