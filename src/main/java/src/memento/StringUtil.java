package src.memento;

/**
 * @author: chenbihao
 * @create: 2021/12/10
 * @Description: 原发器 （Originator） 类
 */
public class StringUtil {

    private StringBuilder text = new StringBuilder();

    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);
    }

    /**
     * 生成自身状态的快照
     */
    public Snapshot createSnapshot() {
        return new Snapshot(text.toString());
    }

    /**
     * 通过快照恢复自身状态
     */
    public void restoreSnapshot(Snapshot snapshot) {
        this.text.replace(0, this.text.length(), snapshot.getText());
    }
}
