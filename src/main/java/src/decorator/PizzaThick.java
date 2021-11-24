package src.decorator;

/**
 * @author: chenbihao
 * @create: 2021/11/24
 * @Description: 厚饼比萨
 * @History:
 */
public class PizzaThick implements Pizza{
    @Override
    public int getPrice() {
        return 20;
    }
}
