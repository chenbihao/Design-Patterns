package src.singleton;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description: 双重校验锁单例
 * @History:
 */
public class DoubleLockingSingleton {

    /**
     * 此处加 volatile 是为了禁止指令重排（通过加内存屏障来禁止）
     * 高版本 Java 可以不需要加 volatile 关键字，因为 JDK 内部已经把 new 操作和初始化操作定义为原子操作（存疑）
     */
    private static volatile DoubleLockingSingleton instance;


    private DoubleLockingSingleton() {
        System.out.println("初始化..");
    }

    /**
     * 这里相比懒汉式，去掉了 synchronized 关键字，因为进行了两次 (instance == null)的判断，所以叫双重校验
     */
    public static DoubleLockingSingleton getInstance() {
        if (instance == null) {
            // 此处为类级别的锁
            synchronized (DoubleLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleLockingSingleton();
                }
            }
        }
        return instance;
    }

}
