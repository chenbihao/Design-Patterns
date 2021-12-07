package src.chainOfResponsibility;

/**
 * @author: chenbihao
 * @create: 2021/12/5
 * @Description:
 */
public class HandlerB extends Handler {
    @Override
    protected boolean doHandler(String msg) {
        boolean handled = false;
        if (msg!=null) {
            if (msg.contains("error")) {
                System.out.println("发现error，紧急通知处理...");
            }
        }
        return handled;
    }
}
