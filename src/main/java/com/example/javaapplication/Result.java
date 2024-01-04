package com.example.javaapplication;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
public class Result {
    @Getter
    @Setter
    private ArrayList<Double> resultList = new ArrayList<>();
    @Getter
    @Setter
    private ArrayList<String> sampleList = new ArrayList<>();
    @Getter
    @Setter
    private ArrayList<Duration> positionsList = new ArrayList<>();
    @Getter
    @Setter
    private String inputText;
    @Getter
    @Setter
    private String replacedText;
    @Getter
    @Setter
    private ArrayList<MathExp> jsonNodes = new ArrayList<>();
    @Getter
    @Setter
    private ArrayList<HelperExpression> mathExpressions = new ArrayList<>();
    @Getter
    @Setter
    private int decision;
    @Getter
    @Setter
    private boolean shouldEncrypt;
    @Getter
    @Setter
    private boolean shouldArchive;
    @Getter
    @Setter
    private String encryptedKey;

    private boolean isFirstEncrypt;

    public boolean isFirstEncrypt() {
        return isFirstEncrypt;
    }

//    public void setFirstEncrypt(boolean firstEncrypt) {
//        isFirstEncrypt = firstEncrypt;
//    }
//
//    public void setShouldArchive(boolean shouldArchive) {
//        this.shouldArchive = shouldArchive;
//    }
//
//
//    public void setEncryptedKey(String encryptedKey) {
//        this.encryptedKey = encryptedKey;
//    }
//
//
//    public void setShouldEncrypt(boolean shouldEncrypt) {
//        this.shouldEncrypt = shouldEncrypt;
//    }

//    public void setResultList(ArrayList<Double> resultList) {
//        this.resultList = resultList;
//    }

//    public void setSampleList(ArrayList<String> sampleList) {
//        this.sampleList = sampleList;
//    }
//
//    public void setPositionsList(ArrayList<Duration> positionsList) {
//        this.positionsList = positionsList;
//    }
//
//    public void setInputText(String inputText) {
//        this.inputText = inputText;
//    }
//
//    public void setReplacedText(String replacedText) {
//        this.replacedText = replacedText;
//    }
//
//    public void setDecision(int decision) {
//        this.decision = decision;
//    }
    public Double getResultByIndex (int index) {return resultList.get(index); }

    public Duration getDurationByIndex (int index) {return positionsList.get(index); }

//    public void setJsonNodes(ArrayList<MathExp> jsonNodes) {this.jsonNodes = jsonNodes;}
//
//    public void setMathExpressions(ArrayList<HelperExpression> helperExpressions) {this.mathExpressions = helperExpressions;}
}
