import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.bridge.*;
import src.builder.ConstructorArg;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class BridgeTest {

    @Test
    public void test() {

        Printer hp = new PrinterHp();
        Printer epson = new PrinterEpson();

        Computer mac = new ComputerMac();
        Computer windows = new ComputerWindows();

        // 我们可以在运行时更改抽象的实现（即计算机的打印机），因为抽象是指通过接口实现的。
        // 在调用mac.print() 或 windows.print() 时，它将请求分派给printer.printFile()。
        // 这充当了桥梁并提供了两者之间的松散耦合。

        mac.setPrinter(hp);
        mac.print();
        mac.setPrinter(epson);
        mac.print();

        windows.setPrinter(hp);
        windows.print();
        windows.setPrinter(epson);
        windows.print();
    }
}
