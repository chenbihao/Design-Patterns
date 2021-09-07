package src.singleton;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description: 懒汉式单例
 * @History:
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("初始化..");
    }

    /**
     * 必须要有 synchronized 来确保线程安全，并发度为1，性能弱
     */
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
