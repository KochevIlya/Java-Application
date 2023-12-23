package com.example.javaapplication;

import java.util.ArrayList;

public class MathExp {
    public ArrayList<OneMathExp> getMathExps() {
        return mathExps;
    }

    public void setMathExps(ArrayList<OneMathExp> mathExps) {
        this.mathExps = mathExps;
    }

    private ArrayList<OneMathExp> mathExps = new ArrayList<>();
}
