package src.singleton;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description: 多例 (可以创建多个同类对象)
 * @History:
 */
public class MultipleSingleton {

    private long id;
    private String msg;

    private static final int COUNT = 3;
    private static final Map<Long, MultipleSingleton> instances = new HashMap<>();

    private MultipleSingleton(long id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    static {
        instances.put(1L, new MultipleSingleton(1L, "多例-1"));
        instances.put(2L, new MultipleSingleton(2L, "多例-2"));
        instances.put(3L, new MultipleSingleton(3L, "多例-3"));
    }

    public static MultipleSingleton getInstance(long id) {
        return instances.get(id);
    }

    public static MultipleSingleton getRandomInstance() {
        SecureRandom r = new SecureRandom();
        int i = r.nextInt(COUNT) + 1;
        return instances.get(i);
    }
}
