package src.command;

/**
 * @author: chenbihao
 * @create: 2021/12/11
 * @Description: 攻击
 */
public class CommandQ extends Command{

    public CommandQ(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public String execute() {
        return gameEngine.attack();
    }
}
