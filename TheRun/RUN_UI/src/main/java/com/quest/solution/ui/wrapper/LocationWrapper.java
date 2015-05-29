package com.quest.solution.ui.wrapper;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.item.Ammunition;
import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.item.ItemInventory;
import com.quest.solutions.run.db.entity.item.Key;
import com.quest.solutions.run.db.entity.item.Medicine;
import com.quest.solutions.run.db.entity.item.Weapon;
import com.quest.solutions.run.db.entity.location.Location;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author arle0814
 */
public class LocationWrapper {

    private static final String IMAGE_PATH_ROOM = "/gamesource/room/";
    private static final String IMAGE_PATH_STREET = "/gamesource/street/";

    private String currentNameLocation;
    private String imageLocation;
    private String desciptionCurrentLocation;
    private List<String> streets;
    private List<String> rooms;
    private Map<String, ItemWrapper> itemInLocation;
    private Map<String, MonsterWrapper> monstersInLocation;
    private Map<String, NPCWrapper> npcInLocation;

    public LocationWrapper(Location currentLocation, List<Street> streetLocation, List<Room> roomLocation) {
        this.init(currentLocation, streetLocation, roomLocation);
    }

    private void initItems(List<Item> items) {
        itemInLocation = new HashMap<>();
        if (items != null && !items.isEmpty()) {
            for (Item it : items) {
                if (it instanceof Weapon) {
                    itemInLocation.put(((Weapon) it).getId(), new ItemWrapper(((Weapon) it).getId(), ((Weapon) it).getName(),
                            ((Weapon) it).getImage(), ((Weapon) it).getDescription()));
                }
                if (it instanceof Medicine) {
                    itemInLocation.put(((Medicine) it).getId(), new ItemWrapper(((Medicine) it).getId(), ((Medicine) it).getName(),
                            ((Medicine) it).getImage(), ((Medicine) it).getDescription()));
                }
                if (it instanceof Key) {
                    itemInLocation.put(((Key) it).getId(), new ItemWrapper(((Key) it).getId(), ((Key) it).getName(),
                            ((Key) it).getImage(), ((Key) it).getDescription()));
                }
                if (it instanceof Ammunition) {
                    itemInLocation.put(((Ammunition) it).getId(), new ItemWrapper(((Ammunition) it).getId(), ((Ammunition) it).getName(),
                            ((Ammunition) it).getImage(), ((Ammunition) it).getDescription()));
                }

                if (it instanceof ItemInventory) {
                    itemInLocation.put(((ItemInventory) it).getId(), new ItemWrapper(((ItemInventory) it).getId(), ((ItemInventory) it).getName(),
                            ((ItemInventory) it).getImage(), ((ItemInventory) it).getDescription()));
                }
            }
        }
    }

    private void initMonsters(List<Monster> monsters) {
        monstersInLocation = new HashMap<>();
        if (monsters != null && !monsters.isEmpty()) {
            for (Monster mon : monsters) {
                monstersInLocation.put(mon.getId(), new MonsterWrapper(mon.getId(), mon.getMonsterType().name(), mon.getHealth()));
            }
        }
    }

    private void initNPC(List<Player> players) {
        npcInLocation = new HashMap<>();
        if (players != null && !players.isEmpty()) {
            for (Player pl : players) {
                npcInLocation.put(pl.getName(), new NPCWrapper(pl.getName(), pl.getHealth()));
            }
        }
    }

    private void init(Location currentLocation, List<Street> streetLocation, List<Room> roomLocation) {
        this.currentNameLocation = currentLocation.toString();
        if (currentLocation instanceof Street) {
            this.desciptionCurrentLocation = ((Street) currentLocation).getDescription();
            this.imageLocation = IMAGE_PATH_STREET + ((Street) currentLocation).getImage();
            this.initItems(((Street) currentLocation).getItems());
            this.initMonsters(((Street) currentLocation).getMonsters());
            this.initNPC(((Street) currentLocation).getNpc());
        } else {
            this.desciptionCurrentLocation = ((Room) currentLocation).getDescription();
            this.imageLocation = IMAGE_PATH_ROOM + ((Room) currentLocation).getImage();
            this.initItems(((Room) currentLocation).getItems());
            this.initMonsters(((Room) currentLocation).getMonsters());
            this.initNPC(((Room) currentLocation).getNpc());
        }
        this.streets = new ArrayList<>();
        if (streetLocation != null && !streetLocation.isEmpty()) {
            for (Street street : streetLocation) {
                streets.add(street.getName());
            }
        }
        this.rooms = new ArrayList<>();
        if (roomLocation != null && !roomLocation.isEmpty()) {
            for (Room room : roomLocation) {
                rooms.add(room.getName());
            }
        }
    }

    public String getCurrentNameLocation() {
        return currentNameLocation;
    }

    public void setCurrentNameLocation(String currentNameLocation) {
        this.currentNameLocation = currentNameLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getDesciptionCurrentLocation() {
        return desciptionCurrentLocation;
    }

    public void setDesciptionCurrentLocation(String desciptionCurrentLocation) {
        this.desciptionCurrentLocation = desciptionCurrentLocation;
    }

    public List<String> getStreets() {
        return streets;
    }

    public void setStreets(List<String> streets) {
        this.streets = streets;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public Map<String, ItemWrapper> getItemInLocation() {
        return itemInLocation;
    }

    public void setItemInLocation(Map<String, ItemWrapper> itemInLocation) {
        this.itemInLocation = itemInLocation;
    }

    public Map<String, MonsterWrapper> getMonstersInLocation() {
        return monstersInLocation;
    }

    public void setMonstersInLocation(Map<String, MonsterWrapper> monstersInLocation) {
        this.monstersInLocation = monstersInLocation;
    }

    public Map<String, NPCWrapper> getNpcInLocation() {
        return npcInLocation;
    }

    public void setNpcInLocation(Map<String, NPCWrapper> npcInLocation) {
        this.npcInLocation = npcInLocation;
    }

}
