package edu.sdccd.cisc191.f.entities;

public abstract class Entity {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int armor;
    protected boolean friendly;
    protected double attackMultiplier = 1.0;
    protected double blockMultiplier = 1.0;
    protected boolean isDead = false;

    public int TakeDamage(int amount) {
        if (armor > 0) {
            armor -= amount;
            if (armor < 0) {
                amount = -armor;
                armor = 0;
            } else {
                amount = 0;
            }
        }
        int damage = java.lang.Math.min(health,amount);
        health -= amount;
        updateHealth();
        return damage;
    }

    public void Heal(int amount) {
        health += amount;
        updateHealth();
    }

    protected void updateHealth() {
        if (health < 0) {
            health = 0;
        }
        else if (health > maxHealth) health = maxHealth;
    }

    public Entity(String name, int maxHealth, int armor) {
        this.name = name;
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    public double getAttackMultiplier() {
        return attackMultiplier;
    }

    public void setAttackMultiplier(double attackMultiplier) {
        this.attackMultiplier = attackMultiplier;
    }

    public double getBlockMultiplier() {
        return blockMultiplier;
    }

    public void setBlockMultiplier(double blockMultiplier) {
        this.blockMultiplier = blockMultiplier;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
