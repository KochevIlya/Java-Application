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
    public void testWriteToTxt()
    {

    }
}