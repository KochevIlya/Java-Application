package com.example.javaapplication;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public void testWriteJsonFile() throws IOException {
        // Arrange
        Writer writer = new Writer("example.json");
        Replacer replacer = new Replacer();
        replacer.setOutputText("This is JSON data.");

        // Act
        writer.write(replacer);

        // Assert
        String content = readContentFromFile("example.json");
        assertEquals("This is JSON data.", content);
    }
    @Test
    public void testWriteXmlFile() throws IOException {
        // Arrange
        Writer writer = new Writer("example.xml");
        Replacer replacer = new Replacer();
        replacer.setOutputText("This is XML data.");
        // Act
        writer.write(replacer);

        // Assert
        String content = readContentFromFile("example.xml");
        assertEquals("This is XML data.", content);
    }

    @Test
    public void testWriteTxtFile() throws IOException {
        // Arrange
        Writer writer = new Writer("example.txt");
        Replacer replacer = new Replacer();
        replacer.setOutputText("This is plain text data.");
        // Act
        writer.write(replacer);

        // Assert
        String content = readContentFromFile("example.txt");
        assertEquals("This is plain text data.", content);
    }
    @Test
    public void testWriteInvalidFileExtension() {
        // Arrange
        Writer writer = new Writer("example.invalid");
        Replacer replacer = new Replacer();
        replacer.setOutputText("Invalid extension data.");

        // Act and Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> writer.write(replacer)
        );
    }
}