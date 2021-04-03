package edu.sdccd.cisc191.f.server.rooms;

import edu.sdccd.cisc191.f.server.cards.Card;

import java.util.HashMap;

/**
 * This class will be used to generate a Shop room where a player
 * will be able to purchase cards to add to his/her deck. The shop
 * will generate a random list of unique cards and assign them a random
 * cost between 25-50 gold. A player will be able to purchase one card,
 * or choose to purchase the option to remove a card. The player will
 * select a card, The cost will be deducted from the players gold, and
 * the card will be placed in the player's deck.
 */

public class Shop extends Room {

    private HashMap<Card, Integer> availableCards;

    public void generateCostOfCards() {

    }

}

