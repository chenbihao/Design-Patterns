package src.mediator;

/**
 * @author: chenbihao
 * @create: 2021/12/13
 * @Description: 组件之一：货车
 */
public class ComponentTruck implements Component {

    private Mediator mediator;
    private int money;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Type getType() {
        return Type.TRUCK;
    }

    // 收费 并返回余额
    public void charge() {
        mediator.charge(3);
        money += 3;
    }
    public int getMoney() {
        return money;
    }
}
