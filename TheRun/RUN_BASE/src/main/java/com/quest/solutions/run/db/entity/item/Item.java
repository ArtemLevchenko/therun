package com.quest.solutions.run.db.entity.item;

import java.io.Serializable;

/**
 *
 * @author arle0814
 * @param <T>
 */
public interface Item<T> extends Serializable{
    public Class<T> getType();
}
