package com.example.javaapplication;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {

    public static String readContentFromFile(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));

        StringBuilder content = new StringBuilder();
        for (String line : lines) {
            content.append(line).append(System.lineSeparator());
        }
        return content.toString();
    }
    @Test
    public void testWriteToXml() throws IOException {
        Writer writer = new Writer("output.xml");
        Result result = new Result();
        result.setDecision(1);
        String text = ("""
                <hello> Something</hello>
                <nothing> new </nothing>""");
        result.setReplacedText(text);
        writer.write(result);
        String expectedText = text.replace("\n", System.lineSeparator());
        assertEquals(expectedText, readContentFromFile("output.xml").trim());
    }
    @Test
    public void testWriteToTxt() throws IOException {
        Writer writer = new Writer("output.txt");
        Result result = new Result();
        result.setDecision(1);
        String text = ("It it just simple text\n" +
                "without any difficulties");
        result.setReplacedText(text);
        writer.write(result);
        String expectedText = text.replace("\n", System.lineSeparator());
        assertEquals(expectedText, readContentFromFile("output.txt").trim());
    }
    @Test
    public void testWriteToJson() throws IOException {
        Writer writer = new Writer("output.json");
        Result result = new Result();
        result.setDecision(1);
        String text = """
                {
                "text" :  "Just simple text in Json"
                }""";
        result.setReplacedText(text);
        writer.write(result);
        String expectedText = text.replace("\n", System.lineSeparator());
        assertEquals(expectedText, readContentFromFile("output.json").trim());
    }
    @Test
    public void testWriteFromXmlToTxt() throws IOException {
        Writer writer = new Writer("outputFromXml.txt");
        Result result = new Result();
        result.setDecision(1);
        String text = ("<hello> Something</hello>\n" +
                "<nothing> new </nothing>");
        result.setReplacedText(text);
        writer.write(result);
        String expectedText = text.replace("\n", System.lineSeparator());
        assertEquals(expectedText, readContentFromFile("outputFromXml.txt").trim());
    }
    @Test
    public void testWriteFromJsonToTxt() throws IOException {
        Writer writer = new Writer("outputFromJson.txt");
        Result result = new Result();
        result.setDecision(1);
        String text = """
                {
                "text" :  "Just simple text in Json"
                }""";
        result.setReplacedText(text);
        writer.write(result);
        String expectedText = text.replace("\n", System.lineSeparator());
        assertEquals(expectedText, readContentFromFile("outputFromJson.txt").trim());
    }
    @Test
    public void testWriteFromTxtToXml() throws IOException {
        Writer writer = new Writer("outputFromTxt.xml");
        Result result = new Result();
        result.setDecision(5);
        String text = ("Hello it is just a simple text with samples 4 + 5, 4 - 3");
        ArrayList<String> sampleList = new ArrayList<>();
        sampleList.add("4 + 5");
        sampleList.add("4 - 3");
        ArrayList<Double> resultList = new ArrayList<>();
        resultList.add(9.0);
        resultList.add(1.0);
        result.setSampleList(sampleList);
        result.setResultList(resultList);
        result.setInputText(text);
        String expectedText = ("""
                <root>
                    <example>4 + 5</example>
                        <result>9.0</result>
                    <example>4 - 3</example>
                        <result>1.0</result>
                    <allText>Hello it is just a simple text with samples 4 + 5, 4 - 3</allText>
                </root>""");
        result.setReplacedText(text);
        writer.write(result);
        expectedText = expectedText.replace("\n", System.lineSeparator());
        assertEquals(expectedText, readContentFromFile("outputFromTxt.xml").trim());
    }
    @Test
    public void testWriteFromTxtToJson() throws IOException {
        Writer writer = new Writer("outputFromTxt.json");
        Result result = new Result();
        result.setDecision(4);
        String text = ("Hello it is just a simple text with samples 4 + 5, 4 - 3");
        ArrayList<String> sampleList = new ArrayList<>();
        sampleList.add("4 + 5");
        sampleList.add("4 - 3");
        ArrayList<Double> resultList = new ArrayList<>();
        resultList.add(9.0);
        resultList.add(1.0);
        result.setSampleList(sampleList);
        result.setResultList(resultList);
        result.setInputText(text);
        String expectedText = ("""
                {
                    "example" : "4 + 5",
                    "result" : "9.0",
                    "example" : "4 - 3",
                    "result" : "1.0",
                    "allText" : "Hello it is just a simple text with samples 4 + 5, 4 - 3"
                }""");
        result.setReplacedText(text);
        writer.write(result);
        expectedText = expectedText.replace("\n", System.lineSeparator());
        assertEquals(expectedText, readContentFromFile("outputFromTxt.json").trim());
    }
}