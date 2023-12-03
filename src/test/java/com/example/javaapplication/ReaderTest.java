package com.example.javaapplication;

import com.example.javaapplication.Reader;
import com.example.javaapplication.Result;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;


public class ReaderTest {
    @Test
    public void testXmlToJsonToStringRead2() {
        Reader reader = new Reader("input2.xml");
        Result result = new Result();
        result.setDecision(1);
        File file = new File("input2.xml");
        String expected = new String("{\n" +
                "    \"city\" : [ {\n" +
                "        \"name\" : \"Minsk\",\n" +
                "        \"country\" : \"Belarus\"\n" +
                "    }, {\n" +
                "        \"name\" : \"Moscow\",\n" +
                "        \"country\" : \"Russia\"\n" +
                "    } ]\n" +
                "}");
        reader.read(result);
        assertEquals(expected, result.getInputText());
    }
}
