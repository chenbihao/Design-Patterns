package src.factory.abstractFactory.client;



import src.factory.abstractFactory.factory.PrinterFactory;
import src.factory.abstractFactory.product.MyArrayList;
import src.factory.abstractFactory.product.MyLinkedList;

/**
 * 客户端代码仅通过抽象类型（PrinterFactory、arrayList 和 LinkedLlist）使用工厂和产品。
 * 这让你无需修改任何工厂或产品子类就能将其传递给客户端代码。
 */
public class Application {

    private PrinterFactory factory;

    private MyArrayList arrayList;
    private MyLinkedList linkedList;

    public Application(PrinterFactory factory) {
        this.factory = factory;
    }

    public void create(){
        arrayList = factory.createArrayList();
        linkedList = factory.createLinkedList();
    }

    public void print() {
        arrayList.print();
        linkedList.print();
    }
}
