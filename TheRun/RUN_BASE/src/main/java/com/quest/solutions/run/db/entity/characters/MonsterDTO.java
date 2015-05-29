package com.quest.solutions.run.db.entity.characters;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arle0814
 */
@XmlRootElement(name = "monsters")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonsterDTO {

    @XmlElement(name = "monster")
    private List<Monster> monsters;

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

}
