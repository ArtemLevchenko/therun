package com.quest.solutions.run.context;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.Event;
import com.quest.solutions.run.db.entity.events.GlobalEvent;
import com.quest.solutions.run.db.entity.events.TypeEvent;
import com.quest.solutions.run.db.gen.GameWorldGenerator;
import java.util.List;

/**
 *
 * @author arle0814
 */
public class GameContext {

    private int turn;

    private Player player;
    private List<Monster> monsters;
    private List<Event> gameEvents;
    private NavigationContext gameNavigation;

    public GameContext(GameWorldGenerator worldGenerator) {
        worldGenerator.execute();
        this.player = worldGenerator.getLoaderSourceContext().getPlayer();
        this.monsters = worldGenerator.getLoaderSourceContext().getMonsters();
        this.gameEvents = worldGenerator.getLoaderSourceContext().getEvents();
        this.gameNavigation = worldGenerator.getLoaderSourceContext().getNavigationContext();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Event> getGameEvents() {
        return gameEvents;
    }

    public void setGameEvents(List<Event> gameEvents) {
        this.gameEvents = gameEvents;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public NavigationContext getGameNavigation() {
        return gameNavigation;
    }

    public void setGameNavigation(NavigationContext gameNavigation) {
        this.gameNavigation = gameNavigation;
    }

    public String getLoadEventDescription() {
        GlobalEvent resultEvent = null;
        String result = "";
        for (Event event : gameEvents) {
            if (TypeEvent.LOAD == ((GlobalEvent) event).getTypeEvent()) {
                resultEvent = ((GlobalEvent) event);
                result = resultEvent.getDescription();
            }
        }
        if (resultEvent != null) {
            gameEvents.remove(resultEvent);
        }
        return result;
    }

}
