package com.example.javaapplication;

import com.example.javaapplication.Reader;
import com.example.javaapplication.Result;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;


public class ReaderTest {
    @Test
    public void testXmlToJsonToStringRead1() {
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
        assertEquals(expected.replace("\n", System.lineSeparator()), result.getInputText().replace("\n", System.lineSeparator()));
    }


    @Test
    public void testJsonToXmlToString1() {
        Reader reader = new Reader("input1.json");
        Result result = new Result();
        result.setDecision(2);
        File file = new File("input1.json");
        String expected = new String("""
                <Cities>
                  <city>
                    <name>Minsk</name>
                    <country>Belarus</country>
                  </city>
                  <city>
                    <name>Moscow</name>
                    <country>Russia</country>
                  </city>
                </Cities>
                """);
        reader.read(result);
        assertEquals(expected.replace("\n", System.lineSeparator()), result.getInputText());
    }

    @Test
    public void StandartReadTestTxt() {
        Reader reader = new Reader("input3.txt");
        Result result = new Result();
        result.setDecision(3);
        File file = new File("input3.txt");
        String expected = new String("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean accumsan placerat massa ac aliquam. Integer justo enim, aliquam sed quam sit amet, rhoncus pretium nunc. Aliquam vestibulum ac dolor accumsan ultrices. Aliquam dapibus eros dolor, a pulvinar nisl accumsan nec. Donec fermentum fermentum nulla. Curabitur faucibus scelerisque lobortis. Sed ultricies elit vitae augue pellentesque, non porta eros accumsan. Suspendisse vel libero posuere, viverra metus sit amet, dapibus eros. Nam sit amet condimentum libero. Vivamus eget mollis orci. Aenean sagittis nisl commodo iaculis scelerisque. Pellentesque pharetra pulvinar consequat. Ut eu risus feugiat, placerat ante ut, placerat felis. Sed odio ligula, convallis ac lobortis vitae, imperdiet eu nulla.
                """);
        reader.read(result);
        assertEquals(expected.replace("\n", System.lineSeparator()), result.getInputText());
    }
    @Test
    public void StandartReadTestJson() {
        Reader reader = new Reader("input4.json");
        Result result = new Result();
        result.setDecision(3);
        File file = new File("input4.json");
        String expected = new String("""
                {
                  "enter": {
                    "plenty": [
                      "teeth",
                      "operation",
                      "poetry",
                      "character",
                      false,
                      true
                    ],
                    "shoot": true,
                    "arrange": 352278660.5470977,
                    "fair": false,
                    "silly": false,
                    "ability": "policeman"
                  },
                  "cave": false,
                  "ear": false,
                  "maybe": "idea",
                  "circle": 1543616687.1868362,
                  "usual": "valley"
                }
                """);
        reader.read(result);
        assertEquals(expected.replace("\n", System.lineSeparator()), result.getInputText());
    }
    @Test
    public void StandartReadTestXml() {
        Reader reader = new Reader("input5.xml");
        Result result = new Result();
        result.setDecision(3);
        File file = new File("input5.xml");
        String expected = new String("""
                <root>
                    <upper>-890202574.2332067</upper>
                    <grabbed>gone</grabbed>
                    <agree>
                        <play>twice</play>
                        <avoid>-669603261.364645</avoid>
                        <local>refused</local>
                        <size>hill</size>
                        <present>1823481319.1760015</present>
                        <stove>him</stove>
                    </agree>
                    <lungs>222169742</lungs>
                    <week>-1689655402.705463</week>
                    <collect>1209626468</collect>
                </root>
                """);
        reader.read(result);
        assertEquals(expected.replace("\n", System.lineSeparator()), result.getInputText());
    }
}




