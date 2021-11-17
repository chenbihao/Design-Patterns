package src.prototype;

import java.util.Objects;

/**
 * @author: chenbihao
 * @create: 2021/11/17
 * @Description: 原型类
 * @History:
 */
public class SimplePrototype {

    private String a;
    private String b;
    // 假设这里N多字段以及有需要计算的字段

    public SimplePrototype() {
    }

    /**
     * 1. 以该类对象为参数的构造函数
     */
    public SimplePrototype(SimplePrototype self) {
        if (self != null) {
            this.a = self.a;
            this.b = self.b;
        }
    }

    /**
     * 2. 克隆方法
     * 直接返回 new 调用参数为 self 的构造函数
     */
    @Override
    public SimplePrototype clone() {
        return new SimplePrototype(this);
    }

    // 重写 equals 和 hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePrototype that = (SimplePrototype) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }


    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
