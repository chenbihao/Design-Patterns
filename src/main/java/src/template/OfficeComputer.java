package src.template;

/**
 * @author: chenbihao
 * @create: 2021/12/3
 * @Description:
 */
public class OfficeComputer extends Computer {

    @Override
    protected void installCpu() {
        this.cpu = "intel i5 ";
    }

    @Override
    protected void installGpu() {
        this.gpu = "intel i5 集成显卡";
    }
}
