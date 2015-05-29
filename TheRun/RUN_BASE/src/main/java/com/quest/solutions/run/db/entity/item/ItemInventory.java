package com.quest.solutions.run.db.entity.item;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arle0814
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemInventory implements Item {

    @XmlElement
    private String id;
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private String image;
    @XmlElement
    private ItemInventoryType itemInventoryType;
    @XmlElement
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ItemInventoryType getItemInventoryType() {
        return itemInventoryType;
    }

    public void setItemInventoryType(ItemInventoryType itemInventoryType) {
        this.itemInventoryType = itemInventoryType;
    }

    @Override
    public Class<ItemInventory> getType() {
        return ItemInventory.class;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    

    @Override
    public String toString() {
        return name;
    }

}
