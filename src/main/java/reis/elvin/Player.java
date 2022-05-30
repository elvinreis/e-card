package reis.elvin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    private List<Card> cards;
    private Card activeCard;
    private int score;
    private boolean isEmperorCardTime = false;
    private boolean turn;

    public Player() {

        this.cards = new ArrayList<>();
        this.activeCard = null;
    }

    public void play() {

        int choice = new Random().nextInt(cards.size());

        Card randomCard = this.getCards().get(choice);

        this.activeCard = randomCard;          
    }

    public void play(int choice) {

        this.setActiveCard(this.getCards().get(choice));              
    }

    public Card getActiveCard() {
        return activeCard;
    }

    public void setActiveCard(Card activeCard) {
        this.activeCard = activeCard;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isEmperorCardTime() {
        return isEmperorCardTime;
    }

    public void setEmperorCardTime() {
        this.isEmperorCardTime = !this.isEmperorCardTime;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn() {
        this.turn = !turn;
    }
}
