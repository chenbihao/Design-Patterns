package src.command;

/**
 * @author: chenbihao
 * @create: 2021/12/11
 * @Description: 沉睡
 */
public class CommandE extends Command{

    public CommandE(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public String execute() {
        return gameEngine.sleep();
    }
}
