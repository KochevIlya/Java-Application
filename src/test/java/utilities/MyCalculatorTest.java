package utilities;

import com.example.javaapplication.Calculator;
import com.example.javaapplication.Result;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorTest {

    @Test
    void testCalculateWithValidExpressions() {
        Result result = new Result();
        result.setSampleList(new ArrayList<>( Arrays.asList("2 * (3 ^ 4) - 5", "8 / (2 + 2) * 3 - 1", "10 * 2 - (4 + 2) / 2")));

        Calculator calculator = new Calculator(true);
        calculator.calculate(result);

        ArrayList<Double> expectedResultList = new ArrayList<>(Arrays.asList(157.0, 5.0, 17.0));
        assertEquals(expectedResultList, result.getResultList());
    }

    @Test
    void testCalculateWithInvalidExpression() {
        Result result = new Result();
        result.setSampleList(new ArrayList<String>(Arrays.asList("2 * (3 + 4) / 5", "invalid_expression", "10 * 2 - (4 + 2) / 2")));

        Calculator calculator = new Calculator(true);

        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(result));
    }

    @Test
    void testCalculateWithEmptySampleList() {
        Result result = new Result();
        result.setSampleList(new ArrayList<>());

        Calculator calculator = new Calculator(true);

        calculator.calculate(result);
        assertEquals(new ArrayList<>(), result.getResultList());
    }
}