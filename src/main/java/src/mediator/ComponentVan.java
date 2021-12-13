package src.mediator;

/**
 * @author: chenbihao
 * @create: 2021/12/13
 * @Description: 组件之一：面包车
 */
public class ComponentVan implements Component {

    private Mediator mediator;
    private int money;


    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Type getType() {
        return Type.VAN;
    }

    // 收费 并返回余额
    public void charge() {
        mediator.charge(2);
        money += 2;
    }
    public int getMoney() {
        return money;
    }
}
