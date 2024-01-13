package com.example.javaapplication;

import java.util.regex.PatternSyntaxException;

public class Decider {

    public int makeDecisionReader(String inputName)
    {
        if(!inputName.contains(".zip") && !inputName.contains(".enc"))
            return 1;
        if(inputName.contains(".zip") && !inputName.contains(".enc"))
            return 2;
        if(inputName.contains(".enc") && ! inputName.contains(".zip"))
            return 3;
        if(inputName.endsWith(".zip"))
            return 4;
        if(inputName.endsWith(".enc"))
            return 5;
        return 0;
    }
    public void makeDecision(String inputName, String outputName, Result result){
        try {
            int decision = 0;
            if(!(inputName.contains(".txt") || inputName.contains(".json") || inputName.contains(".xml")) || !(outputName.contains(".txt") || outputName.contains(".xml") || outputName.contains(".json")))
                throw new PatternSyntaxException("", "", 0);
            if(outputName.contains(".txt"))
                decision = 3;
            else if(outputName.contains(".xml"))
            {
                if(inputName.contains(".txt"))
                    decision = 5;
                else if (inputName.contains(".json"))
                    decision = 2;
                else if (inputName.contains(".xml"))
                    decision = 3;
            }
            else if(outputName.contains(".json"))
            {
                if(inputName.contains(".txt"))
                    decision = 4;
                else if(inputName.contains(".json"))
                    decision = 3;
                else if(inputName.contains(".xml"))
                    decision = 1;
            }
            result.setDecision(decision);
        }
        catch(PatternSyntaxException e)
        {
            System.out.println("Wrong file name, program can not decide with which files it works");
            throw e;
        }
        result.setCasesForReader(makeDecisionReader(inputName));

    }
}
