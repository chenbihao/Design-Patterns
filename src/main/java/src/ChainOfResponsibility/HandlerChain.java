package src.ChainOfResponsibility;

/**
 * @author: chenbihao
 * @create: 2021/12/5
 * @Description:
 */
public class HandlerChain {
    private Handler head = null;
    private Handler tail = null;

    public void addHandler(Handler handler) {
        handler.setNext(null);
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setNext(handler);
        tail = handler;
    }

    public void handle(String msg) {
        if (head != null) {
            head.handle(msg);
        }
    }
}
