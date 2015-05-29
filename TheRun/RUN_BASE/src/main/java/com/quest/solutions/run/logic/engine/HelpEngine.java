package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.location.Location;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author Artem
 */
public class HelpEngine extends BaseEngine {

    private static final String MONSTER_IS_IN_LOCATION = "NPC not found or Monster was in location!";
    private static final String NPC_NOT_FOUND = "NPC not found in Location!";
    private static final String HELP_PLAYER = "We saved: ";

    private Player player;
    private Location currentLocation;

    public HelpEngine(Player player, Location currentLocation, String otherText) {
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

    private void setFindCriteria(Player pl) {
        player.getHelpPlayers().add(pl);
        proxyText.setResponseEventType(ResponseEventType.WINDOW);
        proxyText.setDescription(HELP_PLAYER + pl.getName());
        proxyText.setResponse(HELP_PLAYER + pl.getName());
    }

    private void findNPCtoSaved() {
        if (proxyText.getRequest() != null && !proxyText.getRequest().isEmpty()) {
            if (this.currentLocation instanceof Street) {
                if (((Street) this.currentLocation).getMonsters().isEmpty()) {
                    for (Player pl : ((Street) this.currentLocation).getNpc()) {
                        if (pl.isIsNPC() && pl.getName().equalsIgnoreCase(proxyText.getRequest())) {
                            this.setFindCriteria(pl);
                            ((Street) this.currentLocation).getNpc().remove(pl);
                            return;
                        }
                    }
                }
            } else { // room
                if (((Room) this.currentLocation).getMonsters().isEmpty()) {
                    for (Player pl : ((Room) this.currentLocation).getNpc()) {
                        if (pl.isIsNPC() && pl.getName().equalsIgnoreCase(proxyText.getRequest())) {
                            this.setFindCriteria(pl);
                            ((Room) this.currentLocation).getNpc().remove(pl);
                            return;
                        }
                    }
                }
            }
            proxyText.setResponse(MONSTER_IS_IN_LOCATION);
        } else {
            proxyText.setResponse(NPC_NOT_FOUND);
        }

    }

    @Override
    public void run() {
        this.findNPCtoSaved();
    }

}
