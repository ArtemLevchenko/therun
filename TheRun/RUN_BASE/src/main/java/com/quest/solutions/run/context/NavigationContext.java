package com.quest.solutions.run.context;

import com.quest.solutions.run.db.entity.location.Location;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import java.util.List;

/**
 *
 * @author arle0814
 */
public class NavigationContext {
    
    private Location currentLocation;
    private List<Street> streetLocation;
    private List<Room> roomLocation;
    private List<Street> gameMap;

    public NavigationContext() {
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<Street> getStreetLocation() {
        return streetLocation;
    }

    public void setStreetLocation(List<Street> streetLocation) {
        this.streetLocation = streetLocation;
    }

    public List<Room> getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(List<Room> roomLocation) {
        this.roomLocation = roomLocation;
    }

    public List<Street> getGameMap() {
        return gameMap;
    }

    public void setGameMap(List<Street> gameMap) {
        this.gameMap = gameMap;
    }
    
    

}
