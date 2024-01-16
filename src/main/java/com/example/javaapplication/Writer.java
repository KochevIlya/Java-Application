package com.example.javaapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.json.XML;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class WriterException extends Throwable {

}
@Getter
public class Writer {
    @Setter
    private String fileName;
    @Setter
    private ObjectMapper objMapper;

    public void writeV2(Result result, String fileN) throws JAXBException, JsonProcessingException {
        String[] input = fileN.split("\\.");
        String[] output = fileName.split("\\.");

        if(input[1].equals("json") && output[1].equals("xml")) {

            String jsonString = "{\n" +
                    "  \"Content\":" + result.getBuffer() + "\n" +
                    "}";
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true);

            String xmlString = xmlMapper.writer().withRootName("root").writeValueAsString(jsonNode);
            result.setBuffer(xmlString);
        }

        if(input[1].equals("xml") && output[1].equals("json")) {

            JSONObject json = XML.toJSONObject(result.getBuffer());
            result.setBuffer(json.toString(4). replaceAll("\\\\", ""));

        }

        if(input[1].equals("txt") && output[1].equals("json")) {
            result.setBuffer(writeToJson(result));
        }

        if(input[1].equals("txt") && output[1].equals("xml")) {
            result.setBuffer(writeToXml(result));
        }
    }

    public String writeToJson(Result result)
    {
        String str = "";
        String tab = "    ";
        str += "{\n";
        for(int i = 0; i < result.getResultList().size(); i++)
        {
            str += tab +"\"example\" : " + "\"" + result.getSampleList().get(i) + "\"," + "\n";
            str += tab + "\"result\" : " + "\"" + result.getResultList().get(i) + "\"," + "\n";
        }
        str +=tab + "\"allText\" : ";
        str += "\"" +result.getInputText() + "\"" + "\n";
        str += "}";
        return str;
    }
    public String writeToXml(Result result)
    {
        String str = "";
        String tab = "    ";
        str += "<root>\n";
        for(int i = 0; i < result.getResultList().size(); i++)
        {
            str += tab +"<example>" + result.getSampleList().get(i) + "</example>" + "\n";

            str += tab + tab + "<result>" +  result.getResultList().get(i) + "</result>" + "\n";
        }
        str += tab + "<allText>";
        str += result.getInputText();
        str += "</allText>\n";
        str += "</root>";
        return str;
    }
    public String encryptData(Result result)
    {
        fileName += ".enc";
        String originalString = result.getReplacedText();
        try {
            Cipher cipher = Cipher.getInstance("AES");

            if(result.getEncryptedKey() == null) {
                System.out.println("Ваш ключ шифрования: SunnyDayHome1234\n Запомните его!!!");
                result.setEncryptedKey("SunnyDayHome1234");
            }
            SecretKeySpec secretKey;
            secretKey = new SecretKeySpec(result.getEncryptedKey().getBytes(), "AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes;
            if(result.isFirstEncrypt())
                encryptedBytes = cipher.doFinal(originalString.getBytes(), 0,    originalString.length());
            else
                encryptedBytes = cipher.doFinal(Base64.getDecoder().decode(originalString));

            return Base64.getEncoder().encodeToString(encryptedBytes);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String archiveDataZipAfter(Result result)
    {

        fileName += ".zip";
        String originalData = result.getReplacedText();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zos = new ZipOutputStream(baos)) {

            ZipEntry entry = new ZipEntry("data");
            zos.putNextEntry(entry);
            byte[] bytes;
            if(result.isFirstEncrypt())
                bytes = Base64.getDecoder().decode(originalData);

            else
                bytes = originalData.getBytes();

            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
            zos.finish();
            byte[] encryptedBytes = baos.toByteArray();
            String resultStr;
            resultStr = Base64.getEncoder().encodeToString(encryptedBytes);
            return resultStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Writer(String fileName)
    {
        setFileName(fileName);
    }
    public void write(Result result)  {

        try
        {
            int decision = result.getDecision();
            FileWriter fileWriter;
            if(result.getDecision() <1 || result.getDecision() > 5)
                throw new WriterException();

            switch(decision)
            {
                case 4:
                    if(result.getSampleList() == null || result.getResultList() == null || result.getInputText() == null)
                        throw new WriterException();

                    result.setReplacedText(writeToJson(result));

                    if(result.isShouldEncrypt() && result.isShouldArchive() && result.isFirstEncrypt()) {
                        result.setReplacedText(this.encryptData(result));
                        result.setReplacedText(this.archiveDataZipAfter(result));
                    }
                    else if(result.isShouldEncrypt() && result.isShouldArchive()) {
                        result.setReplacedText(this.archiveDataZipAfter(result));
                        result.setReplacedText(this.encryptData(result));
                    }
                    else if(result.isShouldEncrypt())
                        result.setReplacedText(this.encryptData(result));
                    else if(result.isShouldArchive())
                        result.setReplacedText(this.archiveDataZipAfter(result));

                    fileWriter = new FileWriter(fileName);
                    fileWriter.write(result.getReplacedText());
                    fileWriter.close();
                    break;

                case 5:
                    if(result.getSampleList() == null || result.getResultList() == null || result.getInputText() == null)
                        throw new WriterException();

                    result.setReplacedText(writeToXml(result));

                    if(result.isShouldEncrypt() && result.isShouldArchive() && result.isFirstEncrypt()) {
                        result.setReplacedText(this.encryptData(result));
                        result.setReplacedText(this.archiveDataZipAfter(result));
                    }
                    else if(result.isShouldEncrypt() && result.isShouldArchive()) {
                        result.setReplacedText(this.archiveDataZipAfter(result));
                        result.setReplacedText(this.encryptData(result));
                    }
                    else if(result.isShouldEncrypt())
                        result.setReplacedText(this.encryptData(result));
                    else if(result.isShouldArchive())
                        result.setReplacedText(this.archiveDataZipAfter(result));
                    fileWriter = new FileWriter(fileName);
                    fileWriter.write(result.getReplacedText());
                    fileWriter.close();
                    break;

                default:
                    if(result.getReplacedText() == null)
                        throw new WriterException();

                    if(result.isShouldEncrypt() && result.isShouldArchive() && result.isFirstEncrypt()) {
                        result.setReplacedText(this.encryptData(result));
                        result.setReplacedText(this.archiveDataZipAfter(result));
                    }
                    else if(result.isShouldEncrypt() && result.isShouldArchive()) {
                        result.setReplacedText(this.archiveDataZipAfter(result));
                        result.setReplacedText(this.encryptData(result));
                    }
                    else if(result.isShouldEncrypt()) {
                        String resStr = encryptData(result);
                        result.setReplacedText(resStr);
                    }
                    else if(result.isShouldArchive())
                        result.setReplacedText(this.archiveDataZipAfter(result));
                    fileWriter = new FileWriter(fileName);
                    fileWriter.write(result.getReplacedText().trim());
                    fileWriter.close();
                    break;
            }
        }
        catch(IOException e)
        {
            System.out.println("File name is wrong");
            System.exit(0);
        }
        catch(WriterException e )
        {
            System.out.println("Provided result is not full for that task");
            System.exit(0);
        }
    }

}
