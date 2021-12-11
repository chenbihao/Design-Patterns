package src.command;

/**
 * @author: chenbihao
 * @create: 2021/12/11
 * @Description: 恐惧
 */
public class CommandR extends Command {

    public CommandR(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public String execute() {

        return gameEngine.attack() + gameEngine.fear();
    }
}
