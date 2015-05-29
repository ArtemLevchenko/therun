package com.quest.solutions.run.logic.analyzer;

import com.quest.solutions.run.db.entity.events.ResponseEventType;

/**
 *
 * @author arle0814
 */
public class ResultProxyText {

    private String resultMessage;
    private ResponseEventType typeEvent;
    private String textPopup;

    public ResultProxyText(String resultMessage, ResponseEventType typeEvent, String textPopup) {
        this.resultMessage = resultMessage;
        this.typeEvent = typeEvent;
        this.textPopup = textPopup;
    }

    public ResultProxyText() {
        this.resultMessage = "";
        this.typeEvent = ResponseEventType.RESULT;
        this.textPopup = "";
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public ResponseEventType getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(ResponseEventType typeEvent) {
        this.typeEvent = typeEvent;
    }

    public String getTextPopup() {
        return textPopup;
    }

    public void setTextPopup(String textPopup) {
        this.textPopup = textPopup;
    }
    
    

}
