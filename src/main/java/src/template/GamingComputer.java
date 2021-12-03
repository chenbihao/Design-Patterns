package src.template;

/**
 * @author: chenbihao
 * @create: 2021/12/3
 * @Description:
 */
public class GamingComputer extends Computer {

    @Override
    protected void installCpu() {
        this.cpu = "amd 5900x";
    }

    @Override
    protected void installGpu() {
        this.gpu = "nvidia 3090 super";
    }

    @Override
    protected void installRam() {
        this.ram = "16G";
    }

    @Override
    protected void installRom() {
        this.rom = "1T 固态";
    }
}
