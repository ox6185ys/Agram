package com.patrick;

import java.util.LinkedList;
import java.util.Random;

public class Deck {
    private LinkedList<Card> cards;

    protected Random rng;

    public void setRng(Random rng) {
        this.rng = rng;
    }

    public Deck() {

        rng = new Random();

        this.cards = new LinkedList<Card>();

        for (Card.Suit suit : Card.Suit.values()) {

            for (int v = 1; v <= 13; v++) {
                if (v == 1) {
                    Card c = new Card(v, suit, Card.ACE);
                    cards.add(c);
                } else if (v > 1 && v <= 10) {
                    Card c = new Card(v, suit, Card.NOT_FACE_NOT_ACE);
                    cards.add(c);
                } else if (v == 11) {
                    Card c = new Card(10, suit, Card.JACK);
                    cards.add(c);
                } else if (v == 12) {
                    Card c = new Card(10, suit, Card.QUEEN);
                    cards.add(c);
                } else if (v == 13) {
                    Card c = new Card(10, suit, Card.KING);
                    cards.add(c);
                }
            }
        }

    }

    public int cardsLeft() {
        return cards.size();
    }

    public Card deal() {

        int cardsLeft = this.cards.size();
        int cardpick = rng.nextInt(cardsLeft);
        return cards.remove(cardpick);   //delete this card so it is not drawn again

    }
}
/*
    THE PACK
    The kings, queens, jacks, the 2s of all suits and the ace of
    spades are removed from the deck. The cards of each suit rank,
    from high to low: A, 10, 9, 8, 7, 6, 5, 4, 3. Because the ace of
    spades (called "Chief"') is removed from the deck, the highest card
    in the spade suit is the 10.
*/
