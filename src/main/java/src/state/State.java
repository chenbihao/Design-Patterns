package src.state;

/**
 * @author: chenbihao
 * @create: 2021/12/7
 * @Description: 状态接口
 */
public abstract class State {

    Kettle kettle;

    public State(Kettle kettle) {
        this.kettle = kettle;
    }

    // 动作

    // 加水
    public abstract String addWater();

    // 煮水
    public abstract String boilWater ();

    // 倒水
    public abstract String pourWater();
}
