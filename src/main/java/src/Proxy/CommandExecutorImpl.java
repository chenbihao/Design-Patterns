package src.Proxy;

/**
 * @author: chenbihao
 * @create: 2021/11/21
 * @Description:
 * @History:
 */
public class CommandExecutorImpl implements CommandExecutor{
    @Override
    public void runCommand(String cmd) throws Exception {

        // ... （执行对应操作）

        System.out.println("'" + cmd + "' command executed.");
    }
}
