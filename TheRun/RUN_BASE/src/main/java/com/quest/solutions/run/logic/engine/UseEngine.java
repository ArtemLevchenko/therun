package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.item.Key;
import com.quest.solutions.run.db.entity.item.Medicine;
import com.quest.solutions.run.db.entity.item.MedicineType;
import com.quest.solutions.run.db.entity.location.Location;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.globalsetting.GlobalStaticData;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author Artem
 */
public class UseEngine extends BaseEngine {

    private static final String ITEM_NOT_FOUND = "Item not found in inventory!";
    private static final String USED_MEDICINE = "You used the medicine!";
    private static final String USED_KEY = "You used the key!";

    private Player player;
    private Location currentLocation;

    public UseEngine(Player player, Location currentLocation, String otherText) {
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
                if ((it instanceof Key || it instanceof Medicine)
                        && it.toString().equalsIgnoreCase(proxyText.getRequest())) {
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

    private void medicineProcess(Medicine med) {
        if (med.getMedicineType() == MedicineType.HEALTH) {
            if (player.getHealth() + med.getHealing() >= GlobalStaticData.MAX_HEALTH) {
                player.setHealth(GlobalStaticData.MAX_HEALTH);
            } else {
                player.setHealth(player.getHealth() + med.getHealing());
            }
        } else if (med.getMedicineType() == MedicineType.MENTAL) { // mental
            if (player.getMentalState() + med.getHealing() >= GlobalStaticData.MAX_STATE) {
                player.setMentalState(GlobalStaticData.MAX_STATE);
            } else {
                player.setMentalState(player.getMentalState() + med.getHealing());
            }
        }
        proxyText.setResponse(USED_MEDICINE);
    }

    private void keyProcess(Key key) {
        if (this.currentLocation instanceof Street) {
            // find rooms
            for (Room r : ((Street) currentLocation).getRelatedRooms()) {
                if (r.getName().equalsIgnoreCase(key.getDescription()) && !r.isOpen()) {
                    r.setOpen(true);
                    this.removeFromInventory(key);
                    proxyText.setResponse(USED_KEY);
                    return;
                }
            }
        } else if (this.currentLocation instanceof Room) { // room
            if (((Room) currentLocation).getChildRooms() != null
                    && !((Room) currentLocation).getChildRooms().isEmpty()) {
                for (Room r : ((Room) currentLocation).getChildRooms()) {
                    if (r.getName().equalsIgnoreCase(key.getDescription()) && !r.isOpen()) {
                        r.setOpen(true);
                        this.removeFromInventory(key);
                        proxyText.setResponse(USED_KEY);
                        return;
                    }
                }
            }
        }
    }

    private void processing(Item item) {
        if (item instanceof Medicine) {
            this.medicineProcess((Medicine) item);
            this.removeFromInventory(item);
        } else if (item instanceof Key) {
            this.keyProcess((Key) item);
        }
    }

    @Override
    public void run() {
        Item item = this.findItemInInventory();
        if (item != null) { // if find
            this.processing(item);
        } else {
            proxyText.setResponse(ITEM_NOT_FOUND);
        }
    }
}
