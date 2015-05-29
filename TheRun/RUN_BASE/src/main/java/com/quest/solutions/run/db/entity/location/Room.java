package com.quest.solutions.run.db.entity.location;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.item.Item;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * Created by arle0814 on 29.01.2015.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Room implements Location {

    @XmlElement
    private String id;
    @XmlElement
    private String name;
    @XmlElement
    private String image;
    @XmlElement
    private boolean isOpen;
    @XmlElement
    private String description;

    @XmlElementWrapper
    @XmlElement(name = "childRoom")
    private List<Room> childRooms;

    @XmlTransient
    private List<Item> items = new ArrayList<>();

    @XmlTransient
    private List<Monster> monsters = new ArrayList<>();

    @XmlTransient
    private List<Player> npc = new ArrayList<>();

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public List<Room> getChildRooms() {
        return childRooms;
    }

    public void setChildRooms(List<Room> childRooms) {
        this.childRooms = childRooms;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Player> getNpc() {
        return npc;
    }

    public void setNpc(List<Player> npc) {
        this.npc = npc;
    }

    @Override
    public Class<Room> getType() {
        return Room.class;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
}
