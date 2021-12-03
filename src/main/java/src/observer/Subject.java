package src.observer;

/**
 * @author: chenbihao
 * @create: 2021/12/2
 * @Description:
 */
public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String msg);

}
