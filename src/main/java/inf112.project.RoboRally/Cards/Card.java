package inf112.project.RoboRally.Cards;

public class Card implements ICard{

    private int priority;
    private Action command;


    public Card(int priority, Action command) {
        this.priority = priority;
        this.command = command;
    }


    public int getPriority() {
        return priority;
    }

    public Action getCommand() {
        return command;
    }
}
