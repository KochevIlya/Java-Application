package com.example.javaapplication;

import com.example.javaapplication.Reader;
import com.example.javaapplication.Result;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class ReplacerTest {

    @Test
    public void TestReplacer1 () {
        Result result = new Result();
        result.setInputText("Hello 2+2 , 5 * 4, I AM SIGMA MALE SSIIIIGMA");
        String expectedText = "Hello 4.0 , 20.0, I AM SIGMA MALE SSIIIIGMA";
        ArrayList<Duration> durations = new ArrayList<Duration>();
        Duration t1 = new Duration();
        t1.setFrom(6); t1.setTo(8);
        Duration t2 = new Duration();
        t2.setFrom(12); t2.setTo(16);
        durations.add(t1); durations.add(t2);
        result.setPositionsList(durations);
        Replacer replacer = new Replacer();

        ArrayList<Double> reslist = new ArrayList<>(Arrays.asList(4.0, 20.0));
        result.setResultList(reslist);

        replacer.replace(result);
        assertEquals(expectedText,result.getReplacedText());
    }

    @Test
    public void TestReplacer2 () {
        Result result = new Result();
        result.setInputText("sample1: 5+8" + "\n" +
                "sample2: 5*4+3" + "\n" +
                "sample3: 4/5" + "\n" + "АБРАКАДАБРА БЕБРА");
        String expectedText = "sample1: 13.0" + "\n" +
                "sample2: 23.0" + "\n" +
                "sample3: 0.8" + "\n" + "АБРАКАДАБРА БЕБРА";
        ArrayList<Duration> durations = new ArrayList<Duration>();
        Duration t1 = new Duration();
        Duration t2 = new Duration();
        Duration t3 = new Duration();
        t1.setFrom(9); t1.setTo(11);
        t2.setFrom(22); t2.setTo(26);
        t3.setFrom(37); t3.setTo(39);
        durations.add(t1); durations.add(t2); durations.add(t3);
        result.setPositionsList(durations);
        Replacer replacer = new Replacer();

        ArrayList<Double> reslist = new ArrayList<>(Arrays.asList(13.0,23.0,0.8));
        result.setResultList(reslist);

        replacer.replace(result);
        assertEquals(expectedText,result.getReplacedText());
    }

}
