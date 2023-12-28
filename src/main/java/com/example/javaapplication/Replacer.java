package com.example.javaapplication;

public class Replacer {

    public void replace(Result result)
    {
        if(result.getResultList().isEmpty())
           return;
        StringBuilder FinalBuiltString = new StringBuilder(result.getInputText().substring(0, (int) result.getDurationByIndex(0).getFrom()));
        for(int i = 0; i < result.getPositionsList().size() - 1; i++) {
            FinalBuiltString.append(result.getResultByIndex(i));
            FinalBuiltString.append(result.getInputText(), (int)result.getDurationByIndex(i).getTo() + 1, (int) result.getDurationByIndex(i + 1).getFrom());
        }

        FinalBuiltString.append(result.getResultByIndex(result.getPositionsList().size() - 1));
        String afterLast = result.getInputText().substring((int)result.getDurationByIndex(result.getPositionsList().size() -1).getTo() + 1);
        FinalBuiltString.append(afterLast);
        result.setReplacedText(FinalBuiltString.toString());
        return;
    }

}
