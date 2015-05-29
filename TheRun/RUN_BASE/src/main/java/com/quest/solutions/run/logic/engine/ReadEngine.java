package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.characters.Player;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.item.Item;
import com.quest.solutions.run.db.entity.item.ItemInventory;
import com.quest.solutions.run.db.entity.item.ItemInventoryType;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author arle0814
 */
public class ReadEngine extends BaseEngine {

    private Player player;
    private static final String NO_READ = "I don't read this!";
    private static final String YES_READ = "I'm reading";

    public ReadEngine(Player player, String otherText) {
        this.proxyText = new EventProxyListener(otherText, "", "", ResponseEventType.WINDOW);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (proxyText.getRequest() != null && !proxyText.getRequest().isEmpty()) {
            for (Item it : player.getItems()) {
                if (it instanceof ItemInventory
                        && ((ItemInventory) it).getItemInventoryType() == ItemInventoryType.DOCUMENT) {
                    proxyText.setDescription(((ItemInventory) it).getText());
                    if (!player.getDocuments().contains(((ItemInventory) it))) {
                        player.getDocuments().add(((ItemInventory) it));
                    }
                    proxyText.setResponse(YES_READ);
                    return;
                }
            }
        }
        proxyText.setResponse(NO_READ);
    }

    @Override
    public ResponseEventType getResponceType() {
        if (proxyText.getDescription() != null && !proxyText.getDescription().isEmpty()) {
            return proxyText.getResponseEventType();
        }
        proxyText.setResponseEventType(ResponseEventType.RESULT);
        return proxyText.getResponseEventType();
    }

}
