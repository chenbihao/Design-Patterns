package src.flyweight;

/**
 * @author: chenbihao
 * @create: 2021/12/1
 * @Description: 骰子类（情景类）
 */
public class Dice {

    // 外在属性：幸运数字
    private int luckyNumber;
    // 外在属性：不幸数字
    private int unluckyNumber;

    // 共享的骰子
    private DiceType diceType;

    // 这一次 roll 出来的数字
    private int rollNumber;
    // 这一次 roll 出来的幸运类型： 0普通 1幸运 -1不幸
    private int luckyType;

    public Dice(int luckyNumber, int unluckyNumber, DiceType diceType) {
        this.luckyNumber = luckyNumber;
        this.unluckyNumber = unluckyNumber;
        this.diceType = diceType;
    }

    // 掷骰子
    public int roll() {
        int roll = diceType.roll();
        rollNumber = roll;
        if (roll == luckyNumber) {
            System.out.println("投出了幸运数字：" + roll);
            luckyType = 1;
        } else if (roll == unluckyNumber) {
            System.out.println("投出了不幸数字：" + roll);
            luckyType = -1;
        } else {
            System.out.println("投出了数字：" + roll);
            luckyType = 0;
        }
        return roll;
    }

    // 获取幸运状态：0普通 1幸运 -1不幸
    public int getLuckyType() {
        return luckyType;
    }
}
