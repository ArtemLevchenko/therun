package com.quest.solutions.run.db.entity.item;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Artem
 */
@XmlRootElement
@XmlEnum(String.class)
public enum MedicineType {
    HEALTH, MENTAL
}
