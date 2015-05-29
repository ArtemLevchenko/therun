package com.quest.solutions.run.db.entity.characters;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arle0814
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Monster implements Serializable {

    @XmlElement
    private String id;
    @XmlElement
    private MonsterType monsterType;
    @XmlElement
    private int health;
    @XmlElement
    private int damage;
    @XmlElement
    private int chanceToHit;
    @XmlElement
    private int chanceToEscape;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getChanceToHit() {
        return chanceToHit;
    }

    public void setChanceToHit(int chanceToHit) {
        this.chanceToHit = chanceToHit;
    }

    public int getChanceToEscape() {
        return chanceToEscape;
    }

    public void setChanceToEscape(int chanceToEscape) {
        this.chanceToEscape = chanceToEscape;
    }

}
