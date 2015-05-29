package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.Event;
import com.quest.solutions.run.db.entity.item.Ammunition;
import com.quest.solutions.run.db.entity.item.ItemInventory;
import com.quest.solutions.run.db.entity.item.Key;
import com.quest.solutions.run.db.entity.item.Medicine;
import com.quest.solutions.run.db.entity.item.Weapon;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.context.NavigationContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arle0814
 */
public class GameLoaderSourceContext {

    private List<Ammunition> amunitions;
    private List<Street> streets;
    private List<Room> rooms;
    private List<Medicine> medicines;
    private List<Weapon> weapons;
    private List<Key> keys;
    private List<ItemInventory> itemInventory;
    private Player player;
    private List<Monster> monsters;
    private List<Event> events;
    private NavigationContext navigationContext;

    public GameLoaderSourceContext() {
        this.amunitions = new ArrayList<>();
        this.streets = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.medicines = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.itemInventory = new ArrayList<>();
        this.player = new Player();
        this.monsters = new ArrayList<>();
        this.events = new ArrayList<>();
        this.navigationContext = new NavigationContext();
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Ammunition> getAmunitions() {
        return amunitions;
    }

    public void setAmunitions(List<Ammunition> amunitions) {
        this.amunitions = amunitions;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public List<ItemInventory> getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(List<ItemInventory> itemInventory) {
        this.itemInventory = itemInventory;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public NavigationContext getNavigationContext() {
        return navigationContext;
    }

    public void setNavigationContext(NavigationContext navigationContext) {
        this.navigationContext = navigationContext;
    }
    
    

}
