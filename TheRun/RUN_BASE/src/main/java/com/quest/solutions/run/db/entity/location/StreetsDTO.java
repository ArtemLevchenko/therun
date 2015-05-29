package com.quest.solutions.run.db.entity.location;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arle0814
 */
@XmlRootElement(name = "streets")
@XmlAccessorType(XmlAccessType.FIELD)
public class StreetsDTO {
    
    @XmlElement(name = "street")
    private List<Street> streets;

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }
    
}
