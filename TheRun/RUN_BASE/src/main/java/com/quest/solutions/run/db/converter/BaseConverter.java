package com.quest.solutions.run.db.converter;

import javax.xml.bind.JAXBContext;
import java.io.InputStream;

/**
 * Created by arle0814 on 29.01.2015.
 */
public class BaseConverter {

    protected JAXBContext jaxbContext;
    protected InputStream fileOut;

    public BaseConverter(JAXBContext jaxbContext, InputStream  fileOut) {
        this.jaxbContext = jaxbContext;
        this.fileOut = fileOut;
    }

    public BaseConverter() {
    }

    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    public void setJaxbContext(JAXBContext jaxbContext) {
        this.jaxbContext = jaxbContext;
    }

    public InputStream getFileOut() {
        return fileOut;
    }

    public void setFileOut(InputStream fileOut) {
        this.fileOut = fileOut;
    }
}
