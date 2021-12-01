# 享元模式（Flyweight）

## 介绍

**享元模式**是一种结构型设计模式。

享元模式顾名思义就是被共享的单元，意图是**复用对象**，**节省内存**。


## 适用场景

- 存在大量重复对象（重复状态）且没有足够的内存容量时使用享元模式。
- ...


## 优缺点

优点：

- 节省大量内存。

缺点：

- 代码复杂度提升。
- 如果对象有不同的情景数据（外在状态），调用者需要耗费时间来重新计算。


## 与其他模式的关系

- 可以使用**享元模式**实现**组合模式**树的共享叶节点以节省内存。
- 如果能将对象的所有共享状态简化为一个享元对象，那么**享元**就和**单例**类似了。但这两个模式有两个根本性的不同。
    - **单例**只会有一个单例实体。
    - **享元**可以有多个实体，各实体的内在状态也可以不同。
    - **单例**对象可以是**可变**的。
    - **享元**对象是**不可变**的。


## 实现方式

1. 将享元对象划分为2部分：
    - **内在状态**（不变的部分）
    - **外在状态**（改变的部分）
2. 保留类中表示内在状态的成员变量，并将其设置为**不可修改**。
3. 客户端必须存储和计算**外在状态**（情景）的数值，因为只有这样才能调用享元对象的方法。
    - 为了使用方便，外在状态和引用享元的成员变量可以移动到单独的**情景类**中。
4. 可以创建**工厂类**来管理**享元缓存池**，如果使用了工厂，那么客户端就只能通过工厂来获取享元对象。

--- 

## 示例

**享元对象**：抽取出来的骰子类（只包含不变的内在属性）
```java
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
```

**情景类**：存储着外在状态的骰子类（包含变化的部分以及享元对象）
```java
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
```

**享元工厂**：骰子享元缓存工厂
```java
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
```

测试代码
```java
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
}
```

--- 

## 实例

### JDK

#### 包装器类型

java.lang.Integer#valueOf(int)

Long、Short、Byte、Boolean、Character、BigDecimal 等

```java
public final class Integer extends Number implements Comparable<Integer> {
    // ... 省略

    /**
     * 默认缓存 -128~127 
     * 可通过以下两者方法之一 改掉最大值127
     * -Djava.lang.Integer.IntegerCache.high=255
     * -XX:AutoBoxCacheMax=255
     */
    private static class IntegerCache {
        static final int low = -128;
        static final int high;
        static final Integer cache[];

        static {
            // high value may be configured by property
            int h = 127;
            String integerCacheHighPropValue =
                    VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            if (integerCacheHighPropValue != null) {
                try {
                    int i = parseInt(integerCacheHighPropValue);
                    i = Math.max(i, 127);
                    // Maximum array size is Integer.MAX_VALUE
                    h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
                } catch( NumberFormatException nfe) {
                    // If the property cannot be parsed into an int, ignore it.
                }
            }
            high = h;

            cache = new Integer[(high - low) + 1];
            int j = low;
            for(int k = 0; k < cache.length; k++)
                cache[k] = new Integer(j++);

            // range [-128, 127] must be interned (JLS7 5.1.7)
            assert IntegerCache.high >= 127;
        }

        private IntegerCache() {}
    }

    // 转为包装对象
    public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }
    // ... 省略
}
```
#### java.lang.String

String 类利用享元模式来复用相同的字符串常量。

JVM 会专门开辟一块存储区来存储字符串常量，这块存储区叫作“字符串常量池”。
