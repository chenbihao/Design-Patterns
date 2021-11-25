import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.adapter.SocketAdapter;
import src.adapter.SocketClassAdapterImpl;
import src.adapter.SocketObjectAdapterImpl;
import src.decorator.*;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class AdapterTest {

    @Test
    public void test() {

        SocketAdapter objectAdapter = new SocketObjectAdapterImpl();
        Assertions.assertEquals(3,objectAdapter.get3Volt().getVolts());
        Assertions.assertEquals(12,objectAdapter.get12Volt().getVolts());
        Assertions.assertEquals(120,objectAdapter.get120Volt().getVolts());

        SocketAdapter classAdapter = new SocketClassAdapterImpl();
        Assertions.assertEquals(3,classAdapter.get3Volt().getVolts());
        Assertions.assertEquals(12,classAdapter.get12Volt().getVolts());
        Assertions.assertEquals(120,classAdapter.get120Volt().getVolts());
    }




    // 以下是无关内容


    public void asd(){
        List stooges = Arrays.asList("Larry", "Moe", "Curly");

    }

    /**
     * java.util.Arrays#asList()
     * java.util.Collections#list()
     * java.util.Collections#enumeration()
     *
     *
     */



}
