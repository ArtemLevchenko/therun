package com.quest.solutions.run.db.entity.events;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.item.Ammunition;
import com.quest.solutions.run.db.entity.item.ItemInventory;
import com.quest.solutions.run.db.entity.item.Key;
import com.quest.solutions.run.db.entity.item.Medicine;
import com.quest.solutions.run.db.entity.item.Weapon;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arle0814
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GlobalEvent implements Event {

    @XmlElement
    private String id;
    @XmlElement
    private String description;
    @XmlElementWrapper
    @XmlElement(name = "weapon")
    private List<Weapon> weapons;
    @XmlElementWrapper
    @XmlElement(name = "medicine")
    private List<Medicine> medicines;
    @XmlElementWrapper
    @XmlElement(name = "ammunition")
    private List<Ammunition> ammunitions;
    @XmlElementWrapper
    @XmlElement(name = "key")
    private List<Key> keys;
    @XmlElementWrapper
    @XmlElement(name = "itemInvetory")
    private List<ItemInventory> itemInvetories;
    @XmlElementWrapper
    @XmlElement(name = "monster")
    private List<Monster> monsters;
    @XmlElementWrapper
    @XmlElement(name = "player")
    private List<Player> players;
    @XmlElement
    private TypeEvent typeEvent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<Ammunition> getAmmunitions() {
        return ammunitions;
    }

    public void setAmmunitions(List<Ammunition> ammunitions) {
        this.ammunitions = ammunitions;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public List<ItemInventory> getItemInvetories() {
        return itemInvetories;
    }

    public void setItemInvetories(List<ItemInventory> itemInvetories) {
        this.itemInvetories = itemInvetories;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }
    
    

    @Override
    public Class<GlobalEvent> getType() {
        return GlobalEvent.class;
    }

}
