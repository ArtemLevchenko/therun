package com.quest.solutions.run.db.entity.events;

import java.io.Serializable;

/**
 *
 * @author arle0814
 * @param <T>
 */
public interface Event<T> extends Serializable{
    public Class<T> getType();
}
