package com.patrick;

import java.util.Collections;

public class Game {
    final static int MAX_TRICKS = 6;
    int current_trick = 0;

    static UI ui;

    public static void main(String[] args) {

        createUI();

        Game game = new Game();
        game.play();

        UI.close();

    }

    public static void createUI() {
        ui = new UI();
    }
    public void setupPlayerObjects(int numberOfPlayers, PlayerManager pm){
        // Takes the integer value passed to it.
        //Iterates from 1 to that integer.
        //Create one player per iteration.
        String namePrompt = "Please enter the name of the player\n";
        String typeOfPlayer = " Please choose:\n1. Human\n2. Computer";
        //for loop over 'number_of_pllayers' til it gets to last player.
        for (int i = 0; i < numberOfPlayers; i ++){
            String name = ui.input(namePrompt);
            String type = ui.input(typeOfPlayer);
            //in the loop each player gets a name from ui.input.
            //each player gets a name from player_name = ui.input(player_name_prompt) make a String player_name_prompt.
            //make humanplayer object.
            if (type.equals("1")){
                HumanPlayer newPlayer = new HumanPlayer(name);
                pm.add(newPlayer);
            }
            else if(type.equals("2")){
                ComputerPlayer newPlayer = new ComputerPlayer(name);
                pm.add(newPlayer);
            }else{
                System.out.println("Player not added to player list");
            }
            //TODO figure out validation Im too tired to think
        }
    }

    private void play() {
        //Create the player manager.

        boolean playAgain = true;
        String anotherRound;
        //TODO: make outer while loop for playing more games of Agram.
        //new deck, collections.shuffle(playerManager.players)
        //reset all before new game.
        //TODO: This wile loop is the continue playing loop, uncomment it and finish it.
        PlayerManager playerManager = new PlayerManager();
        String number_of_players_prompt = "How many new players?\nMax of 5 players. ";
        //TODO: validate input for integers only and 2 to 5 players.
        int number_of_players = ui.numInput(number_of_players_prompt);
        //Set up players for game.
        setupPlayerObjects(number_of_players, playerManager);

        while (playAgain){
            //Deal players their starting hands.
            playerManager.dealHands();

            //Shuffle players at the beginning of game.
            Collections.shuffle(playerManager.players);
            while (current_trick < 6) {
                //start trick from PlayerManager.
                System.out.format("Trick Number : %d\n ", current_trick + 1);
                playerManager.oneTrick(current_trick);
                current_trick++;
                //playerManager.printWins();
            }
            playerManager.printFinalWins();
            anotherRound = ui.input("Another hand? n to quit, anything else to continue... ");
            playAgain = !anotherRound.equals("n");   // another.equals("n") returns a boolean
            //If the players want to play more then reset the trick number.
            if (playAgain){
                current_trick = 0;
            }else{
                System.out.println("UU=====>Thanks for playing<======UU");
            }

            //rotate the dealer, so everyone gets turns to be the dealer.

        }


    }
}