package src.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chenbihao
 * @create: 2021/12/1
 * @Description: 享元工厂
 */
public class DiceFactory {

    private static final Map<Integer, DiceType> diceTypeMap = new HashMap<>();

    /**
     * 获取享元对象：获取 N面骰子 实例
     */
    public static DiceType getDiceType(int faceValue) {
        DiceType result = diceTypeMap.get(faceValue);
        if (result == null) {
            DiceType diceType = new DiceType(faceValue);
            diceTypeMap.put(faceValue, diceType);
            result = diceType;
        }
        return result;
    }

}
