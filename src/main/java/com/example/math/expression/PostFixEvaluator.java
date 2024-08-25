package com.example.math.expression;

import com.example.math.element.Element;
import com.example.math.element.NumeralElement;
import com.example.math.element.NumeralElementBuilder;
import com.example.math.element.OperatorElement;
import com.example.math.exception.InvalidInputException;
import com.example.math.operator.Operator;
import com.example.math.operator.OperatorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Stack;

@Component
public class PostFixEvaluator {

    private static final Logger LOG = LoggerFactory.getLogger(PostFixEvaluator.class);
    private void handlePush(NumeralElement numeralElement, OperatorElement operatorElement, Stack<Element> postFix) {
        if (Objects.isNull(operatorElement) && postFix.isEmpty()) {
            postFix.push(numeralElement);
        } else  {
            if (postFix.isEmpty()) throw new InvalidInputException("");
            Stack<Element> temp = new Stack<>();
            while (
                !postFix.isEmpty() &&
                    postFix.peek() instanceof OperatorElement ele &&
                    ele.getOperator().getLevel() < operatorElement.getOperator().getLevel()
            ) {
                temp.push(postFix.peek());
                postFix.pop();
            }
            postFix.push(numeralElement);
            postFix.push(operatorElement);
            while (!temp.isEmpty()) {
                postFix.push(temp.peek());
                temp.pop();
            }
        }
    }
    public Stack<Element> evaluate(String exp) {
        try {
            Stack<Element> postFix = new Stack<>();
            OperatorElement operatorElement = null;

            NumeralElementBuilder numeralElementBuilder = new NumeralElementBuilder();
            for (int i = 0; i < exp.length(); i++) {
                char ch = exp.charAt(i);
                Operator operator = OperatorHelper.getOperator(ch);
                if (Objects.nonNull(operator)) {
                    handlePush(numeralElementBuilder.get(), operatorElement, postFix);
                    operatorElement = new OperatorElement(operator);
                    numeralElementBuilder = new NumeralElementBuilder();
                } else if (ch == '.') {
                    numeralElementBuilder.fraction();
                } else {
                    numeralElementBuilder.addDigit(ch);
                }
            }
            handlePush(numeralElementBuilder.get(), operatorElement, postFix);
            return postFix;
        } catch (InvalidInputException ex) {
            LOG.error("Invalid Input while Postfix Evaluation");
            throw ex;
        } catch (Exception ex) {
            LOG.error("Unknown Exception while Postfix Evaluation");
            throw ex;
        }
    }

    public void printPostFix(String exp, Stack<Element> postFix) {
        Stack<String> reverseStack = new Stack<>();
        while (!postFix.isEmpty()) {
            reverseStack.push(postFix.peek().getString());
            postFix.pop();
        }
        StringBuilder ans = new StringBuilder();
        while (!reverseStack.isEmpty()) {
            ans.append(reverseStack.peek());
            ans.append(" ");
            reverseStack.pop();
        }
        LOG.info("Expression: {} Post Fix: {}", exp, ans.toString());
    }
}
