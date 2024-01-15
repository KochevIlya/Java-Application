package com.example.javaapplication;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Reader {
    private String fileName;
    private ObjectMapper objMapper;
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

                    System.out.println("Неверное имя файла, Попробуйте ещё раз!");
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
                }
                catch(JsonGenerationException e)
                {
                    System.out.println("Невозможно преобразовать данный файл в xml(");
                    System.exit(0);
                }
                catch (IOException a) {

                    System.out.println("Неверное имя файла, Попробуйте ещё раз!");

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

                    System.out.println("Неверное имя файла, Попробуйте ещё раз!");

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
    public void readV2(Result result) throws IOException, JAXBException {
        String[] strings = fileName.split("\\.");
        switch (strings[1]){
            case("json"):
                // from json using deserializer
                objMapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addDeserializer(MathExp.class, new MathExpDeserializer());
                objMapper.registerModule(module);
                ArrayList<MathExp> math_exp = objMapper.readValue(new File(fileName), new TypeReference<ArrayList<MathExp>>() {
                });
                ArrayList<OneMathExp> oneMathExps = new ArrayList<>();
                for (MathExp mathExp : math_exp) {
                    oneMathExps.add(mathExp.getMathExps().get(0));
                }
                result.setJsonNodes(oneMathExps);
                break;
            case("xml"):
                // from xml using Unmarshaller(jaxb)
                JAXBContext jc = JAXBContext.newInstance(Content.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                Content root = (Content) unmarshaller.unmarshal(new File(fileName));
                ArrayList<MathExpression> input = root.getMathExpressions();
                ArrayList<HelperExpression> helperExpressions = new ArrayList<>();
                for(int i = 0; i < root.getMathExpressions().size(); i++){
                    helperExpressions.add(input.get(i).createMathExpression());
                }
                Content content = new Content();
                content.setMathExpressions(input);
                result.setContent(content);
                result.setMathExpressions(helperExpressions);
                break;

        }
    }

    public void read() {
        return;
    }
}
