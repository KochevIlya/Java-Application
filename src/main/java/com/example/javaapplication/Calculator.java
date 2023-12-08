package com.example.javaapplication;
import utilities.*;
public class Calculator {

    private final CalculatorImplementation calculatorImplementation;
    public void calculate(Result result)
    {
        calculatorImplementation.calculate(result);
    }
    public Calculator()
    {
        calculatorImplementation = new APICalculator();
    }

}
