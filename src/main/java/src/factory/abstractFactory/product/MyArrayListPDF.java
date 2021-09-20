package src.factory.abstractFactory.product;

/**
 * 这是另一个产品的【具体产品】。
 * 所有产品都可以互动，但是只有相同具体变体的产品之间才能够正确地进行交互。
 */
public class MyArrayListPDF implements MyArrayList{
    @Override
    public void print() {
        System.out.println("打印ArrayList样式的PDF");
    }
}
