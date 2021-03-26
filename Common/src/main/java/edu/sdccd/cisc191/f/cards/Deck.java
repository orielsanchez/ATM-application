package edu.sdccd.cisc191.f.cards;

import java.util.Collections;
import java.util.List;

public class Deck <Card> {
    private List<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card draw() {
        return deck.remove(0);
    }
}
