package com.quest.solutions.run.logic.analyzer;

import com.quest.solutions.run.context.GameContext;
import com.quest.solutions.run.logic.engine.ElopeEngine;
import com.quest.solutions.run.logic.engine.Engine;
import com.quest.solutions.run.logic.engine.GameOverEngine;
import com.quest.solutions.run.logic.engine.HelpEngine;
import com.quest.solutions.run.logic.engine.HitEngine;
import com.quest.solutions.run.logic.engine.MoveEngine;
import com.quest.solutions.run.logic.engine.ReadEngine;
import com.quest.solutions.run.logic.engine.ShootEngine;
import com.quest.solutions.run.logic.engine.TakeEngine;
import com.quest.solutions.run.logic.engine.ThrowEngine;
import com.quest.solutions.run.logic.engine.UseEngine;

/**
 *
 * @author arle0814
 */
public class FrontGameController implements Command {

    private final static String NOT_FOUND_COMMAND = "Command not found!";
    private final static String INCORECT_COMMAND = "Incorrect command!";

    private GameContext gameContext;
    private ResultProxyText proxyText;
    private GameOverEngine gameOver;

    public FrontGameController(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public FrontGameController() {
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public ResultProxyText getProxyText() {
        return proxyText;
    }

    public void setProxyText(ResultProxyText proxyText) {
        this.proxyText = proxyText;
    }

    public GameOverEngine getGameOver() {
        if (gameOver == null) {
            gameOver = new GameOverEngine(gameContext.getPlayer());
        }
        return gameOver;
    }

    public void setGameOver(GameOverEngine gameOver) {
        this.gameOver = gameOver;
    }

    public void isGameOver() {
        this.getGameOver().run();
        if (this.getGameOver().isHasGameOver()) {
            proxyText.setResultMessage(this.getGameOver().getResponse());
            proxyText.setTypeEvent(this.getGameOver().getResponceType());
            proxyText.setTextPopup(this.getGameOver().getPopupMessage());
        }
    }

    private String getFirstCommand(String command) {
        String firtCommand = null;
        for (GameCommand gc : GameCommand.values()) {
            if (command.toUpperCase().startsWith(gc.name())) {
                firtCommand = gc.name();
                break;
            }
        }
        return firtCommand;
    }

    @Override
    public void execute(String command) {
        this.proxyText = new ResultProxyText();
        String firstCommand = this.getFirstCommand(command);

        if (firstCommand == null) {
            proxyText.setResultMessage(NOT_FOUND_COMMAND);
            return;
        }

        String otherTextBox = command.replaceAll(firstCommand, "");

        if (otherTextBox != null) {

            Engine engine = null;

            switch (GameCommand.valueOf(firstCommand)) {
                case GO:
                    engine = new MoveEngine(gameContext.getPlayer(), gameContext.getGameNavigation(), gameContext.getGameEvents(), otherTextBox.trim());
                    break;
                case TAKE:
                    engine = new TakeEngine(gameContext.getPlayer(), gameContext.getGameNavigation().getCurrentLocation(), otherTextBox.trim());
                    break;
                case THROW:
                    engine = new ThrowEngine(gameContext.getPlayer(), gameContext.getGameNavigation().getCurrentLocation(), otherTextBox.trim());
                    break;
                case READ:
                    engine = new ReadEngine(gameContext.getPlayer(), otherTextBox.trim());
                    break;
                case HELP:
                    engine = new HelpEngine(gameContext.getPlayer(), gameContext.getGameNavigation().getCurrentLocation(), otherTextBox.trim());
                    break;
                case HIT:
                    engine = new HitEngine(gameContext.getPlayer(), gameContext.getGameNavigation().getCurrentLocation(), otherTextBox.trim());
                    break;
                case SHOOT:
                    engine = new ShootEngine(gameContext.getPlayer(), gameContext.getGameNavigation().getCurrentLocation(), otherTextBox.trim());
                    break;
                case ELOPE:
                    engine = new ElopeEngine(gameContext.getPlayer(), gameContext.getGameNavigation(), otherTextBox.trim());
                    break;
                case USE:
                    engine = new UseEngine(gameContext.getPlayer(), gameContext.getGameNavigation().getCurrentLocation(), otherTextBox.trim());
                    break;
                default:
                    proxyText.setResultMessage(INCORECT_COMMAND);
                    break;
            }

            if (engine != null) {
                engine.run();
                proxyText.setResultMessage(engine.getResponse());
                proxyText.setTypeEvent(engine.getResponceType());
                proxyText.setTextPopup(engine.getPopupMessage());
                gameContext.setTurn(gameContext.getTurn() + 1);
            }
        }

    }

}
