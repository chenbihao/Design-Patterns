package src.strategy;

/**
 * @author: chenbihao
 * @create: 2021/12/5
 * @Description:
 */
public class StrategyFIFO implements  Strategy{
    // 将算法逐一抽取到各自的类中，它们都必须实现策略接口。
    @Override
    public void evict() {
        System.out.println("使用【先进先出】策略进行丢弃");
    }
}
