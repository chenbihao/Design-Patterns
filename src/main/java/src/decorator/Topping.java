package src.decorator;

/**
 * @author: chenbihao
 * @create: 2021/11/24
 * @Description: 加料
 * @History:
 */
public class Topping implements Pizza {

    protected Pizza pizza;

    public Topping(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public int getPrice() {
        return this.pizza.getPrice();
    }
}
