package edu.sdccd.cisc191.f.server.rooms;

import edu.sdccd.cisc191.f.server.entities.Enemy;

public abstract class AbstractEnemyArea extends Room {
    protected Enemy enemy;
    protected boolean battleWon;

    public abstract void generateEnemy();
}
