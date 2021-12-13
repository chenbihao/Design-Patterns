package src.mediator;

/**
 * @author: chenbihao
 * @create: 2021/12/13
 * @Description: 组件接口
 */
public interface Component {

    /**
     * 设置中介者引用
     */
    void setMediator(Mediator mediator);

    Type getType();

}
