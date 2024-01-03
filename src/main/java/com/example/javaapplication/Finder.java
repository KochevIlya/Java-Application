package com.example.javaapplication;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Finder {
    private ArrayList<Duration> positionsList = new ArrayList();

    private ArrayList<String> samplesList = new ArrayList<>();
    public Finder()
    {

    }
    public void find(Result result)
    {
        String parseString = result.getInputText();
        ArrayList<String> allMatches = new ArrayList<String>();
        Pattern pattern = Pattern.compile("([()-+]*?[0-9]*\\.?[0-9]+[\\/\\+\\-\\*\\^\\(\\)]*)+([-+]?[0-9]*\\.?[0-9]*[()]*?)");
        Matcher matcher = pattern.matcher(parseString);
        while(matcher.find()) {
            allMatches.add(matcher.group());
        }
        result.setSampleList(allMatches);
    }


}
