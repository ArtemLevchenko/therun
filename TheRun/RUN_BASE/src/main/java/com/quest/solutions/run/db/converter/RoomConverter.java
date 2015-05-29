package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.location.Room;
import com.quest.solutions.run.db.entity.location.RoomsDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arle0814 on 29.01.2015.
 */
public class RoomConverter extends BaseConverter implements IConverter<Room> {

    public RoomConverter(String file){
        fileOut = RoomConverter.class.getResourceAsStream(file);
    }

    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(RoomsDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            RoomsDTO itemDTO = (RoomsDTO) jaxbUnmarshaller.unmarshal(fileOut);
            gameLoadContext.setRooms(itemDTO.getRooms());
        } catch (JAXBException ex) {
            Logger.getLogger(RoomConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
