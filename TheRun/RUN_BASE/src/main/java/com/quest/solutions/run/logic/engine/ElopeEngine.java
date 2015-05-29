package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.context.NavigationContext;
import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author Artem
 */
public class ElopeEngine extends BaseEngine {

    private Player player;
    private NavigationContext gameNavigation;

    public ElopeEngine(Player player, NavigationContext gameNavigation, String otherText) {
        this.player = player;
        this.gameNavigation = gameNavigation;
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

    private Monster findMonsterInLocation() {
        Monster monster = null;
        if (gameNavigation.getCurrentLocation() instanceof Street) {
            if (!((Street) gameNavigation.getCurrentLocation()).getMonsters().isEmpty()) {
                monster = ((Street) gameNavigation.getCurrentLocation()).getMonsters().get(0);
            }
        } else {
            if (!((Room) gameNavigation.getCurrentLocation()).getMonsters().isEmpty()) {
                monster = ((Room) gameNavigation.getCurrentLocation()).getMonsters().get(0);
            }
        }
        return monster;
    }

    private void generateNextLocation() {
        MoveEngine move = new MoveEngine(player, gameNavigation, null, proxyText.getRequest());
        move.setEscapePhase(true);
        move.run();
        this.gameNavigation = move.getGameNavigation();
        this.proxyText = move.getProxyText();
    }

    private void monsterHasAttack(Monster monster) {
        MonsterAttackEngine monsterAttack = new MonsterAttackEngine(monster, player);
        monsterAttack.run();
        this.player = monsterAttack.getPlayer();
        this.proxyText = monsterAttack.getProxyText();
    }

    @Override
    public void run() {
        Monster monster = this.findMonsterInLocation();
        if (GameHelperEngine.chanseToTrue(monster == null
                ? 100 : monster.getChanceToEscape())) { // escape complete
            this.generateNextLocation();
        } else { // monster attack
            this.monsterHasAttack(monster);
        }
    }

}
