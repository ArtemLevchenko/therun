package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;

/**
 *
 * @author Artem
 */
public abstract class BaseEngine implements Engine {

    protected EventProxyListener proxyText;

    public EventProxyListener getProxyText() {
        return proxyText;
    }

    public void setProxyText(EventProxyListener proxyText) {
        this.proxyText = proxyText;
    }

    @Override
    public ResponseEventType getResponceType() {
        return proxyText.getResponseEventType();
    }

    @Override
    public String getResponse() {
        return proxyText.getResponse();
    }

    @Override
    public String getPopupMessage() {
        return proxyText.getDescription();
    }
}
