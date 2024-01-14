package com.example.javaapplication;

import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Setter
@Getter
public class OneMathExp
{
    private String expression;
    private ArrayList<Character> variables;
    private ArrayList<Character> types;
    private ArrayList<Pair<Integer, Integer>> integers;
    private ArrayList<Pair<Double, Integer>> doubles;
}
