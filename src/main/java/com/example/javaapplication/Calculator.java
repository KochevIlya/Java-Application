package com.example.javaapplication;
import utilities.*;
public class Calculator {

    private final ICalculator iCalculator;
    public void calculate(Result result)
    {
        iCalculator.calculate(result);
    }
    public Calculator()
    {
        iCalculator = new APICalculator();
    }

}
