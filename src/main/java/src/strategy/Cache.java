package src.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chenbihao
 * @create: 2021/12/5
 * @Description: Context 上下文
 */
public class Cache {

    private Map cacheData = new HashMap<>();

    // 在上下文类中添加一个成员变量用于保存对于策略对象的引用。
    private Strategy evictionStrategy;

    // 提供设置器以修改该成员变量。
    public void setEvictionStrategy(Strategy evictionStrategy){
        this.evictionStrategy = evictionStrategy;
    }

    // 通过策略接口同策略对象进行交互
    public void evict() {
        evictionStrategy.evict();
    }

    public void add(String value) {
        // ...
    }

    public String get() {
        // ...
        return null;
    }

}
