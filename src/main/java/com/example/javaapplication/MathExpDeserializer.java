package com.example.javaapplication;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class MathExpDeserializer extends StdDeserializer<MathExp> {
    public MathExpDeserializer(Class<?> vc) {
        super(vc);
    }

    public MathExpDeserializer() {
        this(null);
    }
//    JsonNode integersNode = node.get("integers");
//    ArrayList<ImmutablePair<Integer, Integer>> integers = new ArrayList<>();
//        for (JsonNode varNode : integersNode) {
//        String left = varNode.fieldNames().next();
//        integers.add(new ImmutablePair<>(Integer.parseInt(left), varNode.get(left).asInt()));
//    }
    //@Override
    public MathExp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        MathExp exp = new MathExp();
        ArrayList<OneMathExp> math_exp = new ArrayList<>();
            OneMathExp oneMathExp = new OneMathExp();
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
                JsonNode expressionNode_ = node.get("expression");
                JsonNode variablesNode_ = node.get("variables");
                JsonNode typesNode_ = node.get("types");
                JsonNode integersNode_ = node.get("integers");
                JsonNode doublesNode_ = node.get("doubles");
                    String expression_ = expressionNode_.asText();
                    oneMathExp.setExpression(expression_);
                    ArrayList<Character> variables_ = new ArrayList<>();
                    for (JsonNode varNode : variablesNode_) {
                        String next_node = varNode.asText();
                        variables_.add(next_node.charAt(0));
                    }
                    oneMathExp.setVariables(variables_);
                    ArrayList<Character> types_ = new ArrayList<>();
                    for (JsonNode varNode : typesNode_) {
                        String next_node = varNode.asText();
                        types_.add(next_node.charAt(0));
                    }
                    oneMathExp.setTypes(types_);
                    ArrayList<Pair<Integer, Integer>> integers_ = new ArrayList<>();
                    for (JsonNode varNode : integersNode_) {
                        String left = varNode.fieldNames().next();
                        integers_.add(new Pair<>(Integer.parseInt(left), varNode.get(left).asInt()));
                    }
                    oneMathExp.setIntegers(integers_);
                    ArrayList<Pair<Double, Integer>> doubles_ = new ArrayList<>();
                    for (JsonNode varNode : doublesNode_) {
                        String left = varNode.fieldNames().next();
                        doubles_.add(new Pair<>(Double.parseDouble(left), varNode.get(left).asInt()));
                    }
                    oneMathExp.setDoubles(doubles_);
                    math_exp.add(oneMathExp);
        exp.setMathExps(math_exp);
        return exp;
    }
}
