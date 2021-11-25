package src.adapter;

/**
 * @author: chenbihao
 * @create: 2021/11/25
 * @Description: 适配器
 */
public interface SocketAdapter {

    Volt get120Volt();

    Volt get12Volt();

    Volt get3Volt();
}
