package com.example.javaapplication;

import java.util.ArrayList;

public class Result {
    private ArrayList<Double> resultList = new ArrayList<>();
    private ArrayList<String> sampleList = new ArrayList<>();
    private ArrayList<Duration> positionsList = new ArrayList<>();
    private String inputText;
    private String replacedText;
    private int decision;
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

    public boolean isShouldArchive() {
        return shouldArchive;
    }

    public void setShouldArchive(boolean shouldArchive) {
        this.shouldArchive = shouldArchive;
    }



    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }


    public boolean isShouldEncrypt() {
        return shouldEncrypt;
    }

    public void setShouldEncrypt(boolean shouldEncrypt) {
        this.shouldEncrypt = shouldEncrypt;
    }

    public ArrayList<Double> getResultList() {
        return resultList;
    }

    public void setResultList(ArrayList<Double> resultList) {
        this.resultList = resultList;
    }

    public ArrayList<String> getSampleList() {
        return sampleList;
    }

    public void setSampleList(ArrayList<String> sampleList) {
        this.sampleList = sampleList;
    }

    public ArrayList<Duration> getPositionsList() {
        return positionsList;
    }

    public void setPositionsList(ArrayList<Duration> positionsList) {
        this.positionsList = positionsList;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getReplacedText() {
        return replacedText;
    }

    public void setReplacedText(String replacedText) {
        this.replacedText = replacedText;
    }

    public int getDecision() {
        return decision;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }

    public Double getResultByIndex (int index) {return resultList.get(index); }

    public Duration getDurationByIndex (int index) {return positionsList.get(index); }

}
