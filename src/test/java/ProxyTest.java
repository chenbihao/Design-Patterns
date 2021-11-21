import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.Proxy.CommandExecutor;
import src.Proxy.CommandExecutorProxy;
import src.builder.ConstructorArg;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class ProxyTest {

    @Test
    public void test() throws Exception {
        CommandExecutor executor = new CommandExecutorProxy("admin", "abcABC123!@#");
        executor.runCommand("ls");
        executor.runCommand("rm -rf *");
    }

    @Test
    public void testException() {

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            CommandExecutor executor = new CommandExecutorProxy("admin", "123456");
            executor.runCommand("ls");
            executor.runCommand("rm -rf *");
        });
        Assertions.assertEquals("rm command is not allowed for non-admin users.", exception.getMessage());

    }



    public void asd(){
        String str = new StringBuilder().append("asdasd").append(123).toString();
    }

    // jdk：

    // java.lang.reflect.Proxy
    // java.rmi.*


    // 其他：



}
