package src.mediator;

/**
 * @author: chenbihao
 * @create: 2021/12/13
 * @Description: 组件之一：用户
 */
public class ComponentUser implements Component{

    private Mediator mediator;
    private int money;

    public ComponentUser(int money) {
        this.money = money;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Type getType() {
        return Type.USER;
    }

    // 叫车
    public String callTheCar(Type type){
        return mediator.callTheCar(type);
    }

    // 扣费 并返回余额
    public int deduct(int money){
        this.money -= money;
        return money;
    }

    public int getMoney() {
        return money;
    }
}
