package src.bridge;

/**
 * @author: chenbihao
 * @create: 2021/11/22
 * @Description:
 * @History:
 */
public class PrinterHp implements Printer{

    @Override
    public void printFile() {
        System.out.println("惠普打印机正在打印...");
    }
}
