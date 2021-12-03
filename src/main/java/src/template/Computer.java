package src.template;

/**
 * @author: chenbihao
 * @create: 2021/12/3
 * @Description:
 */
public abstract class Computer {

    private String mainboard = "自带主板";
    protected String cpu;
    protected String gpu;
    protected String ram;
    protected String rom;

    // final 防止子类重写
    public final void buildComputer() {
        installCpu();
        installGpu();
        installRam();
        installRom();
    }

    // 抽象方法，给子类实现
    protected abstract void installCpu();
    // 抽象方法，给子类实现
    protected abstract void installGpu();

    // 默认实现
    protected void installRam() {
        this.ram = "8G";
    }
    // 默认实现
    protected void installRom() {
        this.rom = "512G固态";
    }

    public String show() {
        return "Computer{" +
                "mainboard='" + mainboard + '\'' +
                ", cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", ram='" + ram + '\'' +
                ", rom='" + rom + '\'' +
                '}';
    }
}
