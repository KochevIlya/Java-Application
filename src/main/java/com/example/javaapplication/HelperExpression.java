package com.example.javaapplication;

import java.util.ArrayList;

public class HelperExpression {
    public char[] getFinal_variables() {
        return final_variables;
    }

    public void setFinal_variables(char[] final_variables) {
        this.final_variables = final_variables;
    }

    public char[] getFinal_types() {
        return final_types;
    }

    public void setFinal_types(char[] final_types) {
        this.final_types = final_types;
    }

    public ArrayList<MyPair> getFinal_integers() {
        return final_integers;
    }

    public void setFinal_integers(ArrayList<MyPair> final_integers) {
        this.final_integers = final_integers;
    }

    public ArrayList<MyPairDouble> getFinal_doubles() {
        return final_doubles;
    }

    public void setFinal_doubles(ArrayList<MyPairDouble> final_doubles) {
        this.final_doubles = final_doubles;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    private String expression;
    private char[] final_variables;
    private char[] final_types;
    private ArrayList<MyPair> final_integers = new ArrayList<>();
    private ArrayList<MyPairDouble> final_doubles = new ArrayList<>();
}
