package edu.sdccd.cisc191.f.rooms;

import edu.sdccd.cisc191.f.entities.Player;

public abstract class Room {
    protected Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
