package src.bridge;

/**
 * @author: chenbihao
 * @create: 2021/11/22
 * @Description:
 * @History:
 */
public class ComputerWindows implements Computer{

    private Printer printer;

    @Override
    public void print() {
        System.out.println("windows 请求打印：");
        printer.printFile();
    }

    @Override
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
