package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.item.AmmoType;
import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.item.Weapon;
import com.quest.solutions.run.db.entity.location.Location;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author Artem
 */
public class HitEngine extends BaseEngine {

    private static final String WEAPON_FOUND = "WEAPON not found in Inventory!";
    private static final String MADE = "You've come to the monster";
    private static final String MISS = "You do not hit on the monster";

    private Player player;
    private Location currentLocation;

    public HitEngine(Player player, Location currentLocation, String otherText) {
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

    private void checkToHit(Weapon it) {
        if (currentLocation instanceof Street) {
            ((Street) currentLocation).getMonsters().get(0)
                    .setHealth(((Street) currentLocation).getMonsters()
                            .get(0).getHealth() - it.getDamage());

        } else {
            ((Room) currentLocation).getMonsters().get(0)
                    .setHealth(((Room) currentLocation).getMonsters()
                            .get(0).getHealth() - it.getDamage());

        }
    }

    private Monster isDeadMonster() {
        Monster monster = null;
        if (currentLocation instanceof Street) {
            if (((Street) currentLocation).getMonsters().get(0).getHealth() <= 0) {
                ((Street) currentLocation).getMonsters().remove(0);
            } else {
                monster = ((Street) currentLocation).getMonsters().get(0);
            }
        } else {
            if (((Room) currentLocation).getMonsters().get(0).getHealth() <= 0) {
                ((Room) currentLocation).getMonsters().remove(0);
            } else {
                monster = ((Room) currentLocation).getMonsters().get(0);
            }
        }
        return monster;
    }

    @Override
    public void run() {
        if (proxyText.getRequest() != null && !proxyText.getRequest().isEmpty()) {
            for (Item it : player.getItems()) {
                if (it instanceof Weapon) {
                    if (((Weapon) it).getAmmoType() == AmmoType.COLD_ARMS
                            && ((Weapon) it).getName().equalsIgnoreCase(proxyText.getRequest())) {
                        String result;
                        if (GameHelperEngine.chanseToTrue(((Weapon) it).getChanceOfHitting())) {
                            this.checkToHit(((Weapon) it));
                            result = MADE;
                        } else {
                            result = MISS;
                        }
                        this.proxyText.setResponse(result);
                        this.proxyText.setResponseEventType(ResponseEventType.WINDOW);
                        this.proxyText.setDescription(result);

                        Monster monsterAttack = this.isDeadMonster();
                        // come back batle from monster
                        if (monsterAttack != null) {
                            MonsterAttackEngine engineAttack = new MonsterAttackEngine(monsterAttack, player);
                            engineAttack.run();
                            this.proxyText.setResponse(this.proxyText.getResponse() + " " + engineAttack.getResponse());
                            this.proxyText.setDescription(this.proxyText.getDescription() + " " + engineAttack.getPopupMessage());
                            this.player = engineAttack.getPlayer();
                        }
                        return;
                    }
                }
            }
        }
        this.proxyText.setResponse(WEAPON_FOUND);
    }

}
