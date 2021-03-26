package edu.sdccd.cisc191.f.cards;
import edu.sdccd.cisc191.f.entities.Entity;

import java.util.List;

public abstract class Card {
    protected String name;
    protected int cost;
    protected CardType cardType;
    protected String description;
    public int damageTarget;
    public int damageAll;
    public int armorSelf;
}
