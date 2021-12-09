package src.visitor;

import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/12/9
 * @Description: 多边形 (多个坐标，连成闭合的线段)
 */
public class ShapePolygon implements Shape {

    // 坐标
    private List<String> coordinates;

    // 构造器
    public ShapePolygon(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    // 之前实现的方法
    @Override
    public String getShapeType() {
        return "polygon";
    }

    // 之前实现的方法
    @Override
    public List<String> getCoordinates() {
        return coordinates;
    }

    /**
     * 这个是为了访问者加的方法，在单分派的语言中实现双分派的功能 （Java是单分派的）
     */
    @Override
    public Object accept(Visitor visitor) {
        return visitor.visitPolygon(this);
    }
}
