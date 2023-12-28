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
        ArrayList<Duration> positionsList = new ArrayList<>();
        while(matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            Duration tempDuration = new Duration();
            tempDuration.setFrom(startIndex);
            tempDuration.setTo(endIndex);
            positionsList.add(tempDuration);
            allMatches.add(matcher.group());
        }
        if(!positionsList.isEmpty())
            result.setPositionsList(positionsList);
        result.setSampleList(allMatches);
    }


}
