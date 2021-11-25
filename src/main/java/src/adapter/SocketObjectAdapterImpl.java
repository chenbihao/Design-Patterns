package src.adapter;

/**
 * @author: chenbihao
 * @create: 2021/11/25
 * @Description: 对象适配器
 */
public class SocketObjectAdapterImpl implements SocketAdapter {

    /**
     * 对象适配器，利用组合的方式
     */
    private Socket sock = new Socket();

    @Override
    public Volt get120Volt() {
        return sock.getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt v = sock.getVolt();
        return convertVolt(v, 10);
    }

    @Override
    public Volt get3Volt() {
        Volt v = sock.getVolt();
        return convertVolt(v, 40);
    }

    private Volt convertVolt(Volt v, int i) {
        return new Volt(v.getVolts() / i);
    }
}
