package src.prototype;

import java.util.Objects;

/**
 * @author: chenbihao
 * @create: 2021/11/17
 * @Description: 原型类
 * @History:
 */
public class ObjectPrototype implements Cloneable{

    private String a;
    private String b;
    // 假设这里N多字段以及有需要计算的字段

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


    /**
     * 重写 clone
     * 默认被 protected 修饰，改为 public
     * 如果没有 implements Cloneable ，则调用 clone 会报 CloneNotSupportedException
     */
    @Override
    public ObjectPrototype clone() throws CloneNotSupportedException {
        return (ObjectPrototype)super.clone();
    }

    // 重写 equals 和 hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectPrototype that = (ObjectPrototype) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }


}
