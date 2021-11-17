import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.builder.ConstructorArg;
import src.prototype.ObjectPrototype;
import src.prototype.SimplePrototype;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class PrototypeTest {

    @Test
    public void testPrototype() {

        // 自己实现的原型类
        SimplePrototype simplePrototype = new SimplePrototype();
        simplePrototype.setA("asdasdasdasdasdasd");
        simplePrototype.setB("123123124134123123");

        SimplePrototype clonePrototype = simplePrototype.clone();

        Assertions.assertTrue(simplePrototype.equals(clonePrototype));

        clonePrototype.setB("asd");
        Assertions.assertFalse(simplePrototype.equals(clonePrototype));


        // 实现 Cloneable 接口的原型对象
        ObjectPrototype objectPrototype = new ObjectPrototype();
        objectPrototype.setA("asdasdasdasdasdasd");
        objectPrototype.setB("123123124134123123");

        ObjectPrototype clone=null;
        try {
            clone = objectPrototype.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(objectPrototype.equals(clone));

    }



    // java.lang.Object#clone()

    //（类必须实现 java.lang.Cloneable 接口）


}
