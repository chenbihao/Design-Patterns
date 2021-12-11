package src.command;

/**
 * @author: chenbihao
 * @create: 2021/12/11
 * @Description: 命令抽象类
 */
public abstract class Command {
    /**
     *  实际接收者
     */
    protected GameEngine gameEngine;

    public Command(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     *  大部分命令只处理如何将请求传递到接收者的细节
     *  如有需要，也可以在这个类里添加一些附加功能，如撤销、队列、延迟等。
     */
    public abstract String execute();

}
