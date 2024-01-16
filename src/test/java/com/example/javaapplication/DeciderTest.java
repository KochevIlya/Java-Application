
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
        assertThrows(PatternSyntaxException.class, () -> {
            decider.makeDecision("input.txt", "output.);xml(", result);
        });
    }
    @Test
    public void testMakeDecisionInvalidFileWithoutDot() {
        Decider decider = new Decider();
        Result result = new Result();
        assertThrows(PatternSyntaxException.class, () -> {
            decider.makeDecision("inpu2", "output.txt", result);
        });
    }
    @Test
    public void testMakeDecisionFirstEnc()
    {
        Decider decider = new Decider();
        assertEquals(decider.makeDecisionReader("input.txt.enc.zip"), 4);
    }

    @Test
    public void testMakeDecisionFirstArch()
    {
        Decider decider = new Decider();
        assertEquals(decider.makeDecisionReader("input.txt.zip.enc"), 5);
    }
    @Test
    public void testMakeDecisionOnlyArch()
    {
        Decider decider = new Decider();
        assertEquals(decider.makeDecisionReader("input.zip"), 2);
    }
    @Test
    public void testMakeDecisionOnlyEnc()
    {
        Decider decider = new Decider();
        assertEquals(decider.makeDecisionReader("input1234....enc"), 3);
    }
    @Test
    public void testMakeDecisionNothing()
    {
        Decider decider = new Decider();
        assertEquals(decider.makeDecisionReader(""), 1);
    }
    @Test
    public void testMakeDecisionAbraCadabra()
    {
        Decider decider = new Decider();
        assertEquals(decider.makeDecisionReader("wqrlkjh.123l;j.reqlja[d9843"), 1);
    }
}
