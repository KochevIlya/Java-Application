package com.example.javaapplication;

import java.util.regex.PatternSyntaxException;

public class Decider {
    private int decision;

    public void makeDecision(String inputName, String outputName) throws ArrayIndexOutOfBoundsException{
        try {
            String[] parsedInput = inputName.split("\\.");
            String[] parsedOutput = outputName.split("\\.");

            if(parsedOutput.length > 2 || parsedInput.length > 2)
            {
                throw new ArrayIndexOutOfBoundsException();
            }

            String extInput = parsedInput[1];
            String extOutput = parsedOutput[1];

            switch (extOutput) {
                case ("txt"):
                    decision = 3;
                    break;
                case ("xml"):
                    switch (extInput) {
                        case ("txt"):
                            decision = 5;
                            break;
                        case ("json"):
                            decision = 2;
                            break;
                        case ("xml"):
                            decision = 3;
                            break;
                        default:
                            throw new PatternSyntaxException("","",0);
                    }
                    break;
                case ("json"):
                    switch (extInput) {
                        case ("txt"):
                            decision = 4;
                            break;
                        case ("json"):
                            decision = 3;
                            break;
                        case ("xml"):
                            decision = 1;
                            break;
                        default:
                            throw new PatternSyntaxException("","",0);
                    }
                    break;
                default:
                    throw new PatternSyntaxException("","",0);
            }

        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Wrong input or output file name");
            throw e;
        }
        catch(PatternSyntaxException e)
        {
            System.out.println("Wrong extension in input or output file name");
            throw e;
        }
    }

    public int getDecision() {
        return decision;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }
}
