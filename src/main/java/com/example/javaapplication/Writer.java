package com.example.javaapplication;

import java.io.FileWriter;
import java.io.IOException;
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
            FileWriter fileWriter = new FileWriter(fileName);

            int decision = result.getDecision();
            if(result.getDecision() <1 || result.getDecision() > 5)
            {
                throw new WriterException();
            }
            switch(decision)
            {
                case 4:
                    if(result.getSampleList() == null || result.getResultList() == null || result.getInputText() == null) {
                        throw new WriterException();
                    }
                    fileWriter.write(writeToJson(result));
                    break;
                case 5:
                    if(result.getSampleList() == null || result.getResultList() == null || result.getInputText() == null) {
                        throw new WriterException();
                    }
                    fileWriter.write(writeToXml(result));
                    break;

                default:
                    if(result.getReplacedText() == null)
                    {
                        throw new WriterException();
                    }
                    fileWriter.write(result.getReplacedText().trim());
                    break;
            }
            fileWriter.close();
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
