package com.quest.solution.ui.action;

import com.quest.solutions.run.context.SpringApplicationContext;
import com.quest.solutions.run.logic.analyzer.FrontGameController;

/**
 *
 * @author arle0814
 */
public class StartGame {

    private static final String GLOBAL_CONTEXT_NAME = "frontGameController";

    public FrontGameController execute() {
        return (FrontGameController) SpringApplicationContext.getInstance().getBean(GLOBAL_CONTEXT_NAME);
    }
}
