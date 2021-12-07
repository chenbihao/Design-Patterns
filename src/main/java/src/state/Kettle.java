package src.state;

/**
 * @author: chenbihao
 * @create: 2021/12/7
 * @Description: 热水壶类（上下文）
 */
public class Kettle {

    // 保存一个指向表示当前状态的状态对象的引用
    private State state;

    // ... 这里也可以存放一些其他数据，如水量水温等。

    public Kettle() {
        this.state = new EmptyState(this);
    }

    public State getState() {
        return state;
    }

    // 公有设置器
    public void changeState(State state) {
        this.state = state;
    }

    // 将执行工作委派给当前状态 x3
    public String addWater() {
        return state.addWater();
    }
    public String boilWater() {
        return state.boilWater();
    }
    public String pourWater() {
        return state.pourWater();
    }

}
