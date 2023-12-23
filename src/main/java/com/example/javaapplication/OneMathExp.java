package com.example.javaapplication;

import javafx.util.Pair;

import java.util.ArrayList;

public class OneMathExp
{
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public ArrayList<Character> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Character> variables) {
        this.variables = variables;
    }

    public ArrayList<Character> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Character> types) {
        this.types = types;
    }

    public ArrayList<Pair<Integer, Integer>> getIntegers() {
        return integers;
    }

    public void setIntegers(ArrayList<Pair<Integer, Integer>> integers) {
        this.integers = integers;
    }

    public ArrayList<Pair<Double, Integer>> getDoubles() {
        return doubles;
    }

    public void setDoubles(ArrayList<Pair<Double, Integer>> doubles) {
        this.doubles = doubles;
    }

    private String expression;
    private ArrayList<Character> variables;
    private ArrayList<Character> types;
    private ArrayList<Pair<Integer, Integer>> integers;
    private ArrayList<Pair<Double, Integer>> doubles;
}
