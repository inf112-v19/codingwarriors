package inf112.project.RoboRally.cards;

public class Card implements ICard, Comparable<ICard> {

    private int priority;
    private Action command;


    public Card(int priority, Action command) {
        this.priority = priority;
        this.command = command;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Action getCardCommand() {
        return command;
    }

    @Override
    public int compareTo(ICard that) {
        if (this.getPriority() > that.getPriority()) {
            return 1;
        } else if (this.getPriority() < that.getPriority()) {
            return -1;
        } else { // cards have equal priority.
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Priority: " + priority + "\n Action: " + command + "\n";
    }
}
