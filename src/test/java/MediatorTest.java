import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.mediator.*;
import src.memento.SnapshotHolder;
import src.memento.StringUtil;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class MediatorTest {

    @Test
    public void test() {

        // 创建中介者
        Mediator mediatorCenter = new MediatorCenter();

        // 创建组件
        ComponentUser componentUser = new ComponentUser(10);
        ComponentCar componentCar = new ComponentCar();
        ComponentVan componentVan = new ComponentVan();
        ComponentTruck componentTruck = new ComponentTruck();

        // 设置中介引用
        componentUser.setMediator(mediatorCenter);
        componentCar.setMediator(mediatorCenter);
        componentVan.setMediator(mediatorCenter);
        componentTruck.setMediator(mediatorCenter);

        // 把组件注册进中介者
        mediatorCenter.registerComponent(componentUser);
        mediatorCenter.registerComponent(componentCar);
        mediatorCenter.registerComponent(componentVan);

        Assertions.assertEquals("呼叫小车成功", mediatorCenter.callTheCar(Type.CAR));
        Assertions.assertEquals("暂无车辆可调度", mediatorCenter.callTheCar(Type.TRUCK));
        Assertions.assertEquals("呼叫小车成功", mediatorCenter.callTheCar(Type.CAR));
        Assertions.assertEquals("呼叫面包车成功", mediatorCenter.callTheCar(Type.VAN));

        mediatorCenter.registerComponent(componentTruck);
        Assertions.assertEquals("呼叫货车成功", mediatorCenter.callTheCar(Type.TRUCK));

        Assertions.assertEquals(3, componentUser.getMoney());
        Assertions.assertEquals(2, componentCar.getMoney());
        Assertions.assertEquals(2, componentVan.getMoney());
        Assertions.assertEquals(3, componentTruck.getMoney());
    }


}
