import org.junit.jupiter.api.Test;
import src.chainOfResponsibility.HandlerA;
import src.chainOfResponsibility.HandlerB;
import src.chainOfResponsibility.HandlerChain;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class ChainOfResponsibilityTest {

    @Test
    public void test() {

        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());

        chain.handle("info：balabala");
        chain.handle("wrong：asdasdasd");
        chain.handle("error：！！！！");
        chain.handle("wrong and error！！！！！");

    }


    public void asd() {

    }


}
