package com.example.javaapplication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class MyPair
{
    private int one;
    private int two;
}
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class MyPairDouble
{
    private double one;
    private int two;

}

@XmlRootElement(name="MathExpression")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class MathExpression {
    @XmlElement(name="expression")
    private String expression;
    @XmlElement(name="variables")
    private String variables;
    @XmlElement(name="types")
    private String types;
    @XmlElement(name="integers")
    private  String integers;
    @XmlElement(name="doubles")
    private  String doubles;
    public MathExpression(){
    }
    public char[] splitVariables()
    {
        if(variables != null) {
            String newstr = variables.replaceAll("[^A-Za-z]+", "");
            char[] variables_ = newstr.toCharArray();
            return variables_;
        }
        return new char[0];
    }
    public char[] splitTypes()
    {
        if(types != null) {
            String newstr = types.replaceAll("[^A-Za-z]+", "");
            char[] types_ = newstr.toCharArray();
            return types_;
        }
        return new char[0];
    }
    public ArrayList<MyPair> splitIntegers()
    {
        String newstr = integers.replaceAll("\"{1,2}", "");
        ArrayList<MyPair> myPairs = new ArrayList<>();
        Pattern p = Pattern.compile("\\((-?\\d+\\,-?\\d+)\\)");
        Matcher m = p.matcher(newstr);
        String s = null;
        while(m.find()) {
            s = m.group(1);
            String[] myPair = s.split(",");
            myPairs.add(new MyPair(Integer.parseInt(myPair[0]), Integer.parseInt(myPair[1])));
        }
        return myPairs;
    }
    public ArrayList<MyPairDouble> splitDoubles()
    {
        String newstr = doubles.replaceAll("\"{1,2}", "");
        ArrayList<MyPairDouble> myPairs = new ArrayList<>();
        Pattern p = Pattern.compile("\\((-?[0-9eE+-]+\\.?[0-9eE+-]*,-?\\d+)\\)");
        Matcher m = p.matcher(newstr);
        String s = null;
        while(m.find()) {
            s = m.group(1);
            String[] myPairDouble = s.split(",");
            myPairs.add(new MyPairDouble(Double.parseDouble(myPairDouble[0]), Integer.parseInt(myPairDouble[1])));
        }
        return myPairs;
    }
    public HelperExpression createMathExpression()
    {
        HelperExpression helperExpression = new HelperExpression();
        helperExpression.setExpression(expression);
        helperExpression.setFinal_variables(splitVariables());
        helperExpression.setFinal_types(splitTypes());
        helperExpression.setFinal_integers(splitIntegers());
        helperExpression.setFinal_doubles(splitDoubles());
        return helperExpression;
    }
}
