package src.memento;

/**
 * @author: chenbihao
 * @create: 2021/12/10
 * @Description: 备忘录 （Memento）
 */
public class Snapshot {

    private String text;

    /**
     * 备忘录（Memento）是原发器状态快照的值对象（value object）。
     * 通常做法是将备忘录设为不可变的，并通过构造函数一次性传递数据。
     */
    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
