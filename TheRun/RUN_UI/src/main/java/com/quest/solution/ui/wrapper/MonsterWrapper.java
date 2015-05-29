package com.quest.solution.ui.wrapper;

/**
 *
 * @author arle0814
 */
public class MonsterWrapper {

    private String id;
    private String type;
    private int health;

    public MonsterWrapper(String id, String type, int health) {
        this.id = id;
        this.type = type;
        this.health = health;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return type;
    }
   

}
