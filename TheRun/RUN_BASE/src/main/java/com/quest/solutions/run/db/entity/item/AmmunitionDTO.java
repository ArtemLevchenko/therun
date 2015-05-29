package com.quest.solutions.run.db.entity.item;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arle0814
 */
@XmlRootElement(name = "ammunitions")
@XmlAccessorType(XmlAccessType.FIELD)
public class AmmunitionDTO {

    @XmlElement(name = "ammunition")
    private List<Ammunition> ammunitions;

    public List<Ammunition> getAmmunitions() {
        return ammunitions;
    }

    public void setAmmunitions(List<Ammunition> ammunitions) {
        this.ammunitions = ammunitions;
    }
    
}
