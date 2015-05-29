package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author Artem
 */
public class MonsterAttackEngine extends BaseEngine {

    private static final String MONSTER_ATTACK = "Monster attacked for ";
    private static final String MONSTER_MISS = "Monster missed for ";
    private static final String POPUP = " lose a life from the monster damage";

    private Monster monster;
    private Player player;

    public MonsterAttackEngine(Monster monster, Player player) {
        this.monster = monster;
        this.player = player;
        this.proxyText = new EventProxyListener("", "", "", ResponseEventType.RESULT);
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (GameHelperEngine.chanseToTrue(monster.getChanceToHit())) { // monster attack
            player.setHealth(player.getHealth() - monster.getDamage());
            this.proxyText.setResponse(MONSTER_ATTACK + player.getName());
            this.proxyText.setResponseEventType(ResponseEventType.WINDOW);
            this.proxyText.setDescription(player.getName() + POPUP);
        } else {
            this.proxyText.setResponse(MONSTER_MISS + player.getName());
        }
    }

}
