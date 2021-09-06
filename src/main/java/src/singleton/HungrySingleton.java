package src.singleton;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description: 饿汉式单例
 * @History:
 */
public class HungrySingleton {

    /**
     * 线程安全，但不支持延迟加载
     */
    private static HungrySingleton instance = new HungrySingleton();

    /**
     * 必须为 private 防止不小心使用 new 关键字创建
     */
    private HungrySingleton() {
        System.out.println("初始化..");
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

}
