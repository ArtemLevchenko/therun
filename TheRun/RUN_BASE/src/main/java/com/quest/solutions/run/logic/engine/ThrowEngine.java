package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.location.Location;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author arle0814
 */
public class ThrowEngine extends BaseEngine {

    private static final String ITEM_NOT_FOUND = "Item not found in inventory!";

    private Player player;
    private Location currentLocation;

    public ThrowEngine(Player player, Location currentLocation, String otherText) {
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

    private Item findItemInInventory() {
        Item foundItem = null;
        if (proxyText.getRequest() != null && !proxyText.getRequest().isEmpty()) {
            for (Item it : this.player.getItems()) {
                if (it.toString().equalsIgnoreCase(proxyText.getRequest())) {
                    foundItem = it;
                    break;
                }
            }
        }
        return foundItem;
    }

    private void removeFromInventory(Item item) {
        player.getItems().remove(item);
    }

    private void addedItemInLocation(Item item) {
        if (this.currentLocation instanceof Street) {
            ((Street) this.currentLocation).getItems().add(item);
        } else { // room
            ((Room) this.currentLocation).getItems().add(item);
        }
    }

    @Override
    public void run() {
        Item item = this.findItemInInventory();
        if (item != null) { // if find
            this.addedItemInLocation(item);
            this.removeFromInventory(item);
        } else {
            proxyText.setResponse(ITEM_NOT_FOUND);
        }
    }
}
