package com.example.math.expression;

import com.example.math.element.Element;
import com.example.math.visitor.ElementEvaluatorVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Stack;

@Component
public class ExpressionEvaluator {
    private static final Logger LOG = LoggerFactory.getLogger(ExpressionEvaluator.class);
    @Autowired
    private Validator validator;
    @Autowired
    private PostFixEvaluator postFixEvaluator;

    public BigDecimal evaluate(String exp) {
        validator.validateExpression(exp);
        Stack<Element> postFix = postFixEvaluator.evaluate(exp);
        Stack<BigDecimal> ans = new Stack<>();

        for (int i = 0; i < postFix.size(); i++) {
            Element element = postFix.get(i);
            ElementEvaluatorVisitor elementEvaluatorVisitor = new ElementEvaluatorVisitor();
            element.accept(elementEvaluatorVisitor, ans);
        }

        postFixEvaluator.printPostFix(exp, postFix);
        LOG.info("Answer: {}", ans.peek());
        return ans.peek();
    }
}
