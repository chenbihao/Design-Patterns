package src.composite;

/**
 * @author: chenbihao
 * @create: 2021/11/28
 * @Description:
 */
public abstract class FileSystemNode {

    protected String name;

    public FileSystemNode(String name) {
        this.name = name;
    }

    // 文件总数
    public abstract int count();

}
