package src.command;

/**
 * @author: chenbihao
 * @create: 2021/12/11
 * @Description: 眩晕
 */
public class CommandW extends Command{

    public CommandW(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public String execute() {
        return gameEngine.vertigo();
    }
}
