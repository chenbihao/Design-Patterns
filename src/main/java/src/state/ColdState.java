package src.state;

/**
 * @author: chenbihao
 * @create: 2021/12/7
 * @Description: 冷水状态
 */
public class ColdState extends State {

    public ColdState(Kettle kettle) {
        super(kettle);
    }

    @Override
    public String addWater() {
        return "有水了，别加了";
    }
    @Override
    public String boilWater() {
        // 切换上下文状态
        kettle.changeState(new BoilingState(kettle));
        return "煮水成功";
    }
    @Override
    public String pourWater() {
        // 切换上下文状态
        kettle.changeState(new EmptyState(kettle));
        return "倒水成功（冷水）";
    }

    @Override
    public String toString() {
        return "冷水状态";
    }
}
