package com.quest.solutions.run.db.gen;

import com.quest.solutions.run.db.converter.GameLoaderSourceContext;
import com.quest.solutions.run.db.converter.IConverter;
import com.quest.solutions.run.db.entity.events.Event;
import com.quest.solutions.run.db.entity.events.GlobalEvent;
import com.quest.solutions.run.db.entity.events.TypeEvent;
import com.quest.solutions.run.db.entity.item.Ammunition;
import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.item.ItemInventory;
import com.quest.solutions.run.db.entity.item.Medicine;
import com.quest.solutions.run.db.entity.item.Weapon;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.context.NavigationContext;
import com.quest.solutions.run.logic.engine.GameHelperEngine;
import com.quest.solutions.run.logic.random.GameGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by arle0814 on 09.03.2015.
 */
public class GameWorldGenerator {

    private List<IConverter> generatedWorld;

    private GameLoaderSourceContext loaderSourceContext;

    public GameLoaderSourceContext getLoaderSourceContext() {
        return loaderSourceContext;
    }

    @Required
    public void setLoaderSourceContext(GameLoaderSourceContext loaderSourceContext) {
        this.loaderSourceContext = loaderSourceContext;
    }

    public List<IConverter> getGeneratedWorld() {
        return generatedWorld;
    }

    @Required
    public void setGeneratedWorld(List<IConverter> generatedWorld) {
        this.generatedWorld = generatedWorld;
    }

    public void execute() {
        // load all source
        for (IConverter converter : this.getGeneratedWorld()) {
            converter.loadData(loaderSourceContext);
        }
        // generate map and items
        sortRandomComponents();
        createComponents();
    }

    private void sortRandomComponents() {
        // random sort
        Collections.shuffle(loaderSourceContext.getStreets(), new Random(System.nanoTime()));
        Collections.shuffle(loaderSourceContext.getRooms(), new Random(System.nanoTime()));
        Collections.shuffle(loaderSourceContext.getKeys(), new Random(System.nanoTime()));
        Collections.shuffle(loaderSourceContext.getItemInventory(), new Random(System.nanoTime()));
        Collections.shuffle(loaderSourceContext.getWeapons(), new Random(System.nanoTime()));
        Collections.shuffle(loaderSourceContext.getMedicines(), new Random(System.nanoTime()));
        Collections.shuffle(loaderSourceContext.getAmunitions(), new Random(System.nanoTime()));
        Collections.shuffle(loaderSourceContext.getMonsters(), new Random(System.nanoTime()));
        Collections.shuffle(loaderSourceContext.getEvents(), new Random(System.nanoTime()));
    }

    private void setItem(Item item) {
        int numberLocation = GameGenerator.randomIntegerIndex(0, loaderSourceContext.getStreets().size() - 1);

        if (!GameHelperEngine.chanseToTrue(50)) { // if street
            loaderSourceContext.getStreets().get(numberLocation).getItems().add(item);
            return;
        }
        int sizeRoom = loaderSourceContext.getStreets().get(numberLocation).getRelatedRooms().size();
        int numberRoom = GameGenerator.randomIntegerIndex(0, sizeRoom - 1);
        // if room have childs
        if (loaderSourceContext.getStreets().get(numberLocation).
                getRelatedRooms().get(numberRoom).getChildRooms() != null) {
            // 1 - parent, 0 - child
            int chOrParentRoom = GameGenerator.randomIntegerIndex(0, 1);
            // if child
            if (chOrParentRoom == 0) {
                int chRoom = loaderSourceContext.getStreets().get(numberLocation).getRelatedRooms().
                        get(numberRoom).getChildRooms().size();
                chRoom = GameGenerator.randomIntegerIndex(0, chRoom - 1);
                loaderSourceContext.getStreets().get(numberLocation).getRelatedRooms().
                        get(numberRoom).getChildRooms().get(chRoom).getItems().add(item);
            } else {
                loaderSourceContext.getStreets().get(numberLocation).getRelatedRooms().
                        get(numberRoom).getItems().add(item);
            }
        } else {
            loaderSourceContext.getStreets().get(numberLocation).getRelatedRooms().
                    get(numberRoom).getItems().add(item);
        }
    }

    private void setItemInventoryToRoom() {
        // ItemInventory GENERATOR
        for (ItemInventory itemInventory : loaderSourceContext.getItemInventory()) {
            this.setItem(itemInventory);
        }
    }

    private void setMedicineToRoom() {
        // Medicine GENERATOR
        for (Medicine medicine : loaderSourceContext.getMedicines()) {
            this.setItem(medicine);
        }
    }

    private void setAmmoToRoom() {
        // Ammo GENERATOR
        for (Ammunition ammo : loaderSourceContext.getAmunitions()) {
            this.setItem(ammo);
        }
    }

