package src.strategy;

/**
 * @author: chenbihao
 * @create: 2021/12/5
 * @Description:
 */
public class StrategyLRU implements Strategy {
    @Override
    public void evict() {
        System.out.println("使用【最近最久未使用】策略进行丢弃");
    }
}
