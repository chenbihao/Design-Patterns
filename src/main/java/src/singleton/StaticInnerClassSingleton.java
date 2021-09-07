package src.singleton;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description: 静态内部类单例
 * @History:
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
        System.out.println("初始化..");
    }

    /**
     * 类加载时，不会创建静态内部类实例，当调用 getInstance() 时才会创建 instance
     * 相当于让 JVM 来确保 instance 的线程安全与唯一性
     */
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
