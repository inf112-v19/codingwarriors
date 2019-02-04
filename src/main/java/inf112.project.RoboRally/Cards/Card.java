package inf112.project.RoboRally.Cards;

public class Card implements ICard, Comparable {

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
    public Action getCommand() {
        return command;
    }


    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (that.getClass() != this.getClass()) {
            return false;
        }
        Card otherCard = (Card) that;
        if (otherCard.getPriority() == this.getPriority()
                && otherCard.getCommand() == this.getCommand()) {
            return true;
        }
        return false;
    }


    @Override
    public int compareTo(Object that) {
        if (!this.equals(that)) {
            throw new IllegalArgumentException("Can't compare cards to other objects!");
        }

        Card otherCard = (Card) that;
        if (this.getPriority() > otherCard.getPriority()) {
            return 1;
        } else if (this.getPriority() < otherCard.getPriority()) {
            return -1;
        } else { // Cards have equal priority.
            return 0;
        }
    }
}
