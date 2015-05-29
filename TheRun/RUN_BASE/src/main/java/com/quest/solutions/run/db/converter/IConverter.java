package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.converter.GameLoaderSourceContext;

/**
 * Created by arle0814 on 29.01.2015.
 * @param <T>
 */
public interface IConverter<T> {
    public void loadData(GameLoaderSourceContext gameLoadContext);
}

