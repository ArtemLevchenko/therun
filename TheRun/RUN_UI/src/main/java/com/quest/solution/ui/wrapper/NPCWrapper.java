package com.quest.solution.ui.wrapper;

/**
 *
 * @author arle0814
 */
public class NPCWrapper {

    private String name;
    private int health;

    public NPCWrapper(String name, int health) {
        this.name = name;
        this.health = health;
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

    @Override
    public String toString() {
        return name;
    }

}
