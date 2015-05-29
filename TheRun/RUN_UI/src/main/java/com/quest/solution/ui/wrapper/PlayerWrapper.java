package com.quest.solution.ui.wrapper;

import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.item.Ammunition;
import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.item.ItemInventory;
import com.quest.solutions.run.db.entity.item.Key;
import com.quest.solutions.run.db.entity.item.Medicine;
import com.quest.solutions.run.db.entity.item.Weapon;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arle0814
 */
public class PlayerWrapper {

    private static final String IMAGE_PATH_PLAYER = "/gamesource/players/";
    private static final String IMAGE_PATH_AMMO = "/gamesource/ammo/";
    private static final String IMAGE_PATH_WEAPON = "/gamesource/weapon/";
    private static final String IMAGE_PATH_MEDICINE = "/gamesource/medicine/";
    private static final String IMAGE_PATH_ITINVENTORY = "/gamesource/itemInventory/";
    private static final String IMAGE_PATH_KEY = "/gamesource/keys/";

    private Map<String, ItemWrapper> items;
    private int health;
    private int mentalState;
    private String name;
    private String image;

    public PlayerWrapper(Player player) {
        this.health = player.getHealth();
        this.mentalState = player.getMentalState();
        this.name = player.getName();
        this.items = new HashMap<>();
        this.image = IMAGE_PATH_PLAYER + player.getImage();
        this.init(player);
    }

    public Map<String, ItemWrapper> getItems() {
        return items;
    }

    public void setItems(Map<String, ItemWrapper> items) {
        this.items = items;
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

    private void init(Player player) {
        if (player.getItems() != null && !player.getItems().isEmpty()) {
            for (Item it : player.getItems()) {
                if (it instanceof Weapon) {
                    items.put(((Weapon) it).getId(), new ItemWrapper(((Weapon) it).getId(), ((Weapon) it).getName(),
                            IMAGE_PATH_WEAPON + ((Weapon) it).getImage(), ((Weapon) it).getDescription()));
                }
                if (it instanceof Medicine) {
                    items.put(((Medicine) it).getId(), new ItemWrapper(((Medicine) it).getId(), ((Medicine) it).getName(),
                            IMAGE_PATH_MEDICINE + ((Medicine) it).getImage(), ((Medicine) it).getDescription()));
                }
                if (it instanceof Key) {
                    items.put(((Key) it).getId(), new ItemWrapper(((Key) it).getId(), ((Key) it).getName(),
                            IMAGE_PATH_KEY + ((Key) it).getImage(), ((Key) it).getDescription()));
                }
                if (it instanceof Ammunition) {
                    items.put(((Ammunition) it).getId(), new ItemWrapper(((Ammunition) it).getId(), ((Ammunition) it).getName(),
                            IMAGE_PATH_AMMO + ((Ammunition) it).getImage(), ((Ammunition) it).getDescription()));
                }

                if (it instanceof ItemInventory) {
                    items.put(((ItemInventory) it).getId(), new ItemWrapper(((ItemInventory) it).getId(), ((ItemInventory) it).getName(),
                            IMAGE_PATH_ITINVENTORY + ((ItemInventory) it).getImage(), ((ItemInventory) it).getDescription()));
                }
            }
        }
    }

}
