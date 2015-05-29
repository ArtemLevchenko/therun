package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.item.ItemInventory;
import com.quest.solutions.run.db.entity.item.ItemInventoryDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by arle0814 on 29.01.2015.
 */
public class ItemInventoryConverter extends BaseConverter implements IConverter<ItemInventory> {

    public ItemInventoryConverter(String file) {
        fileOut = ItemInventoryConverter.class.getResourceAsStream(file);
    }

    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(ItemInventoryDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ItemInventoryDTO itemDTO = (ItemInventoryDTO) jaxbUnmarshaller.unmarshal(fileOut);
            gameLoadContext.setItemInventory(itemDTO.getItemInventories());
        } catch (JAXBException ex) {
            Logger.getLogger(RoomConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
