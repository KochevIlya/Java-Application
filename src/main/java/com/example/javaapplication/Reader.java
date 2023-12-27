package com.example.javaapplication;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


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
                    System.out.println("Wrong File name. Try again");
                    a.printStackTrace();
                    System.exit(0);
                }
                break;
            case(2):
                try {
                    ObjectMapper jsonmapper = new ObjectMapper();
                    JsonNode jsonnode = jsonmapper.readTree(new File(fileName));
                    XmlMapper xmlmapper = new XmlMapper();
                    xmlmapper.enable(ToXmlGenerator.Feature.UNWRAP_ROOT_OBJECT_NODE);
                    String xmlstring = xmlmapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonnode);
                    result.setInputText(xmlstring);
                } catch (IOException a) {
                    System.out.println("Wrong File name. Try again");
                    a.printStackTrace();
                    System.exit(0);
                }
                break;
            case(6):
                break;
            case(7):
                break;
            default:
                List<String> lines = null;
                try {
                    lines = Files.readAllLines(Paths.get(fileName));
                }
                catch (IOException e) {
                    System.out.println("Wrong File name. Try again");
                    System.exit(0);
                }
                StringBuilder content = new StringBuilder();
                for (String line : lines) {
                    content.append(line).append(System.lineSeparator());
                }
                result.setInputText(content.toString());
                break;
        }
        return;
    }


    public void read() {
        return;
    }
}
