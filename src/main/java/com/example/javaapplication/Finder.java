package com.example.javaapplication;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        ArrayList<String> allMatches = new ArrayList<>();
        Pattern pattern = Pattern.compile("([()-+]*?[0-9]*\\.?[0-9]+[/+\\-*^()]*)+([-+]?[0-9]*\\.?[0-9]*[()]*?)");
        Matcher matcher = pattern.matcher(parseString);
        while(matcher.find()) {
            allMatches.add(matcher.group());
        }
        result.setSampleList(allMatches);
    }
public void findV2(Result result, String fileName){
    String[] strings = fileName.split("\\.");
    switch(strings[1]){
        case("json"):
            int size = result.getJsonNodes().size();
            ArrayList<MathExp> mathExps = result.getJsonNodes();
            MathExp oneMathExp;
            ArrayList<OneMathExp> elem;
            OneMathExp one;
            for(int i = 0; i < size; i++){
                oneMathExp = mathExps.get(i);
                elem = oneMathExp.getMathExps();
                one = elem.get(0);
                String expression = one.getExpression();
                Map<Character, Double> m = getDoubleMap(one);
                StringBuilder final_str = new StringBuilder();
                for(int j = 1; j < expression.length(); j++){
                    if(expression.charAt(j) != ' ') {
                        if (Character.isLetter(expression.charAt(j))) {
                            Double num = m.get(expression.charAt(j));
                            if(num < 0) {
                                final_str.append('(').append(num).append(')');
                            }
                            else {
                                final_str.append(num);
                            }
                        }
                        else {
                            final_str.append(expression.charAt(j));
                        }
                    }
                }
                result.getSampleList().add(String.valueOf(final_str));
            }
            break;
        case("xml"):
            ArrayList<HelperExpression> helperExpressions = result.getMathExpressions();
            for (HelperExpression helperExpression : helperExpressions) {
                String expression = helperExpression.getExpression();
                Map<Character, Double> m = getDoubleMap(helperExpression);
                StringBuilder final_str = new StringBuilder();
                for (int j = 0; j < expression.length(); j++) {
                    if (expression.charAt(j) != ' ') {
                        if (Character.isLetter(expression.charAt(j))) {
                            Double num = m.get(expression.charAt(j));
                            if (num < 0) {
                                final_str.append('(').append(num).append(')');
                            } else {
                                final_str.append(num);
                            }
                        } else {
                            final_str.append(expression.charAt(j));
                        }
                    }
                }
                result.getSampleList().add(String.valueOf(final_str));
            }
            break;
    }
}

    private static Map<Character, Double> getDoubleMap(HelperExpression helperExpression) {
        char[] variables = helperExpression.getFinal_variables();
        ArrayList<MyPair> integers = helperExpression.getFinal_integers();
        ArrayList<MyPairDouble> doubles = helperExpression.getFinal_doubles();
        Map<Character, Double> m = new HashMap<>();
        for (MyPair integer : integers) {
            m.put(variables[integer.getTwo()], (double) integer.getOne());
        }
        for (MyPairDouble aDouble : doubles) {
            m.put(variables[aDouble.getTwo()], aDouble.getOne());
        }
        return m;
    }

    private static Map<Character, Double> getDoubleMap(OneMathExp one) {
        ArrayList<Character> variables = one.getVariables();
        ArrayList<Pair<Integer, Integer>> integers = one.getIntegers();
        ArrayList<Pair<Double, Integer>> doubles = one.getDoubles();
        Map<Character, Double> m = new HashMap<>();
        for (Pair<Integer, Integer> integer : integers) {
            m.put(variables.get(integer.getValue()), Double.valueOf(integer.getKey()));
        }
        for (Pair<Double, Integer> aDouble : doubles) {
            m.put(variables.get(aDouble.getValue()), aDouble.getKey());
        }
        return m;
    }

}
