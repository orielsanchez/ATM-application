package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnemyResponse {
    private Integer id;
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int block;
    protected double attackMultiplier = 1.0;
    protected double blockMultiplier = 1.0;
    protected boolean isDead = false;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(EnemyResponse enemy) throws Exception {
        return objectMapper.writeValueAsString(enemy);
    }

    public static EnemyResponse fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, EnemyResponse.class);
    }

    protected EnemyResponse() {}

    public EnemyResponse(Integer id, String name, int health, int maxHealth, int block, double attackMultiplier, double blockMultiplier, boolean isDead) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.block = block;
        this.attackMultiplier = attackMultiplier;
        this.blockMultiplier = blockMultiplier;
        this.isDead = isDead;
    }

    @Override
    public String toString() {
        return String.format(
                "Enemy[id=%d, name=%s, health=%d, maxHealth=%d, block=%d, attackMultiplier=%f, blockMultiplier=%f, isDead=%b]",
                id,name,health,maxHealth,block,attackMultiplier,blockMultiplier,isDead);
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public int getMaxHealth() {return maxHealth;}

    public void setMaxHealth(int maxHealth) {this.maxHealth = maxHealth;}

    public int getBlock() {return block;}

    public void setBlock(int block) {this.block = block;}

    public double getAttackMultiplier() {return attackMultiplier;}

    public void setAttackMultiplier(double attackMultiplier) {this.attackMultiplier = attackMultiplier;}

    public double getBlockMultiplier() {return blockMultiplier;}

    public void setBlockMultiplier(double blockMultiplier) {this.blockMultiplier = blockMultiplier;}

    public boolean getIsDead() {return isDead;}

    public void setIsDead(boolean isDead) {this.isDead = isDead;}
}
