package src.ChainOfResponsibility;

/**
 * @author: chenbihao
 * @create: 2021/12/5
 * @Description:
 */
public class HandlerA extends Handler {
    @Override
    protected boolean doHandler(String msg) {
        boolean handled = false;
        if (msg!=null) {
            if (msg.contains("wrong")) {
                System.out.println("发现wrong，进行告警处理...");
            }
        }
        return handled;
    }
}
