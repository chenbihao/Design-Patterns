package src.state;

/**
 * @author: chenbihao
 * @create: 2021/12/7
 * @Description: 空的状态
 */
public class EmptyState extends State{

    public EmptyState(Kettle kettle) {
        super(kettle);
    }

    @Override
    public String addWater() {
        // 切换上下文状态
        kettle.changeState(new ColdState(kettle));
        return "加水成功";
    }
    @Override
    public String boilWater() {
        return "没水...";
    }
    @Override
    public String pourWater() {
        return "没水...";
    }

    @Override
    public String toString() {
        return "空的状态";
    }
}
