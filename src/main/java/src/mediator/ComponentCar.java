package src.mediator;

/**
 * @author: chenbihao
 * @create: 2021/12/13
 * @Description: 组件之一：小车
 */
public class ComponentCar implements Component {

    private Mediator mediator;
    private int money;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Type getType() {
        return Type.CAR;
    }

    // 收费 并返回余额
    public void charge() {
        mediator.charge(1);
        money += 1;
    }

    public int getMoney() {
        return money;
    }
}
