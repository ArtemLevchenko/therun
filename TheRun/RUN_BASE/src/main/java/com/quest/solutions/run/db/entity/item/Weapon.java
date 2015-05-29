package com.quest.solutions.run.db.entity.item;

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
public class Weapon implements Item<Weapon> {

    @XmlElement
    private String id;
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private String image;
    @XmlElement
    private AmmoType ammoType;
    @XmlElement
    private int chanceOfHitting;
    @XmlElement
    private int damage;

    public Weapon() {
    }

    public Weapon(String id, String name, String description, String image, int chanceOfHitting, int damage, AmmoType ammoType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.chanceOfHitting = chanceOfHitting;
        this.damage = damage;
        this.ammoType = ammoType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AmmoType getAmmoType() {
        return ammoType;
    }

    public void setAmmoType(AmmoType ammoType) {
        this.ammoType = ammoType;
    }

    public int getChanceOfHitting() {
        return chanceOfHitting;
    }

    public void setChanceOfHitting(int chanceOfHitting) {
        this.chanceOfHitting = chanceOfHitting;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public Class<Weapon> getType() {
        return Weapon.class;
    }

    @Override
    public String toString() {
        return name;
    }

}
