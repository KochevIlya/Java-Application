package com.example.javaapplication;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Setter
@Getter
public class HelperExpression {
    private String expression;
    private char[] final_variables;
    private char[] final_types;
    private ArrayList<MyPair> final_integers = new ArrayList<>();
    private ArrayList<MyPairDouble> final_doubles = new ArrayList<>();
}
