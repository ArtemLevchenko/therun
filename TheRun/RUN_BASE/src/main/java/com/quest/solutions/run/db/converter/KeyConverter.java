package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.item.Key;
import com.quest.solutions.run.db.entity.item.KeyDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arle0814 on 29.01.2015.
 */
public class KeyConverter extends BaseConverter implements IConverter<Key> {
    
    public KeyConverter(String file) {
        fileOut = KeyConverter.class.getResourceAsStream(file);
    }
    
    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(KeyDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            KeyDTO itemDTO = (KeyDTO) jaxbUnmarshaller.unmarshal(fileOut);
            gameLoadContext.setKeys(itemDTO.getKeys());
        } catch (JAXBException ex) {
            Logger.getLogger(RoomConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
