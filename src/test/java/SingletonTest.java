import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.singleton.*;

/**
 * @author: chenbihao
 * @create: 2021/9/6
 * @Description:
 * @History:
 */
public class SingletonTest {

    @Test
    public void testHungrySingleton() {
        HungrySingleton instance = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();
        Assertions.assertEquals(instance, instance2);
    }

    @Test
    public void testLazySingleton() {
        LazySingleton instance = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();
        Assertions.assertEquals(instance, instance2);
    }

    @Test
    public void testDoubleLockingSingleton() {
        DoubleLockingSingleton instance = DoubleLockingSingleton.getInstance();
        DoubleLockingSingleton instance2 = DoubleLockingSingleton.getInstance();
        Assertions.assertEquals(instance, instance2);
    }

    @Test
    public void testStaticInnerClassSingleton() {
        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton instance2 = StaticInnerClassSingleton.getInstance();
        Assertions.assertEquals(instance, instance2);
    }

    @Test
    public void testEnumSingleton() {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        Assertions.assertEquals(instance, instance2);
    }

    // 其他方法：

    // 静态初始化：
//    static{
//        try{
//            instance = new StaticBlockSingleton();
//        }catch(Exception e){
//            throw new RuntimeException("Exception occured in creating singleton instance");
//        }
//    }

    // 反射破坏、序列化


    @Test
    public void testThreadSingleton() {

        // 线程内相同
        ThreadSingleton instanceA1 = ThreadSingleton.getInstance();
        ThreadSingleton instanceA2 = ThreadSingleton.getInstance();
        Assertions.assertEquals(instanceA1, instanceA2);

        Thread t = new Thread(() -> {

            // 线程内相同
            ThreadSingleton instanceB1 = ThreadSingleton.getInstance();
            ThreadSingleton instanceB2 = ThreadSingleton.getInstance();
            Assertions.assertEquals(instanceB1, instanceB2);

            // 线程之间不同
            Assertions.assertNotEquals(instanceA1, instanceB1);
        });
    }


    // 集群单例
    // 前面的单例实例都是属于 “进程唯一”，而集群单例也就是 “进程间也唯一”
    // 具体实现方法为：
    //
    // 把这个单例对象序列化并存储到外部共享存储区（比如文件、缓存等）。
    // 进程在使用这个单例对象的时候，需要先从外部共享存储区中将它读取到内存，并反序列化成对象，然后再使用，使用完成之后还需要再存储回外部共享存储区。
    // 为了保证任何时刻，在进程间都只有一份对象存在，一个进程在获取到对象之后，需要对对象加锁，避免其他进程再将其获取。在进程使用完这个对象之后，还需要显式地将对象从内存中删除，并且释放对对象的加锁。


    @Test
    public void testMultipleSingleton() {

        MultipleSingleton instanceA1 = MultipleSingleton.getInstance(1);
        MultipleSingleton instanceB1 = MultipleSingleton.getInstance(1);
        MultipleSingleton instanceA2 = MultipleSingleton.getInstance(2);
        MultipleSingleton instanceA3 = MultipleSingleton.getInstance(3);
        Assertions.assertEquals(instanceA1, instanceB1);
        Assertions.assertNotEquals(instanceA1, instanceA2);
        Assertions.assertNotEquals(instanceA2, instanceA3);

        Runtime.getRuntime();
    }


    // 实例：


    // java.lang.Runtime#getRuntime()

//     public class Runtime {
//        private static final Runtime currentRuntime = new Runtime();
//
//        public static Runtime getRuntime() {
//            return currentRuntime;
//        }
//
//        /** Don't let anyone else instantiate this class */
//        private Runtime() {}
//          ......
//     }

    // Mybatis ErrorContext#instance()
    // 这里的 ThreadLocal 相当于【线程唯一单例】里的 ConcurrentHashMap

//    public class ErrorContext {
//        private static final String LINE_SEPARATOR = System.getProperty("line.separator","\n");
//        private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<ErrorContext>();
//
//        private ErrorContext stored;
//        private String resource;
//        private String activity;
//        private String object;
//        private String message;
//        private String sql;
//        private Throwable cause;
//
//        private ErrorContext() {
//        }
//
//        public static ErrorContext instance() {
//            ErrorContext context = LOCAL.get();
//            if (context == null) {
//                context = new ErrorContext();
//                LOCAL.set(context);
//            }
//            return context;
//        }
//    }




}
