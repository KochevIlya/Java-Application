package com.example.javaapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

@Getter
@Setter
public class Replacer {
    ObjectMapper objMapper = new ObjectMapper();

    public void replace(Result result)
    {

        if(result.getResultList().isEmpty())
           return;
        StringBuilder FinalBuiltString = new StringBuilder(result.getInputText().substring(0, (int) result.getDurationByIndex(0).getFrom()));
        for(int i = 0; i < result.getPositionsList().size() - 1; i++) {
            FinalBuiltString.append(result.getResultByIndex(i));
            FinalBuiltString.append(result.getInputText(), (int)result.getDurationByIndex(i).getTo() + 1, (int) result.getDurationByIndex(i + 1).getFrom());
        }

        FinalBuiltString.append(result.getResultByIndex(result.getPositionsList().size() - 1));
        String afterLast = result.getInputText().substring((int)result.getDurationByIndex(result.getPositionsList().size() -1).getTo() + 1);
        FinalBuiltString.append(afterLast);
        result.setReplacedText(FinalBuiltString.toString());
        return;
    }
    //TODO Write Replacer for 2-rd version of our Super-Project!
    //FIXME
    // - replace variables with nums,
    // - then place it in node <expression> .. </expression>
    // - then convert ArrayList of JsonNodes into String(for Jackson)
    //  - then convert ArrayList of MathExpressions into String(for XML)
    // - put this String into Result class as a "replacedText"
    public void replaceV2(Result result, String fileName) throws JAXBException {
        String[] strings = fileName.split("\\.");
        switch (strings[1]){
            case("json"):
                ArrayList<OneMathExp> oneMathExps = result.getJsonNodes();
            for(int i = 0; i < oneMathExps.size(); i++) {
                oneMathExps.get(i).setExpression(result.getResultList().get(i).toString());
            }
                result.setJsonNodes(oneMathExps);

                objMapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addDeserializer(MathExp.class, new MathExpDeserializer());
                objMapper.registerModule(module);
                objMapper.enable(SerializationFeature.INDENT_OUTPUT);
                StringWriter sw = new StringWriter();
                try {
                    objMapper.writeValue(sw, oneMathExps);
                    result.setReplacedText(sw.toString());
                    result.setBuffer(sw.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            break;

            case("xml"):
                ArrayList<MathExpression> mathExpressions = result.getContent().getMathExpressions();
                for(int i = 0; i < mathExpressions.size(); i++){
                    mathExpressions.get(i).setExpression(result.getResultList().get(i).toString());
                }
                Content content = new Content();
                content.setMathExpressions(mathExpressions);
                result.setContent(content);

                JAXBContext jaxbContext = JAXBContext.newInstance(Content.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                StringWriter swr = new StringWriter();
                jaxbMarshaller.marshal(result.getContent(), swr);
                result.setReplacedText(swr.toString());
                result.setBuffer(swr.toString());
                break;
            default:
                replace(result);
        }
    }

}
