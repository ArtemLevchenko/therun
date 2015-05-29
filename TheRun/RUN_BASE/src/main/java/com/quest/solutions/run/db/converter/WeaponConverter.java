package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.item.Weapon;
import com.quest.solutions.run.db.entity.item.WeaponDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arle0814 on 29.01.2015.
 */
public class WeaponConverter extends BaseConverter implements IConverter<Weapon> {

    public WeaponConverter(String file) {
        fileOut = WeaponConverter.class.getResourceAsStream(file);
    }

    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(WeaponDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            WeaponDTO itemDTO = (WeaponDTO) jaxbUnmarshaller.unmarshal(fileOut);
            gameLoadContext.setWeapons(itemDTO.getWeapons());
        } catch (JAXBException ex) {
            Logger.getLogger(RoomConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
