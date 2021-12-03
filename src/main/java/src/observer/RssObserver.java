package src.observer;

/**
 * @author: chenbihao
 * @create: 2021/12/2
 * @Description: rss 订阅对象（观察者）
 */
public class RssObserver implements Observer {

    // 当前订阅者用户名
    private String user;
    // 记录最后信息，方便测试用
    private String lastMessage;
    // 记录消息总数，方便测试用
    private int count;

    public RssObserver(String user) {
        this.user = user;
    }

    @Override
    public void update(String msg) {
        System.out.println("当前用户[" + user + "]收到推送信息：" + msg);
        lastMessage = msg;
        count++;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getCount() {
        return count;
    }
}
