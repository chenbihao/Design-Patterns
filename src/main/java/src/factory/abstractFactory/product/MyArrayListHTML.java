package src.factory.abstractFactory.product;

/**
 * 具体产品由相应的具体工厂创建。
 */
public class MyArrayListHTML implements MyArrayList{
    @Override
    public void print() {
        System.out.println("打印ArrayList样式的HTML");
    }
}
