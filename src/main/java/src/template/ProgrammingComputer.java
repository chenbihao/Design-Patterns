package src.template;

/**
 * @author: chenbihao
 * @create: 2021/12/3
 * @Description:
 */
public class ProgrammingComputer extends Computer {

    @Override
    protected void installCpu() {
        this.cpu = "amd 5900x";
    }

    @Override
    protected void installGpu() {
        this.gpu = "nvidia 2070 super";
    }

    @Override
    protected void installRam() {
        this.ram = "64G";
    }

    @Override
    protected void installRom() {
        this.rom = "2T 固态";
    }
}
