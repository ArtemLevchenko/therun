package com.quest.solutions.run.logic.analyzer;

import com.quest.solutions.run.db.entity.events.ResponseEventType;

/**
 *
 * @author arle0814
 */
public class EventProxyListener {

    private String request;
    private String response;
    private String description;
    private ResponseEventType responseEventType;

    public EventProxyListener(String request, String response, String description, ResponseEventType responseEventType) {
        this.request = request;
        this.response = response;
        this.description = description;
        this.responseEventType = responseEventType;
    }

    public EventProxyListener() {
        this.request = "";
        this.response = "";
        this.description = "";
        this.responseEventType = ResponseEventType.RESULT;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResponseEventType getResponseEventType() {
        return responseEventType;
    }

    public void setResponseEventType(ResponseEventType responseEventType) {
        this.responseEventType = responseEventType;
    }

}
