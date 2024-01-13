package com.example.javaapplication;

import org.junit.jupiter.api.Test;

import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class DeciderTest {

    @Test
    public void testMakeDecisionTxtToTxt() {
        Decider decider = new Decider();
        Result result = new Result();
        decider.makeDecision("input.txt", "output.txt", result);
        assertEquals(3, result.getDecision());
    }

    @Test
    public void testMakeDecisionXmlToJson() {
        Decider decider = new Decider();
        Result result = new Result();
        decider.makeDecision("input.xml", "output.json", result);
        assertEquals(1, result.getDecision());
    }

    @Test
    public void testMakeDecisionJsonToXml() {
        Decider decider = new Decider();
        Result result = new Result();
        decider.makeDecision("input.json", "output.xml", result);
        assertEquals(2, result.getDecision());
    }

    @Test
    public void testMakeDecisionXmlToXml() {
        Decider decider = new Decider();
        Result result = new Result();
        decider.makeDecision("input.xml", "output.xml", result);
        assertEquals(3, result.getDecision());
    }


    @Test
    public void testMakeDecisionInvalidFileExtension() {
        Decider decider = new Decider();
        Result result = new Result();
        PatternSyntaxException thrownException = assertThrows(PatternSyntaxException.class, () -> {
            decider.makeDecision("input.txt", "output.);xml(", result);
        });
    }
    @Test
    public void testMakeDecisionInvalidFileWithoutDot() {
        Decider decider = new Decider();
        Result result = new Result();
        ArrayIndexOutOfBoundsException thrownException = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            decider.makeDecision("inpu2", "output.txt", result);
        });
    }
    public void testMakeDecisionInvalidFileWithDot() {
        Decider decider = new Decider();
        Result result = new Result();
        ArrayIndexOutOfBoundsException thrownException = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            decider.makeDecision("input.txt.xml", "output.txt", result);
        });
    }
}
