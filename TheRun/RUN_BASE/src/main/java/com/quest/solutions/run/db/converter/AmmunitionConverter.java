package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.item.Ammunition;
import com.quest.solutions.run.db.entity.item.AmmunitionDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arle0814 on 29.01.2015.
 */
public class AmmunitionConverter extends BaseConverter implements IConverter<Ammunition> {

    public AmmunitionConverter(String file) {
        fileOut = AmmunitionConverter.class.getResourceAsStream(file);
    }

    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(AmmunitionDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            AmmunitionDTO itemDTO = (AmmunitionDTO) jaxbUnmarshaller.unmarshal(fileOut);
            gameLoadContext.setAmunitions(itemDTO.getAmmunitions());
        } catch (JAXBException ex) {
            Logger.getLogger(AmmunitionConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
