package src.chainOfResponsibility;

/**
 * @author: chenbihao
 * @create: 2021/12/5
 * @Description: 处理器接口  （这里使用的模板方法）
 */
public abstract class Handler {

    protected Handler next = null;

    public void setNext(Handler handler) {
        this.next = handler;
    }

    public final void handle(String msg) {
        // 执行处理
        boolean handled = doHandler(msg);

//        // 如果当前处理器处理不了，并且还有下一个节点的话，则传递处理，否则停止传递
//        if (next != null && !handled) {
//            next.handle(msg);
//        }

        // 这里选择继续传递处理
        if (next != null) {
            next.handle(msg);
        }
    }

    // 留给子类实现
    protected abstract boolean doHandler(String msg);

}
