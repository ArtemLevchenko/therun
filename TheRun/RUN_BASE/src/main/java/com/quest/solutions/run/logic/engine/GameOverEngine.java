package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author Artem
 */
public class GameOverEngine extends BaseEngine {
    
    private final static String GAME_OVER_HEALTH = "You're died! Game over!";
    private final static String GAME_OVER_MENTAL = "You got cranky! Game over!";

    private Player player;
    private boolean hasGameOver;

    public GameOverEngine(Player player) {
        this.player = player;
        this.proxyText = new EventProxyListener("", "", "", ResponseEventType.WINDOW);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isHasGameOver() {
        return hasGameOver;
    }

    public void setHasGameOver(boolean hasGameOver) {
        this.hasGameOver = hasGameOver;
    }

    @Override
    public void run() {
        if (player.getHealth() <= 0) {
            hasGameOver = true;
            this.proxyText.setDescription(GAME_OVER_HEALTH);
            this.proxyText.setResponse(GAME_OVER_HEALTH);
        } else if (player.getMentalState() <= 0) {
            hasGameOver = true;
            this.proxyText.setDescription(GAME_OVER_MENTAL);
            this.proxyText.setResponse(GAME_OVER_MENTAL);
        }
    }

}
