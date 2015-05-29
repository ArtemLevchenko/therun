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
@XmlRootElement(name = "iteminventories")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemInventoryDTO {
    @XmlElement(name = "iteminventory")
    private List<ItemInventory> itemInventories;

    public List<ItemInventory> getItemInventories() {
        return itemInventories;
    }

    public void setItemInventories(List<ItemInventory> itemInventories) {
        this.itemInventories = itemInventories;
    }
    
}
