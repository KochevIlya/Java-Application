package com.example.javaapplication;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;


public class Reader {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Reader(String fileName)
    {
        this.fileName = fileName;
    }
    public void read(Result result)
    {
        switch (result.getDecision()) {
            case(1):
                try {
                    XmlMapper xmlmapper = new XmlMapper();
                    JsonNode jsonnode = xmlmapper.readTree(new File(fileName));
                    ObjectMapper jsonmapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                    DefaultPrettyPrinter printer = new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("    ", "\n"));
                    String jsonstring = jsonmapper.writer(printer).writeValueAsString(jsonnode);
                    result.setInputText(jsonstring);
                } catch (IOException a) {
                    a.printStackTrace();
                }
                break;
            case(2):
                break;
            default:
                break;
        }
        return;
    }


    public void read() {
        return;
    }
}
