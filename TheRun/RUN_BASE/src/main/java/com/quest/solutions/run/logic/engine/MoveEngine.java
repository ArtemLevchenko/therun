package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.context.NavigationContext;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.Event;
import com.quest.solutions.run.db.entity.events.GlobalEvent;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.location.Location;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arle0814
 */
public class MoveEngine extends BaseEngine {

    private static final String LOCATION_NOT_FOUND = "Location not found!";
    private static final String LOCATION_LOCKED = "Location is locked!";
    private static final String GO_STREET = "We goes to Street!";
    private static final String GO_ROOM = "We goes to Room!";
    private static final String WARNING_BATTLE = "In this location has monster. SHOOT/HIT or ELOPE";
    private static final String MENTAL_MESSAGE = "When switching to another location, you were frightened and lost one willpower";

    private NavigationContext gameNavigation;
    private List<Event> events;
    private boolean escapePhase;
    private Player player;

    public MoveEngine(Player player, NavigationContext gameNavigation, List<Event> events, String otherText) {
        this.player = player;
        this.gameNavigation = gameNavigation;
        this.events = events;
        this.proxyText = new EventProxyListener(otherText, "", "", ResponseEventType.RESULT);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public NavigationContext getGameNavigation() {
        return gameNavigation;
    }

    public void setGameNavigation(NavigationContext gameNavigation) {
        this.gameNavigation = gameNavigation;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public boolean isEscapePhase() {
        return escapePhase;
    }

    public void setEscapePhase(boolean escapePhase) {
        this.escapePhase = escapePhase;
    }

    private Location findNextLocation() {
        Location location = null;
        if (proxyText.getRequest() != null && !proxyText.getRequest().isEmpty()) {
            for (Room room : gameNavigation.getRoomLocation()) {
                if (room.getName().equalsIgnoreCase(proxyText.getRequest())) {
                    location = room;
                    break;
                }
            }
            // if not found check location
            if (location == null) {
                for (Street street : gameNavigation.getStreetLocation()) {
                    if (street.getName().equalsIgnoreCase(proxyText.getRequest())) {
                        location = street;
                        break;
                    }
                }
            }
        }
        return location;
    }

    private Room findParentRoom(Room room) {
        List<Street> streets = gameNavigation.getGameMap();
        for (Street street : streets) {
            List<Room> rooms = street.getRelatedRooms();
            for (Room parent : rooms) {
                // one level
                if (room.getId().equalsIgnoreCase(parent.getId())) {
                    return parent;
                }
                List<Room> childRoom = parent.getChildRooms();
                if (childRoom != null && !childRoom.isEmpty()) {
                    for (Room ch : childRoom) {
                        if (ch.getId().equalsIgnoreCase(room.getId())) {
                            return parent;
                        }
                    }
                }

            }
        }
        return null;
    }

    private boolean checkMonsterInRoom() {
        if (!isEscapePhase()) { //if not ELOPE Phase
            if (gameNavigation.getCurrentLocation() instanceof Street) {    //if  monster in location
                if ((((Street) gameNavigation.getCurrentLocation()).getMonsters() != null
                        && !((Street) gameNavigation.getCurrentLocation()).getMonsters().isEmpty())) {
                    proxyText.setResponse(WARNING_BATTLE);
                    return true;
                }
            } else {
                if (((Room) gameNavigation.getCurrentLocation()).getMonsters() != null
                        && !((Room) gameNavigation.getCurrentLocation()).getMonsters().isEmpty()) {
                    proxyText.setResponse(WARNING_BATTLE);
                    return true;
                }
            }
        }
        return false;
    }

    private void setNextStreet(Location location) {
        gameNavigation.setCurrentLocation(location);
        gameNavigation.setRoomLocation(((Street) location).getRelatedRooms());
        gameNavigation.setStreetLocation(gameNavigation.getStreetLocation());
        proxyText.setResponse(GO_STREET);
        // ADDED EVENT FOR NAVIGATIONS
        if (events != null && !events.isEmpty()) {
            this.setEventsToLocation();
        }

    }

    private void setNextRoom(Location location) {
        Room nextRoom = ((Room) location);
        //if NOT monster in location
        if (nextRoom.isOpen()) { //if open location
            gameNavigation.setCurrentLocation(nextRoom);
            // IF FIRST LEVEL
            if (nextRoom.getChildRooms() != null && !nextRoom.getChildRooms().isEmpty()) {
                gameNavigation.setRoomLocation(nextRoom.getChildRooms());
                gameNavigation.setStreetLocation(gameNavigation.getGameMap());
                if (events != null && !events.isEmpty()) {
                    this.setEventsToLocation();
                }
            } else { // IF SECOND LEVEL or ONE LEVEL LOCATION
                gameNavigation.setStreetLocation(new ArrayList<Street>());
                Room parent = this.findParentRoom(nextRoom);
                if (parent != null) {
                    // if location have one level
                    if (parent.getId().equalsIgnoreCase(nextRoom.getId())) {
                        gameNavigation.setStreetLocation(gameNavigation.getGameMap());
                        gameNavigation.setRoomLocation(new ArrayList<Room>());
                    } else {
                        List<Room> listToNavigateRoom = new ArrayList<>();
                        listToNavigateRoom.add(parent);
                        gameNavigation.setRoomLocation(listToNavigateRoom);
                    }
                    proxyText.setResponse(GO_ROOM);
                    // ADDED EVENT FOR NAVIGATIONS
                    if (events != null && !events.isEmpty()) {
                        this.setEventsToLocation();
                    }
                } else {
                    proxyText.setResponse(LOCATION_NOT_FOUND);
                }
            }

        } else {
            proxyText.setResponse(LOCATION_LOCKED);
        }
    }

    @Override
    public void run() {
        Location location = this.findNextLocation();
        if (location != null && !this.checkMonsterInRoom()) { // if find
            if (location instanceof Street) {
                this.setNextStreet(location);
            } else if (location instanceof Room) { // room
                this.setNextRoom(location);
            }
            // check mental state
            if (this.checkMonsterInRoom()) {
                this.updateMentalState();
            }
        } else {
            if (!this.checkMonsterInRoom()) {
                proxyText.setResponse(LOCATION_NOT_FOUND);
            }
        }
    }

    private void updateMentalState() {
        if (GameHelperEngine.chanseToTrue(30)) {
            player.setMentalState(player.getMentalState() - 1);
            proxyText.setResponse(MENTAL_MESSAGE);
            proxyText.setDescription(MENTAL_MESSAGE);
            proxyText.setResponseEventType(ResponseEventType.WINDOW);
        }
    }

    private List<Item> collectAllItemToLocation(GlobalEvent gl) {
        List<Item> items = new ArrayList<>();
        if (gl.getWeapons() != null && !gl.getWeapons().isEmpty()) {
            items.addAll(gl.getWeapons());
        }
        if (gl.getMedicines() != null && !gl.getMedicines().isEmpty()) {
            items.addAll(gl.getMedicines());
        }
        if (gl.getItemInvetories() != null && !gl.getItemInvetories().isEmpty()) {
            items.addAll(gl.getItemInvetories());
        }
        if (gl.getKeys() != null && !gl.getKeys().isEmpty()) {
            items.addAll(gl.getKeys());
        }
        if (gl.getAmmunitions() != null && !gl.getAmmunitions().isEmpty()) {
            items.addAll(gl.getAmmunitions());
        }
        return items;
    }

    private void setEventsToLocation() {
        EventEngine monsterGeneration = new EventEngine(events);
        monsterGeneration.run();
        if (monsterGeneration.getEventToExecute() != null) {
            GlobalEvent gl = (GlobalEvent) monsterGeneration.getEventToExecute();
            if (gameNavigation.getCurrentLocation() instanceof Street) {
                if (gl.getMonsters() != null && !gl.getMonsters().isEmpty()) {
                    ((Street) gameNavigation.getCurrentLocation()).getMonsters().addAll(gl.getMonsters());
                }
                if (gl.getPlayers() != null && !gl.getPlayers().isEmpty()) {
                    ((Street) gameNavigation.getCurrentLocation()).getNpc().addAll(gl.getPlayers());
                }
                List<Item> resultItems = this.collectAllItemToLocation(gl);
                if (resultItems != null && !resultItems.isEmpty()) {
                    ((Street) gameNavigation.getCurrentLocation()).getItems().addAll(resultItems);
                }
            } else if (gameNavigation.getCurrentLocation() instanceof Room) { // room
                if (gl.getMonsters() != null && !gl.getMonsters().isEmpty()) {
                    ((Room) gameNavigation.getCurrentLocation()).getMonsters().addAll(gl.getMonsters());
                }
                if (gl.getPlayers() != null && !gl.getPlayers().isEmpty()) {
                    ((Room) gameNavigation.getCurrentLocation()).getNpc().addAll(gl.getPlayers());
                }
                List<Item> resultItems = this.collectAllItemToLocation(gl);
                if (resultItems != null && !resultItems.isEmpty()) {
                    ((Room) gameNavigation.getCurrentLocation()).getItems().addAll(resultItems);
                }
            }
            events.remove(gl);
        }
    }

}
