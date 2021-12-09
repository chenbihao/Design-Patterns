package src.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/12/9
 * @Description: 点 (单纯一个点坐标，没有线段)
 */
public class ShapePoint implements Shape {

    // 坐标
    private String coordinate;

    // 构造器
    public ShapePoint(String coordinate) {
        this.coordinate = coordinate;
    }

    // 之前实现的方法
    @Override
    public String getShapeType() {
        return "point";
    }

    // 之前实现的方法
    @Override
    public List<String> getCoordinates() {
        return new ArrayList() {{add(coordinate);}};
    }

    /**
     * 这个是为了访问者加的方法，在单分派的语言中实现双分派的功能 （Java是单分派的）
     */
    @Override
    public Object accept(Visitor visitor) {
        return visitor.visitPoint(this);
    }
}
