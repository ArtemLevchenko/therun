package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.characters.Monster;
import com.quest.solutions.run.db.entity.characters.MonsterDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author arle0814
 */
public class MonsterConverter extends BaseConverter implements IConverter<Monster> {

    public MonsterConverter(String file) {
        fileOut = MonsterConverter.class.getResourceAsStream(file);
    }

    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(MonsterDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            MonsterDTO itemDTO = (MonsterDTO) jaxbUnmarshaller.unmarshal(fileOut);
            gameLoadContext.setMonsters(itemDTO.getMonsters());
        } catch (JAXBException ex) {
            Logger.getLogger(MonsterConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

