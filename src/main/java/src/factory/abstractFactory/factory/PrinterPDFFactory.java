package src.factory.abstractFactory.factory;

import src.factory.abstractFactory.product.*;

/**
 * 每个【具体工厂】中都会包含一个相应的产品变体。
 */
public class PrinterPDFFactory implements PrinterFactory {
    @Override
    public MyArrayList createArrayList() {
        return new MyArrayListPDF();
    }

    @Override
    public MyLinkedList createLinkedList() {
        return new MyLinkedListPDF();
    }
    //  这里可以扩展类型
}
