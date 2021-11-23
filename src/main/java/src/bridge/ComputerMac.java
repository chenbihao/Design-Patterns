package src.bridge;

/**
 * @author: chenbihao
 * @create: 2021/11/22
 * @Description:
 * @History:
 */
public class ComputerMac implements Computer{

    private Printer printer;

    @Override
    public void print() {
        System.out.println("mac 请求打印：");
        printer.printFile();
    }

    @Override
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
