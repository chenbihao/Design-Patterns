package src.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/11/28
 * @Description:
 */
public class DirectoryNode extends FileSystemNode {

    // Node 列表集合
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public DirectoryNode(String name) {
        super(name);
    }

    @Override
    public int count() {
        // 遍历统计文件数量
        int numOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            numOfFiles += fileOrDir.count();
        }
        return numOfFiles;
    }

    public void addSubNode(FileSystemNode node) {
        subNodes.add(node);
    }

    public void removeSubNode(FileSystemNode node) {
        subNodes.remove(node);
    }

    public void clear() {
        subNodes.clear();
    }

}
