package edu.sdccd.cisc191.f.entities;

import edu.sdccd.cisc191.f.cards.Card;
import edu.sdccd.cisc191.f.cards.Deck;

public class Player extends Entity {
    protected int energy;
    protected int gold;
    protected Deck<Card> deck;

    public Player(String name, int health, int block, int energy, int gold) {
        super(name, health, block);
        this.energy = energy;
        this.gold = gold;
    }


    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Deck<Card> getDeck() {
        return deck;
    }

    public void setDeck(Deck<Card> deck) {
        this.deck = deck;
    }
}
