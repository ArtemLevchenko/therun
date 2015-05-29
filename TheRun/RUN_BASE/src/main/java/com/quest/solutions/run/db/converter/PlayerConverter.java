package com.quest.solutions.run.db.converter;

import com.quest.solutions.run.db.entity.characters.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author arle0814
 */
public class PlayerConverter extends BaseConverter implements IConverter<Player> {

    public PlayerConverter(String file) {
        fileOut = PlayerConverter.class.getResourceAsStream(file);
    }

    @Override
    public void loadData(GameLoaderSourceContext gameLoadContext) {
        try {
            jaxbContext = JAXBContext.newInstance(Player.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Player player = (Player) jaxbUnmarshaller.unmarshal(fileOut);
            gameLoadContext.setPlayer(player);
        } catch (JAXBException ex) {
            Logger.getLogger(PlayerConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
