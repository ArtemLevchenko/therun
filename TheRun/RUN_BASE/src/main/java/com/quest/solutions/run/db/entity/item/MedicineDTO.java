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
@XmlRootElement(name = "medicines")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicineDTO {

    @XmlElement(name = "medicine")
    private List<Medicine> medicines;

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

}
