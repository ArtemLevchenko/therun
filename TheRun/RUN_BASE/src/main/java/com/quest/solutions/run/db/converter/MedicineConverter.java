package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.item.Medicine;
import com.quest.solutions.run.db.entity.item.MedicineDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arle0814 on 29.01.2015.
 */
public class MedicineConverter extends BaseConverter implements IConverter<Medicine> {
    
    public MedicineConverter(String file) {
        fileOut = MedicineConverter.class.getResourceAsStream(file);
    }
    
    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(MedicineDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            MedicineDTO itemDTO = (MedicineDTO) jaxbUnmarshaller.unmarshal(fileOut);
            gameLoadContext.setMedicines(itemDTO.getMedicines());
        } catch (JAXBException ex) {
            Logger.getLogger(RoomConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
