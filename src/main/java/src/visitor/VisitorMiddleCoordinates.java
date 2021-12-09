package src.visitor;

import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/12/9
 * @Description: 访问者实现类： 获取中间值
 */
public class VisitorMiddleCoordinates implements Visitor{

    @Override
    public Object visitPoint(ShapePoint shapePoint) {
        // 直接返回点坐标
        return shapePoint.getCoordinates().get(0);
    }

    @Override
    public Object visitSection(ShapeSection shapeSection) {
        // 返回线段的中间点
        List<String> coordinates = shapeSection.getCoordinates();
        int middle = coordinates.size()/2;
        return coordinates.get(middle);
    }

    @Override
    public Object visitPolygon(ShapePolygon shapePolygon) {
        // 返回线段的起点（也是终点）
        return shapePolygon.getCoordinates().get(0);
    }

}
