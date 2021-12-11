package src.memento;

import java.util.Stack;

/**
 * @author: chenbihao
 * @create: 2021/12/10
 * @Description: 负责人 （Caretaker）
 */
public class SnapshotHolder {

    /**
     * 也可以叫做 history ，负责人通过保存备忘录栈来记录原发器的历史状态。
     */
    private Stack<Snapshot> snapshots = new Stack<>();

    /**
     * 负责人通过保存备忘录栈来记录原发器的历史状态。
     */
    public void save(StringUtil stringUtil){
        snapshots.push(stringUtil.createSnapshot());
    }

    /**
     * 当原发器需要回溯历史状态时，负责人将从栈中获取最顶部的备忘录，并将其传递给原发器的恢复方法。
     */
    public void undo(StringUtil stringUtil){
        stringUtil.restoreSnapshot(snapshots.pop());
    }

    /**
     * 原本是在外部：
     * stringUtil.restoreSnapshot(snapshotHolder.popSnapshot());
     * snapshotHolder.pushSnapshot(stringUtil.createSnapshot());
     *
     * 负责人:
     * public Snapshot popSnapshot() {
     *     return snapshots.pop();
     * }
     * public void pushSnapshot(Snapshot snapshot) {
     *     snapshots.push(snapshot);
     * }
     *
     * 这里优化成 由负责人来操作:save undo
     */

}
