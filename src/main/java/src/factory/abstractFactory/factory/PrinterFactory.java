package src.factory.abstractFactory.factory;

import src.factory.abstractFactory.product.MyArrayList;
import src.factory.abstractFactory.product.MyLinkedList;

/**
 * 【抽象工厂接口】声明了一组能返回不同抽象产品的方法。
 * 这些产品属于同一个系列且在高层主题或概念上具有相关性。
 * 同系列的产品通常能相互搭配使用。
 * 系列产品可有多个变体，但不同变体的产品不能搭配使用。
 */
public interface PrinterFactory {

    MyArrayList createArrayList();
    MyLinkedList createLinkedList();
    //  这里可以扩展类型

}
