import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.builder.ConstructorArg;

import java.util.Calendar;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class BuilderTest {

    @Test
    public void testBuilder1() {

        ConstructorArg byId = new ConstructorArg.Builder().setRef(true).setArg("id").build();
        ConstructorArg byType = new ConstructorArg.Builder().setRef(false).setArg("123").setType(Integer.class).build();

    }

    @Test
    public void testBuilderException() {

        IllegalArgumentException exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ConstructorArg byId = new ConstructorArg.Builder().setRef(true).setType(Integer.class).build();
        });
        Assertions.assertEquals("校验异常", exception1.getMessage());


        String str = new StringBuilder().append("asdasd").append(123).toString();

    }


    // java.lang.StringBuilder （ 非同步 ）
    // java.lang.StringBuffer （ 同步 ）


    // 76 建造者模式在 Calendar 类中的应用
    // java.util.Calendar

    // 82 Builder 模式在 Guava 中的应用


}
