package com.patrick;
//Adds players.
//Determines trick structure.
//Prints trick winner.

import java.util.LinkedList;
public class PlayerManager {
    LinkedList<Player> players;
    LinkedList<Card> cardsThatWereAlreadyPlayedThisRound;
    Deck deck = new Deck();
    final static int MAX_CARDS_IN_HAND = 6; //Variable in Hand & Player.

    public final int MAX_PLAYERS = 5; //for a single deck, anyway, to allow for a possible max of 5 cards per player

    public PlayerManager(LinkedList<Player> players) {
        this.players = players;

        //only take the first 5 players
        while (players.size() > MAX_PLAYERS) {
            System.out.println("More than 5 players, removing players from end of list");
            System.out.println(players.removeLast().getName());
        }
        //setDealer();
    }

    public PlayerManager() {
        this.players = new LinkedList<Player>();
        this.cardsThatWereAlreadyPlayedThisRound = new LinkedList<Card>();
    }


    public void add(Player player) {
        //refuse to add more than 5 players
        if (players.size() < MAX_PLAYERS) {
            player.setManager(this);
            players.add(player);
            //setDealer();
        } else {
            Game.ui.output("There's already " + MAX_PLAYERS + " in the game. Can't add " + player.getName());
        }
    }

    public void oneTrick(){
        players.get(0).setPlayerOne(true);
// 1. PlayerOne plays a card
// 2. Each other player tries to match the suit with as high a card as possible.
// 3. This method compares those cards to PlayerOne's card.
// 4. Gives players who matched the suit points for their cards.
// 5. Figure out who PlayerOne is now.
// 6. Sets new playerOne to True, Switches old to false.
       for (Player p : players){
           //checking for playerone and if they're human.
           //since instanceof human, play a card.
           if (p.isPlayerOne && p instanceof HumanPlayer){
               Card playedCard = p.humanSelectCardToPlay();
               playedCard.setAlpha(true);
               cardsThatWereAlreadyPlayedThisRound.add(playedCard);

           }
           if(!p.isPlayerOne && p instanceof HumanPlayer){
               System.out.print("Player One Played : " + cardsThatWereAlreadyPlayedThisRound.get(0) + "\n");
               Card playedCard = p.humanSelectCardToPlay();
               cardsThatWereAlreadyPlayedThisRound.add(playedCard);
           }
           else {
               System.out.println("Computer's place/turn.");
               //TODO: this is where the computer turn should go.
           }
       }
        Player trick_winner = determineTrickWinner();
        setPlayers(trick_winner);
        //Put cards back in the deck.
        deck.cards.addAll(cardsThatWereAlreadyPlayedThisRound);
        cardsThatWereAlreadyPlayedThisRound.clear();//Creates Empty list.  Next round starts cleanly.
        }

    public void setPlayers(Player trick_winner){
        //remove winner.
        players.remove(trick_winner);
        //add winner as first.
        players.addFirst(trick_winner);
        //shift all players down one.
        //TODO: reorder so the circle remains intact.
        for (Player p : players){
            p.setPlayerOne(false);
        }
        players.getFirst().setPlayerOne(true);
    }
    public void dealHands(){
        //For the number of cards a player is dealt
        for (int i = 0; i < 6; i ++){
            //Deal one card to each player.
            for (Player p : players){
                Card dealtCard = deck.deal();
                p.handOfCards.addCard(dealtCard);
            }
        }
    }


    public void printWins() {
        //TODO: make this the player with the highest score.
        for (Player p : players) {
            int wins = p.getWins();
            System.out.println(p.getName() + " won " + wins + " times");
        }
    }
    public void printTrickWinner(Player trickWinner){
        System.out.format("%s is the winner and has %d points",trickWinner.getName(),trickWinner.getCurrentScore());
    }

    public void printPlayerScores(){
        for (Player p : players){
            int score = p.getCurrentScore();
            System.out.format("| Player: %s      Score:  %d |", p.getName(), score);
        }
    }


    public void printFinalWins() {

        int mostWins = 0;
        String winnerName = null;

        for (Player p : players) {
            int wins = p.getWins();
            System.out.println(p.getName() + " won " + wins + " times");
            if (wins > mostWins) {
                mostWins = wins;
                winnerName = p.getName();
            }
        }

        Game.ui.output("The winner is " + winnerName);

    }

    public Player determineTrickWinner() {
        int counter =0;
        //set alpha card so we can compare to other cards easily.
        Card alpha_card = cardsThatWereAlreadyPlayedThisRound.removeFirst();
        int maxScore = alpha_card.getValue();
        //check cards
        Player winner = players.get(0);
        for (Card c : cardsThatWereAlreadyPlayedThisRound){
            counter ++;

            if (c.getSuit()== alpha_card.getSuit()){
                if (c.getValue() > alpha_card.getValue()){
                    winner = players.get(counter);
                    maxScore = c.getValue();
                }
            }
        }
        winner.setCurrentScore(maxScore);
        printTrickWinner(winner);
        return winner;


    }

    public LinkedList<Player> getPlayersList() {
        return players;
    }
}