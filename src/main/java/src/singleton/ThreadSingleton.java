package src.singleton;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description: 线程唯一 单例
 * @History:
 */
public class ThreadSingleton {

    private static final ConcurrentHashMap<Long, ThreadSingleton> instances = new ConcurrentHashMap<>();

    private ThreadSingleton() {}

    /**
     * 前面的单例实例都是属于 “进程唯一” ，这个版本属于 “线程唯一”
     * 相同线程只有一个对象，而不同线程之间对象有多个
     * 相当于 ThreadLocal 工具类
     */
    public static ThreadSingleton getInstance() {
        Long currentThreadId = Thread.currentThread().getId();
        instances.putIfAbsent(currentThreadId, new ThreadSingleton());
        return instances.get(currentThreadId);
    }
}
