import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.composite.DirectoryNode;
import src.composite.FileNode;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class CompositeTest {

    @Test
    public void test() {
        DirectoryNode d1 = new DirectoryNode("目录1");
        DirectoryNode d2 = new DirectoryNode("目录2");
        DirectoryNode d3 = new DirectoryNode("目录3");
        FileNode fileA = new FileNode("文件A");
        FileNode fileB = new FileNode("文件B");
        FileNode fileC = new FileNode("文件C");

        d1.addSubNode(d2);
        d1.addSubNode(fileA);

        d2.addSubNode(d3);
        d3.addSubNode(fileB);
        d3.addSubNode(fileC);

        Assertions.assertEquals(3,d1.count());
        d3.removeSubNode(fileC);
        Assertions.assertEquals(2,d1.count());
        d2.clear();
        Assertions.assertEquals(1,d1.count());
        d1.addSubNode(fileC);
        Assertions.assertEquals(2,d1.count());

    }
}
