package utilities;
import com.example.javaapplication.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyCalculator implements ICalculator {
    @Override
    public void calculate(Result result) {
        ArrayList<Double> resultList = new ArrayList<>();
        for (int i = 0; i < result.getSampleList().size(); i++) {
            String expression = result.getSampleList().get(i);
            expression = expression.replaceAll("\\s+", "");

            String regex = "([\\d.]+)|(\\(-\\d(\\.\\d+)?\\))|([()+-/*^])";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(expression);
            MyStack<Double> numbers = new MyStack<>();
            MyStack<Character> operators = new MyStack<>();
            while (matcher.find()) {
                String token = matcher.group();

                if (token.matches("[\\d.]+")) {
                    numbers.push(Double.parseDouble(token));

                } else if (token.matches("\\(-\\d(\\.\\d+)?\\)")){
                    String str = "";
                    for(int j = 1; j < token.length()-1; j++)
                        str += token.charAt(j);
                    numbers.push(Double.parseDouble(str));
            }
                else if (token.matches("[()+-/^*]")) {
                    processOperator(token.charAt(0), numbers, operators);
                }
            }
            while (!operators.isEmpty()) {
                applyOperator(numbers, operators.pop());
            }
            resultList.add(numbers.pop());
        }
        result.setResultList(resultList);
    }

    private static void processOperator(char op, MyStack<Double> numbers, MyStack<Character> operators) {
        if (op == '(') {
            operators.push(op);
        } else if (op == ')') {
            while (operators.peek() != '(') {
                applyOperator(numbers, operators.pop());
            }
            operators.pop();
        } else {
            while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(op)) {
                applyOperator(numbers, operators.pop());
            }
            operators.push(op);
        }
    }

    private static void applyOperator(MyStack<Double> numbers, char op) {
        double right = numbers.pop();
        double left = numbers.pop();

        switch (op) {
            case '+':
                numbers.push(left + right);
                break;
            case '-':
                numbers.push(left - right);
                break;
            case '*':
                numbers.push(left * right);
                break;
            case '/':
                numbers.push(left / right);
                break;
            case '^':
                double num = 1.0;
                for(int i = 0; i < right; i++)
                    num *= left;
                numbers.push(num);
        }
    }

    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case'^':
                return 2;
            default:
                return 0;
        }
    }

    static class MyStack<T> {
        private final LinkedList<T> list = new LinkedList<>();

        void push(T item) {
            list.addLast(item);
        }

        T pop() {
            if (isEmpty()) {
                throw new IllegalArgumentException("Stack is empty");
            }
            return list.removeLast();
        }

        T peek() {
            if (isEmpty()) {
                throw new IllegalArgumentException("Stack is empty");
            }
            return list.getLast();
        }

        boolean isEmpty() {
            return list.isEmpty();
        }
    }
}