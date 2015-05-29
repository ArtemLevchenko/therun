package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.item.AmmoType;
import com.quest.solutions.run.db.entity.item.Ammunition;
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
public class ShootEngine extends BaseEngine {

    private static final String WEAPON_NOT_FOUND = "WEAPON not found in Inventory!";
    private static final String AMMO_NOT_FOUND = "Ammunition not found in Inventory!";
    private static final String MADE = "You've come to the monster";
    private static final String MISS = "You do not hit on the monster";

    private Player player;
    private Location currentLocation;

    public ShootEngine(Player player, Location currentLocation, String otherText) {
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

    private Weapon getActiveWeapon() {
        Weapon weapon = null;
        for (Item it : player.getItems()) {
            if (it instanceof Weapon) {
                if (((Weapon) it).getName().equalsIgnoreCase(proxyText.getRequest())) {
                    weapon = ((Weapon) it);
                    break;
                }
            }
        }
        return weapon;
    }

    private Ammunition getActiveAmmo(Weapon weapon) {
        Ammunition ammo = null;
        for (Item it : player.getItems()) {
            if (it instanceof Ammunition) {
                if (weapon.getAmmoType() == ((Ammunition) it).getAmmoType()) {
                    ammo = ((Ammunition) it);
                    break;
                }
            }
        }
        return ammo;
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
            Weapon weapon = this.getActiveWeapon();
            if (weapon != null) {
                Ammunition ammo = this.getActiveAmmo(weapon);
                if (ammo != null) {
                    String result;
                    if (GameHelperEngine.chanseToTrue(weapon.getChanceOfHitting())) {
                        this.checkToHit(weapon);
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
                    // remove ammo
                    player.getItems().remove(ammo);
                    return;
                }
                this.proxyText.setResponse(AMMO_NOT_FOUND);
                return;
            }
        }
        this.proxyText.setResponse(WEAPON_NOT_FOUND);
    }

}
