import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.flyweight.Dice;
import src.flyweight.DiceFactory;
import src.flyweight.DiceType;
import src.observer.RssObserver;
import src.observer.RssSubject;

import java.util.Observable;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class ObserverTest {

    @Test
    public void test() {

        // RSS 服务端 （被观察者主体）
        RssSubject rssSubject = new RssSubject();

        // rss 订阅对象（观察者）
        RssObserver observer1 = new RssObserver("用户A");
        RssObserver observer2 = new RssObserver("用户B");
        RssObserver observer3 = new RssObserver("用户C");
        RssObserver observer4 = new RssObserver("用户D");

        // 把观察者注册到主体（相当于订阅了通知，有新推送则会收到）
        rssSubject.registerObserver(observer1);
        rssSubject.registerObserver(observer2);
        rssSubject.registerObserver(observer3);

        // 推送信息
        rssSubject.notifyObservers("早上推送文章《论观察者模式1》");

        Assertions.assertEquals(1,observer1.getCount());
        Assertions.assertEquals(0,observer4.getCount());

        rssSubject.removeObserver(observer3);
        rssSubject.registerObserver(observer4);

        rssSubject.notifyObservers("晚上推送文章《论观察者模式2》");
        Assertions.assertEquals(2,observer1.getCount());
        Assertions.assertEquals(1,observer3.getCount());
        Assertions.assertEquals(1,observer4.getCount());

        Assertions.assertEquals("早上推送文章《论观察者模式1》",observer3.getLastMessage());
    }


}
