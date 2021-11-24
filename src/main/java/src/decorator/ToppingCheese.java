package src.decorator;

/**
 * @author: chenbihao
 * @create: 2021/11/24
 * @Description:
 * @History:
 */
public class ToppingCheese extends Topping {
    public ToppingCheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public int getPrice() {
        int price = this.pizza.getPrice();
        return  price + 5;
    }
}
