package src.factory.abstractFactory.factory;

import src.factory.abstractFactory.product.MyArrayList;
import src.factory.abstractFactory.product.MyArrayListHTML;
import src.factory.abstractFactory.product.MyLinkedList;
import src.factory.abstractFactory.product.MyLinkedListHTML;

/**
 * 【具体工厂】可生成属于同一变体的系列产品。
 * 工厂会确保其创建的产品能相互搭配使用。
 * 具体工厂方法签名会返回一个抽象产品，但在方法内部则会对具体产品进行实例化。
 */
public class PrinterHTMLFactory implements PrinterFactory {

    @Override
    public MyArrayList createArrayList() {
        return new MyArrayListHTML();
    }

    @Override
    public MyLinkedList createLinkedList() {
        return new  MyLinkedListHTML();
    }
    //  这里可以扩展类型
}
