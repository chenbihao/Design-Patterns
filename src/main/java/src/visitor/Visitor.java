package src.visitor;

/**
 * @author: chenbihao
 * @create: 2021/12/9
 * @Description: 访问者接口
 */
public interface Visitor {

    Object visitPoint(ShapePoint shapePoint);

    Object visitSection(ShapeSection shapeSection);

    Object visitPolygon(ShapePolygon shapePolygon);

}
