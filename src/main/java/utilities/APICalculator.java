package utilities;
import com.example.javaapplication.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.ArrayList;

public class APICalculator implements ICalculator{
    @Override
    public void calculate(Result result)
    {
        ArrayList<Double> resultList = new ArrayList<>();
        for(int i = 0; i < result.getSampleList().size(); i++) {
            String expression = result.getSampleList().get(i);
            try {
                Expression exp = new ExpressionBuilder(expression).build();
                resultList.add(exp.evaluate());
            } catch (Exception e) {
                System.out.println("Error evaluating expression: " + e.getMessage());
                System.out.println("Error sample: " + expression);

                throw new IllegalArgumentException();
            }
        }
        result.setResultList(resultList);
    }

}
