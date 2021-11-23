package src.bridge;

/**
 * @author: chenbihao
 * @create: 2021/11/22
 * @Description:
 * @History:
 */
public class PrinterEpson implements Printer{

    @Override
    public void printFile() {
        System.out.println("爱普生打印机正在打印...");
    }
}
