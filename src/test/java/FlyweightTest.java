import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.flyweight.Dice;
import src.flyweight.DiceFactory;
import src.flyweight.DiceType;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class FlyweightTest {

    @Test
    public void test() {

        // 获取享元对象：6面骰子
        DiceType diceType1 = DiceFactory.getDiceType(6);

        // 幸运6 不幸1 其他正常
        int luckyNumber = 6;
        int unluckyNumber = 1;

        Dice dice1 = new Dice(luckyNumber, unluckyNumber, diceType1);
        for (int i = 0; i < 100; i++) {
            int rollNumber = dice1.roll();;
            int luckyType = dice1.getLuckyType();
            if (rollNumber==luckyNumber) {
                Assertions.assertEquals(1,luckyType);
            }else if(rollNumber==unluckyNumber){
                Assertions.assertEquals(-1,luckyType);
            }else{
                Assertions.assertEquals(0,luckyType);
            }
        }

        // 再次获取享元对象 ，一样是6面骰子
        DiceType diceType2 = DiceFactory.getDiceType(6);

        // 获取的对象应该是同一个
        Assertions.assertEquals(diceType1,diceType2);

        // 改一下外在状态
        luckyNumber = 3;
        unluckyNumber = 2;

        Dice dice2 = new Dice(luckyNumber, unluckyNumber, diceType2);
        for (int i = 0; i < 100; i++) {
            int rollNumber = dice2.roll();;
            int luckyType = dice2.getLuckyType();
            if (rollNumber==luckyNumber) {
                Assertions.assertEquals(1,luckyType);
            }else if(rollNumber==unluckyNumber){
                Assertions.assertEquals(-1,luckyType);
            }else{
                Assertions.assertEquals(0,luckyType);
            }
        }
    }



    public void asd(){

        new Integer(1);
        new String("123").intern();
    }




}
