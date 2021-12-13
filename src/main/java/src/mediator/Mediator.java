package src.mediator;

/**
 * @author: chenbihao
 * @create: 2021/12/13
 * @Description: 中介者接口
 */
public interface Mediator {

    /**
     * 把组件 注册到 中介者
     */
    void registerComponent(Component component);

    // 呼叫车辆
    String callTheCar(Type type);

    // 收费
    void charge(int i);


}
