package src.composite;

/**
 * @author: chenbihao
 * @create: 2021/11/28
 * @Description:
 */
public class FileNode extends FileSystemNode {

    public FileNode(String name) {
        super(name);
    }

    @Override
    public int count() {
        return 1;
    }

}
