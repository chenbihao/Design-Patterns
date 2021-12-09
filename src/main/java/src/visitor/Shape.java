package src.visitor;

import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/12/9
 * @Description: 接口
 */
public interface Shape {

    // 已经实现了的功能 [获取类型]
    String getShapeType();
    // 已经实现了的功能 [获取坐标]
    List<String> getCoordinates();

    /**
     * 这个是为了访问者加的接口方法
     */
    Object accept(Visitor visitor);

}
