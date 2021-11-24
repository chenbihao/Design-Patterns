package src.decorator;

/**
 * @author: chenbihao
 * @create: 2021/11/24
 * @Description: 水果
 * @History:
 */
public class ToppingFruits extends Topping {
    public ToppingFruits(Pizza pizza) {
        super(pizza);
    }
    @Override
    public int getPrice() {
        int price = this.pizza.getPrice();
        return  price + 7;
    }
}
