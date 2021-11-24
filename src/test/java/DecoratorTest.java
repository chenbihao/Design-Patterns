import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.decorator.*;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class DecoratorTest {

    @Test
    public void test() {

        // 薄饼披萨15 + 芝士5 + 番茄7
        PizzaThin pizzaThin = new PizzaThin();
        Pizza finalPizza = new ToppingCheese(new ToppingFruits(pizzaThin));
        Assertions.assertEquals(27,finalPizza.getPrice());

        // 厚饼披萨20 + 芝士5
        PizzaThick pizzaThick = new PizzaThick();
        Pizza finalPizza2 = new ToppingCheese(pizzaThick);
        Assertions.assertEquals(25,finalPizza2.getPrice());

    }
}
