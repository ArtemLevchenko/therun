package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.globalsetting.GlobalStaticData;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.item.ItemInventory;
import com.quest.solutions.run.db.entity.item.ItemInventoryType;
import com.quest.solutions.run.db.entity.location.Location;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author arle0814
 */
public class TakeEngine extends BaseEngine {

    private static final String ITEM_NOT_FOUND = "Item not found in Location!";
    private static final String MONSTER_WAS = "I don't take. Monster was in location!";
    private static final String FULL_INVENTORY = "Inventory is full!";
    private static final String ADD = "Item added!";
    private static final String NOT_NOW = "I do not need it now!";

    private Player player;
    private Location currentLocation;

    public TakeEngine(Player player, Location currentLocation, String otherText) {
        this.player = player;
        this.currentLocation = currentLocation;
        this.proxyText = new EventProxyListener(otherText, "", "", ResponseEventType.RESULT);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    private Item findItemInLocation() {
        Item foundItem = null;
        if (proxyText.getRequest() != null && !proxyText.getRequest().isEmpty()) {
            if (this.currentLocation instanceof Street) {
                for (Item it : ((Street) this.currentLocation).getItems()) {
                    if (it.toString().equalsIgnoreCase(proxyText.getRequest())
                            && ((Street) this.currentLocation).getMonsters().isEmpty()) {
                        foundItem = it;
                        break;
                    }
                }
            } else { // room
                for (Item it : ((Room) this.currentLocation).getItems()) {
                    if (it.toString().equalsIgnoreCase(proxyText.getRequest())
                            && ((Room) this.currentLocation).getMonsters().isEmpty()) {
                        foundItem = it;
                        break;
                    }
                }
            }
        }
        return foundItem;
    }

    private void removeFromLocation(Item item) {
        if (this.currentLocation instanceof Street) {
            ((Street) this.currentLocation).getItems().remove(item);
        } else { // room
            ((Room) this.currentLocation).getItems().remove(item);
        }
    }

    private void addedItemInInventory(Item item) {
        if (player.getItems().size() >= GlobalStaticData.INVENTORY_SIZE) {
            proxyText.setResponse(FULL_INVENTORY);
        } else {
            if (item instanceof ItemInventory) { // check item invetory to scenario
                if (ItemInventoryType.OTHER
                        == ((ItemInventory) item).getItemInventoryType()) {
                    boolean hasEqual = false;
                    for (ItemInventory it : player.getDocuments()) {
                        if (((ItemInventory) item).getDescription().equalsIgnoreCase(it.getDescription())) {
                            player.getItems().add(item);
                            this.removeFromLocation(item);
                            hasEqual = true;
                            break;
                        }
                    }
                    proxyText.setResponse(hasEqual ? ADD : NOT_NOW);
                    return;
                }
            }
            // other type
            player.getItems().add(item);
            proxyText.setResponse(ADD);
            this.removeFromLocation(item);
        }
    }

    @Override
    public void run() {
        Item item = this.findItemInLocation();
        if (item != null) { // if find
            this.addedItemInInventory(item);
        } else {
            if (this.currentLocation instanceof Street) {
                if (((Street) this.currentLocation).getMonsters().isEmpty()) {
                    proxyText.setResponse(ITEM_NOT_FOUND);
                } else {
                    proxyText.setResponse(MONSTER_WAS);
                }
            } else {
                if (((Room) this.currentLocation).getMonsters().isEmpty()) {
                    proxyText.setResponse(ITEM_NOT_FOUND);
                } else {
                    proxyText.setResponse(MONSTER_WAS);
                }
            }

        }
    }

}
