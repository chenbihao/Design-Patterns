package src.Proxy;

/**
 * @author: chenbihao
 * @create: 2021/11/21
 * @Description:
 * @History:
 */
public class CommandExecutorProxy implements CommandExecutor{

    /**
     * 指向原服务的引用
     */
    private CommandExecutor executor;
    private boolean isAdmin;

    public CommandExecutorProxy(String user, String pwd){
        if("admin".equals(user) && "abcABC123!@#".equals(pwd)) {
            isAdmin=true;
        }
        executor = new CommandExecutorImpl();
    }

    /**
     * 代理类和原始类需要实现相同的接口
     */
    @Override
    public void runCommand(String cmd) throws Exception {
        if(isAdmin){
            executor.runCommand(cmd);
        }else{
            if(cmd.trim().startsWith("rm")){
                throw new Exception("rm command is not allowed for non-admin users.");
            }else{
                executor.runCommand(cmd);
            }
        }
    }
}
