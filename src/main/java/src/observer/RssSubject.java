package src.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenbihao
 * @create: 2021/12/2
 * @Description: RSS 服务端 （被观察者主体）
 */
public class RssSubject implements Subject{

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String msg) {
        // 通知每一个订阅者
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }
}
