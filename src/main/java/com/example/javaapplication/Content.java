package com.example.javaapplication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Content")
@XmlAccessorType(XmlAccessType.FIELD)
public class Content {
    public ArrayList<MathExpression> getMathExpressions() {
        return mathExpressions;
    }

    public void setMathExpressions(ArrayList<MathExpression> mathExpressions) {
        this.mathExpressions = mathExpressions;
    }

    @XmlElement(name = "MathExpression")
    private ArrayList<MathExpression> mathExpressions = new ArrayList<>();
}
