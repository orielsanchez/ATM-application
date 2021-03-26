package edu.sdccd.cisc191.f.rooms;

import edu.sdccd.cisc191.f.entities.Enemy;

public abstract class AbstractEnemyArea extends Room {
    protected Enemy enemy;
    protected boolean battleWon;

    public abstract void generateEnemy();
}
