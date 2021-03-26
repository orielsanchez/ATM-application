package edu.sdccd.cisc191.f.cards;

public class BlockCard extends Card {
    private int baseCost = 10;
    private int baseBlock;
    private final CardType cardType = CardType.SKILL;

    public BlockCard() {
    }

    public BlockCard(int baseCost, int baseBlock) {
        this.baseCost = baseCost;
        this.baseBlock = baseBlock;
    }




    public int getBaseCost() {
        return baseCost;
    }

    public int getBaseBlock() {
        return baseBlock;
    }

}
