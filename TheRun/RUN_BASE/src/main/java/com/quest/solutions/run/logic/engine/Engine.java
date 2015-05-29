package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.events.ResponseEventType;

/**
 *
 * @author arle0814
 */
public interface Engine {

    public void run();

    public ResponseEventType getResponceType();

    public String getResponse();

    public String getPopupMessage();
}
