package reis.elvin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import reis.elvin.factory.CardFactory;

public class Game {

    private Player playerOne;
    private Player playerTwo;

    private int turn = 1;
    // private int round;

    private boolean gameOver = false;

    private final int MAXCARDS = 5;
    private final int MINSCORE = 11;

    public Game() {

        this.playerOne = new Player();
        this.playerTwo = new Player();
    }

    private void init() {

        playerOne.setScore(0);
        playerTwo.setScore(0);

        playerOne.setEmperorCardTime();
        playerOne.setTurn();

        generatePlayerCards(playerOne);
        generatePlayerCards(playerTwo);

    }

    private int input() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        
        int choice = -1;

        try {

            System.out.print("\n Choose a card to play: ");
            line = bufferedReader.readLine();

        } catch (Exception e) {
        }

        choice = Integer.parseInt(line);

        return choice;
    }

    private void draw() {

        System.out.print("\033[H\033[2J");  
        System.out.flush();

        for(Card card : playerTwo.getCards()) {

            System.out.print("[/] ");
        }

        System.out.println("score: " + playerTwo.getScore());


        String playerOneActiveCard = playerOne.getActiveCard() != null?playerOne.getActiveCard().toString():" ";
        String playerTwoActiveCard = playerTwo.getActiveCard() != null?playerTwo.getActiveCard().toString():" ";
        
        System.out.println("\n\n["+playerOneActiveCard+" 1]\t["+playerTwoActiveCard+"]\n\n");

        for(Card card : playerOne.getCards()) {
            
            System.out.print("["+card+"] ");
        }

        System.out.println("score: " + playerOne.getScore());

        for(int i = 0; i < playerOne.getCards().size(); i++) {

           // System.out.print("["+i+"] "); 
            System.out.print(" "+i+"  "); 
        }

        System.out.println();
    }

    private void update() {
        play();
        comparePlayerCard(playerOne, playerTwo);
        comparePlayerCard(playerTwo, playerOne);
        checkTurn();
        checkWinner();
    }

    private void checkWinner() {

        if(playerOne.getScore() >= MINSCORE || playerTwo.getScore() >= MINSCORE) {
            
            gameOver = true;

            if(playerOne.getScore() > MINSCORE) {

                System.out.println("You won :)");
            
            }else {

                System.out.println("You've lost :(");
            }
        }
    }

    private void play() {

        int choice = input();

        if(playerTwo.isTurn()) {

            playerTwo.play();
            playerOne.play(choice);

        }else {

            playerOne.play(choice);
            playerTwo.play();
        }
    }

    private void checkTurn() {

        if(turn % 2 == 0){

            swapCards();
        }

        turn++;
    }

    public void start() {

        init();

        while (!gameOver) {

            draw();
//            input();
            update();
        }
    }

    private void comparePlayerCard(Player playerOne, Player playerTwo) {

        // player score situation
        if (playerOne.getActiveCard().getCardType() == CardType.EMPEROR
                && playerTwo.getActiveCard().getCardType() == CardType.CITIZEN) {

            playerOne.setScore(playerOne.getScore() + 1);
        }

        else if (playerOne.getActiveCard().getCardType() == CardType.CITIZEN
                    && playerTwo.getActiveCard().getCardType() == CardType.SLAVE) {

            playerOne.setScore(playerOne.getScore() + 2);
        }

        else if (playerOne.getActiveCard().getCardType() == CardType.SLAVE
                && playerTwo.getActiveCard().getCardType() == CardType.EMPEROR) {

            playerOne.setScore(playerOne.getScore() + 5);
        }
        /*
         * // player two score situation
         * else if (playerTwo.getActiveCard().getCardType() == CardType.EMPEROR
         * && playerOne.getActiveCard().getCardType() == CardType.CITIZEN) {
         * playerTwo.setScore(playerTwo.getScore() + 1);
         * }
         * 
         * else if (playerTwo.getActiveCard().getCardType() == CardType.CITIZEN
         * && playerOne.getActiveCard().getCardType() == CardType.SLAVE) {
         * playerTwo.setScore(playerTwo.getScore() + 2);
         * }
         * 
         * else if (playerTwo.getActiveCard().getCardType() == CardType.SLAVE
         * && playerOne.getActiveCard().getCardType() == CardType.EMPEROR) {
         * playerTwo.setScore(playerTwo.getScore() + 5);
         * }
         */
    }

    private void swapCards() {

        playerOne.getCards().clear();
        playerTwo.getCards().clear();

        playerOne.setEmperorCardTime();
        playerTwo.setEmperorCardTime();

        generatePlayerCards(playerOne);
        generatePlayerCards(playerTwo);

    }

    private void generatePlayerCards(Player player) {

        for (int i = 0; i < MAXCARDS - 1; i++) {

            player.getCards().add(new Card(CardType.CITIZEN));
        }

        Card card = player.isEmperorCardTime() ? CardFactory.createCard(CardType.EMPEROR)
                : CardFactory.createCard(CardType.SLAVE);

        player.getCards().add(card);
    }

}
