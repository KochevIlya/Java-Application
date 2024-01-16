package com.example.javaapplication;

import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
    public String decryptData(Result result, String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKey = new SecretKeySpec(result.getEncryptedKey().getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes;
            decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String decompressString(byte[] compressedData, Result result) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(compressedData);
             ZipInputStream zis = new ZipInputStream(bais)) {

            ZipEntry entry = zis.getNextEntry();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = zis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            byte[] afterBaos = baos.toByteArray();
            if(result.isFirstEncrypt())
                return Base64.getEncoder().encodeToString(afterBaos);
            else
                return baos.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error decompressing string", e);
        }
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

    @Test
    void testEncryptionWithKeyTxtToTxt() {
        try {
            Writer writer = new Writer("encryptedOutput.enc");
            Result result = new Result();
            result.setEncryptedKey("NewKeyDecription");
            String text = "Hello and Hi";
            result.setFirstEncrypt(true);
            result.setReplacedText(text);

            String afterEncryption = writer.encryptData(result);

            String afterDecryption = decryptData(result, afterEncryption);

            assertEquals(text, afterDecryption);
            }
        catch (Exception e) {
            e.printStackTrace();
            fail("Тест не удался: " + e.getMessage());
        }
    }
    @Test
    void testEncryptionWithoutKeyTxtToTxt() {
        try {
            Writer writer = new Writer("encryptedOutputwoKey.enc");
            Result result = new Result();
            String text = "Hello from txt to txt";
            result.setFirstEncrypt(true);
            result.setReplacedText(text);

            String afterEncryption = writer.encryptData(result);

            String afterDecryption = decryptData(result, afterEncryption);

            assertEquals(text, afterDecryption);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Тест не удался: " + e.getMessage());
        }
    }
    @Test
    public void encryptionAfter()
    {
        try {
            Writer writer = new Writer("encryptedAfterOutputwoKey.enc");
            Result result = new Result();
            String text = "Hello from txt to txt";
            byte[] base64Bytes = text.getBytes();
            String base64Str = Base64.getEncoder().encodeToString(base64Bytes);

            result.setReplacedText(base64Str);

            String afterEncryption = writer.encryptData(result);

            String afterDecryption = decryptData(result, afterEncryption);

            assertEquals(text, afterDecryption);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Тест не удался: " + e.getMessage());
        }
    }

     @Test
     public void archiveTestFirstEncrypt()
      {
        Writer writer = new Writer("ArchiveOutputZipAfterEncr.txt");
        Result result = new Result();
        result.setFirstEncrypt(true);
        String text = "Hello from txt to zip";
        byte[] parsedText = text.getBytes();
        String textBase = Base64.getEncoder().encodeToString(parsedText);
        result.setReplacedText(textBase);
        result.setShouldArchive(true);
        String afterArchive = writer.archiveDataZipAfter(result);
        String afterDearchive = decompressString(Base64.getDecoder().decode(afterArchive), result);
        assertEquals(textBase, afterDearchive);
    }
    @Test
    public void archiveTestSecondEncrypt()
    {
        Writer writer = new Writer("ArchiveOutputZipBeforeEncr.txt");
        Result result = new Result();
        String text = "Hello from txt to zip";
        result.setReplacedText(text);
        result.setShouldArchive(true);
        String afterArchive = writer.archiveDataZipAfter(result);
        String afterDearchive = decompressString(Base64.getDecoder().decode(afterArchive), result);
        assertEquals(text, afterDearchive);
    }
}