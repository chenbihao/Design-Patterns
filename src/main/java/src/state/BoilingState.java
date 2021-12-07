package src.state;

/**
 * @author: chenbihao
 * @create: 2021/12/7
 * @Description: 热水状态
 */
public class BoilingState extends State{

    public BoilingState(Kettle kettle) {
        super(kettle);
    }

    @Override
    public String addWater() {
        return "有水了，别加了";
    }
    @Override
    public String boilWater() {
        return "保温中...";
    }
    @Override
    public String pourWater() {
        // 切换上下文状态
        kettle.changeState(new EmptyState(kettle));
        return "倒水成功（开水）";
    }

    @Override
    public String toString() {
        return "热水状态";
    }
}
