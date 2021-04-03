package edu.sdccd.cisc191.f.server.entities;

public abstract class Enemy extends Entity {

    public Enemy(String name, int health, int block) {
        super(name, health, block);
    }

    public void attack(Player player) {

    }

    public void defend() {

    }
}
