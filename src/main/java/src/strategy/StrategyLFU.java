package src.strategy;

/**
 * @author: chenbihao
 * @create: 2021/12/5
 * @Description:
 */
public class StrategyLFU implements  Strategy{
    @Override
    public void evict() {
        System.out.println("使用【最近最少使用】策略进行丢弃");
    }
}
