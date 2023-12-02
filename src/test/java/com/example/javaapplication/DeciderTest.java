package com.example.javaapplication;

import org.junit.jupiter.api.Test;

import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class DeciderTest {

    @Test
    public void testMakeDecisionTxtToTxt() {
        Decider decider = new Decider();
        decider.makeDecision("input.txt", "output.txt");
        assertEquals(3, decider.getDecision());
    }

    @Test
    public void testMakeDecisionXmlToJson() {
        Decider decider = new Decider();
        decider.makeDecision("input.xml", "output.json");
        assertEquals(1, decider.getDecision());
    }

    @Test
    public void testMakeDecisionJsonToXml() {
        Decider decider = new Decider();
        decider.makeDecision("input.json", "output.xml");
        assertEquals(2, decider.getDecision());
    }

    @Test
    public void testMakeDecisionXmlToXml() {
        Decider decider = new Decider();
        decider.makeDecision("input.xml", "output.xml");
        assertEquals(3, decider.getDecision());
    }


    @Test
    public void testMakeDecisionInvalidFileExtension() {
        Decider decider = new Decider();

        PatternSyntaxException thrownException = assertThrows(PatternSyntaxException.class, () -> {
            decider.makeDecision("input.txt", "output.);xml(");
        });
    }
    @Test
    public void testMakeDecisionInvalidFileWithoutDot() {
        Decider decider = new Decider();

        ArrayIndexOutOfBoundsException thrownException = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            decider.makeDecision("inpu2", "output.txt");
        });
    }
    public void testMakeDecisionInvalidFileWithDot() {
        Decider decider = new Decider();

        ArrayIndexOutOfBoundsException thrownException = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            decider.makeDecision("input.txt.xml", "output.txt");
        });
    }
}
