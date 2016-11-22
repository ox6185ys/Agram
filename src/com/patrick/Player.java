package com.patrick;

public abstract class Player {
    public void setWins(int winsPoint) {
        this.wins += winsPoint;
    }

    int wins = 0;

    public int getCurrentScore() {//Alt insert generate getter and setter.
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {//Alt insert generate getter and setter.
        this.currentScore += currentScore;
    }

    int currentScore = 0;
    String name;

    Hand handOfCards = new Hand();

    PlayerManager manager;
    boolean isPlayerOne = false;

    Player(String name) {
        this.name = name;
    }

    Player(String name, PlayerManager manager) {

        this.name = name;
        this.manager = manager;

    }

    //Insist that subclasses implement this method.
    //public abstract void playHand(Deck allCards);

    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    public void setPlayerOne(boolean playerOne) {
        isPlayerOne = playerOne;
    }

    public void wonRound() {
        wins += 1;
    }

    //Getters and setters.
    public void setManager(PlayerManager manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public Hand getHandOfCards() {
        return handOfCards;
    }

    public void setHand(Hand handOfCards) {
        this.handOfCards = handOfCards;
    }

    public int getWins() {
        return wins;
    }

    public Card humanSelectCardToPlay() {
        while(true) {
            handOfCards.displayHand();
            String prompt = "Please select a card to play.";
            int userChoice = Game.ui.numInput(prompt);
            if (userChoice <= handOfCards.size()) {
                Card selectedCard = handOfCards.playCard(userChoice);
                //Returns a card to PlayerManager.TODO Add this card to the pile of played cards.
                return selectedCard;
            }else{
                System.out.println("Please select valid card");
            }
        }

    }
    public Card computerSelectCardToPlay(){
        handOfCards.displayHand();
        int ComputerChoice = 1;
        Card selectedCard = handOfCards.playCard(ComputerChoice);
        return selectedCard;
    }

    @Override
    public String toString() {
        String playerOne = (isPlayerOne) ? "* is currently Player One" : "(is not Player One)";
        return name + " " + playerOne;
    }
}