package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.events.Event;
import com.quest.solutions.run.db.entity.events.GlobalEvent;
import com.quest.solutions.run.db.entity.events.GlobalEventDTO;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arle0814 on 29.01.2015.
 */
public class GlobalEventConverter extends BaseConverter implements IConverter<GlobalEvent> {

    public GlobalEventConverter(String file) {
        fileOut = GlobalEventConverter.class.getResourceAsStream(file);
    }

    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(GlobalEventDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GlobalEventDTO eventDTO = (GlobalEventDTO) jaxbUnmarshaller.unmarshal(fileOut);
            List<Event> events = gameLoadContext.getEvents();
            for(Event event : eventDTO.getGlobalEvents()) {
                events.add(event);
            }
            gameLoadContext.setEvents(events);
        } catch (JAXBException ex) {
            Logger.getLogger(GlobalEventConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
