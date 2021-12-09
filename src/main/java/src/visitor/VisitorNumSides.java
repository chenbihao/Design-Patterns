package src.visitor;

/**
 * @author: chenbihao
 * @create: 2021/12/9
 * @Description: 访问者实现类： 获取 边数
 */
public class VisitorNumSides implements Visitor{

    @Override
    public Object visitPoint(ShapePoint shapePoint) {
        // 点 没有边数
        return 0;
    }

    @Override
    public Object visitSection(ShapeSection shapeSection) {
        // 段 边数=坐标数-1
        return shapeSection.getCoordinates().size()-1;
    }

    @Override
    public Object visitPolygon(ShapePolygon shapePolygon) {
        // 段 边数=坐标数
        return shapePolygon.getCoordinates().size();
    }
}
