package com.example.javaapplication.ReaderTestInput;

import com.example.javaapplication.Reader;
import com.example.javaapplication.Result;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;


public class ReaderTest {

    @Test
    public void testXmlToJsonToStringRead() {
        Reader reader = new Reader("input1.xml");
        Result result = new Result();
        result.setDecision(1);
        File file = new File("input1.xml");
        String expected = new String("{\n" +
                "  \"person\": {\n" +
                "    \"name\": \"Bob\",\n" +
                "    \"age\": 31,\n" +
                "    \"job\": \"Software Engineer\"\n" +
                "  }\n" +
                "}");
        reader.read(result);
        assertEquals(expected, result.getInputText());
    }
}
