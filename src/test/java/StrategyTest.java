import org.junit.jupiter.api.Test;
import src.strategy.Cache;
import src.strategy.StrategyFIFO;
import src.strategy.StrategyLFU;
import src.strategy.StrategyLRU;
import src.template.Computer;
import src.template.GamingComputer;
import src.template.OfficeComputer;
import src.template.ProgrammingComputer;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class StrategyTest {

    @Test
    public void test() {

        Cache cache = new Cache();
        cache.add("添加");
        cache.add("缓存");
        cache.add("数据");

        cache.setEvictionStrategy(new StrategyFIFO());
        cache.get();
        cache.evict();
        cache.evict();

        cache.setEvictionStrategy(new StrategyLRU());
        cache.evict();

        cache.setEvictionStrategy(new StrategyLFU());
        cache.evict();

    }



    public void asd(){

//        new Comparator();
//        Collections.sort();
//        Arrays.sort()

    }




}
