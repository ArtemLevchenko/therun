package com.quest.solutions.run.db.entity.location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by arle0814 on 29.01.2015.
 */
@XmlRootElement(name = "rooms")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomsDTO {
    @XmlElement(name = "room")
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}