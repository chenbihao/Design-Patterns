import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.command.*;
import src.strategy.Cache;
import src.strategy.StrategyFIFO;
import src.strategy.StrategyLFU;
import src.strategy.StrategyLRU;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class CommandTest {

    @Test
    public void test() {

        // 游戏引擎，负责底层逻辑操作
        GameEngine gameEngine = new GameEngine();

        Assertions.assertEquals("敌方扣血",new CommandQ(gameEngine).execute());
        Assertions.assertEquals("敌方眩晕",new CommandW(gameEngine).execute());
        Assertions.assertEquals("敌方沉睡",new CommandE(gameEngine).execute());
        Assertions.assertEquals("敌方扣血敌方恐惧",new CommandR(gameEngine).execute());

        /**
         * 利用命令模式，可以轻松的实现队列功能（这里未实现）
         * 例如客户端输入一个连招命令 “WQER” 的文本，触发者将命令解码后按照顺序执行
         */

    }



    public void asd(){
    }




}
