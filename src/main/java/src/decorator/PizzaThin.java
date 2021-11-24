package src.decorator;

/**
 * @author: chenbihao
 * @create: 2021/11/24
 * @Description: 薄饼比萨
 * @History:
 */
public class PizzaThin implements Pizza{
    @Override
    public int getPrice() {
        return 15;
    }
}
