import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.memento.Snapshot;
import src.memento.SnapshotHolder;
import src.memento.StringUtil;
import src.state.Kettle;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class MementoTest {

    @Test
    public void test() {

        StringUtil stringUtil = new StringUtil();
        SnapshotHolder snapshotHolder = new SnapshotHolder();

        stringUtil.append("张三");
        snapshotHolder.save(stringUtil);
        Assertions.assertEquals("张三",stringUtil.getText());

        stringUtil.append("是法外狂徒");
        Assertions.assertEquals("张三是法外狂徒",stringUtil.getText());
        // 发现输错了 撤销一下
        snapshotHolder.undo(stringUtil);
        Assertions.assertEquals("张三",stringUtil.getText());

        stringUtil.append("李四");
        snapshotHolder.save(stringUtil);
        Assertions.assertEquals("张三李四",stringUtil.getText());


    }

}
