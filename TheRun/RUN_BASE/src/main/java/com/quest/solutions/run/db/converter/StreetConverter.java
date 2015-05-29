package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.location.Street;
import com.quest.solutions.run.db.entity.location.StreetsDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author arle0814
 */
public class StreetConverter extends BaseConverter implements IConverter<Street> {

    public StreetConverter(String file) {
        fileOut = StreetConverter.class.getResourceAsStream(file);
    }

    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(StreetsDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StreetsDTO itemDTO = (StreetsDTO) jaxbUnmarshaller.unmarshal(fileOut);
            gameLoadContext.setStreets(itemDTO.getStreets());
        } catch (JAXBException ex) {
            Logger.getLogger(StreetConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