    // FOR FIRST LEVEL ROOMS RANDOM
    private void setWeaponsToRoom() {
        // WEAPON GENERATOR
        for (Weapon weapon : loaderSourceContext.getWeapons()) {
            int numberLocation = GameGenerator.randomIntegerIndex(0, loaderSourceContext.getStreets().size() - 1);
            int sizeRoom = loaderSourceContext.getStreets().get(numberLocation).getRelatedRooms().size();
            int numberRoom = GameGenerator.randomIntegerIndex(0, sizeRoom - 1);
            loaderSourceContext.getStreets().get(numberLocation).getRelatedRooms().get(numberRoom).getItems().add(weapon);
        }
    }

    private void createNavigationContext() {
        NavigationContext navigationContext = new NavigationContext();
        Street street = loaderSourceContext.getStreets().get(0);
        navigationContext.setGameMap(loaderSourceContext.getStreets());
        navigationContext.setCurrentLocation(street);
        navigationContext.setStreetLocation(loaderSourceContext.getStreets());
        navigationContext.setRoomLocation(street.getRelatedRooms());
        loaderSourceContext.setNavigationContext(navigationContext);
    }

    private void update(List<GlobalEvent> loadPlayerEvents) {
        for (GlobalEvent gl : loadPlayerEvents) {
            if (gl.getWeapons() != null && !gl.getWeapons().isEmpty()) {
                loaderSourceContext.getPlayer().getItems().addAll(gl.getWeapons());
            }
            if (gl.getAmmunitions() != null && !gl.getAmmunitions().isEmpty()) {
                loaderSourceContext.getPlayer().getItems().addAll(gl.getAmmunitions());
            }
            if (gl.getMedicines() != null && !gl.getMedicines().isEmpty()) {
                loaderSourceContext.getPlayer().getItems().addAll(gl.getMedicines());
            }
            if (gl.getKeys() != null && !gl.getKeys().isEmpty()) {
                loaderSourceContext.getPlayer().getItems().addAll(gl.getKeys());
            }
            if (gl.getItemInvetories() != null && !gl.getItemInvetories().isEmpty()) {
                loaderSourceContext.getPlayer().getItems().addAll(gl.getItemInvetories());
            }
            // ADDED MONSTERS
            if (gl.getMonsters() != null && !gl.getMonsters().isEmpty()) {
                if (loaderSourceContext.getNavigationContext().getCurrentLocation() instanceof Room) {
                    ((Room) loaderSourceContext.getNavigationContext().getCurrentLocation()).setMonsters(gl.getMonsters());
                } else {
                    ((Street) loaderSourceContext.getNavigationContext().getCurrentLocation()).setMonsters(gl.getMonsters());
                }
            }
            if (gl.getPlayers() != null && !gl.getPlayers().isEmpty()) {
                // ADDED NPC
                if (loaderSourceContext.getNavigationContext().getCurrentLocation() instanceof Room) {
                    ((Room) loaderSourceContext.getNavigationContext().getCurrentLocation()).setNpc(gl.getPlayers());
                } else {
                    ((Street) loaderSourceContext.getNavigationContext().getCurrentLocation()).setNpc(gl.getPlayers());
                }
            }
        }
    }

    private void createInventoryAndLocation() {
        List<GlobalEvent> loadPlayerEvents = null;
        for (Event event : loaderSourceContext.getEvents()) {
            if (TypeEvent.LOAD == ((GlobalEvent) event).getTypeEvent()) {
                if (loadPlayerEvents == null) {
                    loadPlayerEvents = new ArrayList<>();
                }
                loadPlayerEvents.add(((GlobalEvent) event));

            }
        }
        //loaderSourceContext.getEvents().removeAll(loadPlayerEvents);

        if (loadPlayerEvents != null && !loadPlayerEvents.isEmpty()) {
            this.update(loadPlayerEvents);
        }
    }

    private void createComponents() {
        // for size street add room
        for (Street street : loaderSourceContext.getStreets()) {
            List<Room> lineRooms = new ArrayList<>();
            for (int i = 0; i < street.getSize(); i++) {
                lineRooms.add(loaderSourceContext.getRooms().get(0));
                loaderSourceContext.getRooms().remove(0);
            }
            // KEYS GENERATOR FOR FIRST ROOM STEP BY STEP
            if (!loaderSourceContext.getKeys().isEmpty()) {
                lineRooms.get(0).getItems().add(loaderSourceContext.getKeys().get(0));
                loaderSourceContext.getKeys().remove(0);
            }
            //
            street.setRelatedRooms(lineRooms);
        }
        // WEAPON GENERATOR
        setWeaponsToRoom();
        // MEDICINE GENERATOR
        setMedicineToRoom();
        // Ammo GENERATOR
        setAmmoToRoom();
        // Item Invetory GENERATOR
        setItemInventoryToRoom();
        // Create Navigation Context
        createNavigationContext();
        // CREATE Start inventory Player and Location
        createInventoryAndLocation();
    }

}
