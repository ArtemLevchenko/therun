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
public class Medicine implements Item {

    @XmlElement
    private String id;
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private String image;
    @XmlElement
    private int healing;
    @XmlElement
    private MedicineType medicineType;

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

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    @Override
    public Class<Medicine> getType() {
        return Medicine.class;
    }

    @Override
    public String toString() {
        return name;
    }
}
