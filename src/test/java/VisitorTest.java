import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.strategy.Cache;
import src.strategy.StrategyFIFO;
import src.strategy.StrategyLFU;
import src.strategy.StrategyLRU;
import src.visitor.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class VisitorTest {

    @Test
    public void test() {

        String pointCoordinates = "坐标点1";
        ArrayList<String> sectionCoordinates = new ArrayList<>(Arrays.asList("坐标点1", "坐标点2", "坐标点3"));
        ArrayList<String> polygonCoordinates = new ArrayList<>(Arrays.asList("坐标点1", "坐标点2", "坐标点3"));

        ShapePoint point = new ShapePoint(pointCoordinates);
        ShapeSection section = new ShapeSection(sectionCoordinates);
        ShapePolygon polygon = new ShapePolygon(polygonCoordinates);

        // 获取中间点
        VisitorMiddleCoordinates visitorMiddleCoordinates = new VisitorMiddleCoordinates();
        Assertions.assertEquals("坐标点1", point.accept(visitorMiddleCoordinates));
        Assertions.assertEquals("坐标点2", section.accept(visitorMiddleCoordinates));
        Assertions.assertEquals("坐标点1", polygon.accept(visitorMiddleCoordinates));

        // 获取边数
        VisitorNumSides visitorNumSides = new VisitorNumSides();
        Assertions.assertEquals(0, point.accept(visitorNumSides));
        Assertions.assertEquals(2, section.accept(visitorNumSides));
        Assertions.assertEquals(3, polygon.accept(visitorNumSides));

    }


}
