package com.quest.solutions.run.db.entity.location;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.item.Item;
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
public class Street implements Location {

    @XmlElement
    private String id;
    @XmlElement
    private String name;
    @XmlElement
    private String image;
    @XmlElement
    private String description;
    @XmlElement
    private int size;

    @XmlTransient
    private List<Room> relatedRooms;

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

    public List<Room> getRelatedRooms() {
        return relatedRooms;
    }

    public void setRelatedRooms(List<Room> relatedRooms) {
        this.relatedRooms = relatedRooms;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public Class<Street> getType() {
        return Street.class;
    }

    @Override
    public String toString() {
        return name;
    }

}
