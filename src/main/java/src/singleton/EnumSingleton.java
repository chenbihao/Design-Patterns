package src.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public enum EnumSingleton {

    /**
     * Java规范字规定，每个枚举类型及其定义的枚举变量在JVM中都是唯一的，保证了实例创建的线程安全性和实例的唯一性
     */
    INSTANCE;

    public static void doSomething(){
        //do something
    }

}
