package com.patrick;
/*
    THE PACK
    The kings, queens, jacks, the 2s of all suits and the ace of
    spades are removed from the deck. The cards of each suit rank,
    from high to low: A, 10, 9, 8, 7, 6, 5, 4, 3. Because the ace of
    spades (called "Chief"') is removed from the deck, the highest card
    in the spade suit is the 10.
*/

import java.util.InputMismatchException;
import java.util.Scanner;
public class UI {
    static Scanner stringScanner = new Scanner(System.in);
    static Scanner numberScanner = new Scanner(System.in);

    public void output(String output) {
        System.out.println(output);
    }

    public String input() {
        return stringScanner.nextLine();
    }

    public String input(String prompt) {
        output(prompt);
        return stringScanner.nextLine();
    }
    public void numOutput(String output){
        System.out.println(output);
    }
    public int numInput(){
        return numberScanner.nextInt();
    }
    public int numInput(String prompt) {
        while (true) {
            numOutput(prompt);
            try {
                return numberScanner.nextInt();
            }catch(InputMismatchException ime){
                System.out.println("Enter a valid integer");
                numberScanner.next();
            }
        }
    }

    public static void close() {
        numberScanner.close();
        stringScanner.close();
    }
}