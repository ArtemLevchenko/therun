package com.quest.solutions.run.db.entity.location;

import java.io.Serializable;

/**
 *
 * @author arle0814
 * @param <T>
 */
public interface Location<T> extends Serializable {
    public Class<T> getType();
}
