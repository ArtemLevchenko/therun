package com.quest.solutions.run.db.entity.events;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arle0814
 */
@XmlRootElement(name = "globalEvents")
@XmlAccessorType(XmlAccessType.FIELD)
public class GlobalEventDTO {

    @XmlElement(name = "globalEvent")
    private List<GlobalEvent> globalEvents;

    public List<GlobalEvent> getGlobalEvents() {
        return globalEvents;
    }

    public void setGlobalEvents(List<GlobalEvent> globalEvents) {
        this.globalEvents = globalEvents;
    }
   

}
