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


}
