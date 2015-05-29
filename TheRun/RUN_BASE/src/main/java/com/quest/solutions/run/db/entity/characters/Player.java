package com.quest.solutions.run.db.entity.characters;

import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.item.ItemInventory;
import com.quest.solutions.run.globalsetting.GlobalStaticData;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author arle0814
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Player {

    @XmlElement
    private String name;
    @XmlElement
    private int health;
    @XmlElement
    private int mentalState;
    @XmlElement
    private String image;
    // SIZE inventory
    @XmlTransient
    private List<Item> items = new ArrayList<>(GlobalStaticData.INVENTORY_SIZE);

    @XmlElement
    private boolean isNPC;

    @XmlTransient
    private List<ItemInventory> documents = new ArrayList<>();

    @XmlTransient
    private List<Player> helpPlayers = new ArrayList<>();

    public Player() {
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

    public int getMentalState() {
        return mentalState;
    }

    public void setMentalState(int mentalState) {
        this.mentalState = mentalState;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isIsNPC() {
        return isNPC;
    }

    public void setIsNPC(boolean isNPC) {
        this.isNPC = isNPC;
    }

    public List<ItemInventory> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ItemInventory> documents) {
        this.documents = documents;
    }

    public List<Player> getHelpPlayers() {
        return helpPlayers;
    }

    public void setHelpPlayers(List<Player> helpPlayers) {
        this.helpPlayers = helpPlayers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }
}
