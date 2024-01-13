package com.example.javaapplication;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class Result {
    private ArrayList<Double> resultList = new ArrayList<>();
    private ArrayList<String> sampleList = new ArrayList<>();
    private ArrayList<Duration> positionsList = new ArrayList<>();

    private String inputText;
    private String replacedText;

    private ArrayList<OneMathExp> jsonNodes = new ArrayList<>();
    private ArrayList<HelperExpression> mathExpressions = new ArrayList<>();
    private Content content;

    private int decision;

    private boolean isEncrypted;
    private boolean isArchived;
    private boolean shouldEncrypt;
    private boolean shouldArchive;

    private String encryptedKey;

    private boolean isFirstEncrypt;

    public boolean isFirstEncrypt() {
        return isFirstEncrypt;
    }
    public void setFirstEncrypt(boolean firstEncrypt) {
        isFirstEncrypt = firstEncrypt;
    }
    public Double getResultByIndex (int index) {return resultList.get(index); }
    public Duration getDurationByIndex (int index) {return positionsList.get(index); }
}
