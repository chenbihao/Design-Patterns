package src.factory.abstractFactory.client;


import src.factory.abstractFactory.factory.PrinterFactory;
import src.factory.abstractFactory.factory.PrinterHTMLFactory;
import src.factory.abstractFactory.factory.PrinterPDFFactory;

/**
 * 程序会根据当前配置或环境设定选择工厂类型，并在运行时创建工厂（通常在初始化阶段）。
 */
public class ApplicationConfigurator {

    public static void main(String[] args) throws Exception {

        PrinterFactory factory;

        if ("pdf".equals(args[0])) {
            factory = new PrinterPDFFactory();
        } else if ("html".equals(args[0])) {
            factory = new PrinterHTMLFactory();
        } else {
            throw new Exception("未知的输出方式");
        }
        Application application = new Application(factory);
        application.create();
        application.print();
    }

}
