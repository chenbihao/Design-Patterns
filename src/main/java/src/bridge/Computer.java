package src.bridge;

/**
 * @author: chenbihao
 * @create: 2021/11/22
 * @Description: 抽象基类
 * @History:
 */
public interface Computer {

    /**
     * 1.定义业务需求：打印
     */
    void print();
    /**
     * 2.指向实现类型的引用：设置打印机
     */
    void setPrinter(Printer printer);

}
