package com.example.javaapplication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class MyPair
{
    int one;
    int two;

    public int getOne() {
        return one;
    }

    public MyPair(int one, int two) {
        this.one = one;
        this.two = two;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }
    public MyPair()
    {
        this.one = 0;
        this.two = 0;
    }
}
class MyPairDouble
{
    double one;
    int two;

    public double getOne() {
        return one;
    }

    public MyPairDouble(double one, int two) {
        this.one = one;
        this.two = two;
    }

    public void setOne(double one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }
    public MyPairDouble()
    {
        this.one = 0;
        this.two = 0;
    }
}



@XmlRootElement(name="MathExpression")
@XmlAccessorType(XmlAccessType.FIELD)
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
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getIntegers() {
        return integers;
    }

    public void setIntegers(String integers) {
        this.integers = integers;
    }

    public String getDoubles() {
        return doubles;
    }

    public void setDoubles(String doubles) {
        this.doubles = doubles;
    }
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
      //       (0,0)(1,1)(1,3)
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
    //    (1.0,2)(0.057,4)(-0.058,5)
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
