package com.example.javaapplication;

import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Content")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Content {
    @XmlElement(name = "MathExpression")
    private ArrayList<MathExpression> mathExpressions = new ArrayList<>();
}
