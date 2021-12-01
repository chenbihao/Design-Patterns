package src.flyweight;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author: chenbihao
 * @create: 2021/12/1
 * @Description: 享元对象
 */
public class DiceType {

    // 内在属性：面数
    private int faceValue;
    private SecureRandom random = new SecureRandom();

    public DiceType(int faceValue) {
        this.faceValue = faceValue;
    }

    public int roll() {
        int i = random.nextInt(faceValue);
        return i + 1;
    }
}
