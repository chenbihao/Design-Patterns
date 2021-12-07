import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.chainOfResponsibility.HandlerA;
import src.chainOfResponsibility.HandlerB;
import src.chainOfResponsibility.HandlerChain;
import src.state.Kettle;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class StateTest {

    @Test
    public void test() {

        Kettle kettle = new Kettle();

        Assertions.assertEquals("空的状态",kettle.getState().toString());

        Assertions.assertEquals("没水...",kettle.pourWater());
        Assertions.assertEquals("加水成功",kettle.addWater());

        Assertions.assertEquals("冷水状态",kettle.getState().toString());

        Assertions.assertEquals("有水了，别加了",kettle.addWater());
        Assertions.assertEquals("煮水成功",kettle.boilWater());

        Assertions.assertEquals("热水状态",kettle.getState().toString());

        Assertions.assertEquals("有水了，别加了",kettle.addWater());
        Assertions.assertEquals("保温中...",kettle.boilWater());
        Assertions.assertEquals("倒水成功（开水）",kettle.pourWater());

        Assertions.assertEquals("空的状态",kettle.getState().toString());

    }

}
