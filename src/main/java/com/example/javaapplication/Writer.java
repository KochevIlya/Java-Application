package com.example.javaapplication;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;

class WriterException extends Throwable {

}
public class Writer {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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


    public Writer(String fileName)
    {
        setFileName(fileName);
    }
    public void write(Result result)  {
        try {
            int decision = result.getDecision();

            if(result.getDecision() <1 || result.getDecision() > 5)
                throw new WriterException();

            switch(decision)
            {
                case 4:
                    if(result.getSampleList() == null || result.getResultList() == null || result.getInputText() == null)
                        throw new WriterException();

                    if(result.isShouldEncrypt())
                    {
                        result.setReplacedText(writeToJson(result));
                        this.encryptData(result);
                        return;
                    }
                    else {
                        FileWriter fileWriter = new FileWriter(fileName);
                        fileWriter.write(writeToJson(result));
                        fileWriter.close();
                    }
                    break;

                case 5:
                    if(result.getSampleList() == null || result.getResultList() == null || result.getInputText() == null)
                        throw new WriterException();

                    if(result.isShouldEncrypt())
                    {
                        result.setReplacedText(writeToXml(result));
                        this.encryptData(result);
                        return;
                    }
                    else {
                        FileWriter fileWriter = new FileWriter(fileName);
                        fileWriter.write(writeToXml(result));
                        fileWriter.close();
                    }
                    break;

                default:
                    if(result.getReplacedText() == null)
                        throw new WriterException();

                    if(result.isShouldEncrypt()) {
                        this.encryptData(result);
                        return;
                    }
                    else {
                        FileWriter fileWriter = new FileWriter(fileName);
                        fileWriter.write(result.getReplacedText().trim());
                        fileWriter.close();
                    }
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
    public void encryptData(Result result)
    {
        String originalString = result.getReplacedText();
        try {
            // Получите экземпляр Cipher с установленным режимом шифрования и ключом
            Cipher cipher = Cipher.getInstance("AES");

            if(result.getEncryptedKey() == null) {
                System.out.println("Ваш ключ шифрования: SunnyDayHome1234\n Запомните его!!!");
                result.setEncryptedKey("SunnyDayHome1234");
            }
            SecretKeySpec secretKey = new SecretKeySpec(result.getEncryptedKey().getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedBytes = cipher.doFinal(originalString.getBytes(), 0,    originalString.length());

            // Записать зашифрованные данные в файл с использованием FileWriter
            try {
                Files.write(Paths.get(fileName), encryptedBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
