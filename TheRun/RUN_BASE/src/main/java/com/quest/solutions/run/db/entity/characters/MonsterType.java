package com.quest.solutions.run.db.entity.characters;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arle0814
 */
@XmlRootElement
@XmlEnum(String.class)
public enum MonsterType {
    ZOMBIE, DOG, SPIDER, HUNTER, NEMESIS
}
