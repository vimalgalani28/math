package com.example.math.visitor;

import com.example.math.element.Element;
import com.example.math.element.NumeralElement;
import com.example.math.element.OperatorElement;
import com.example.math.operator.Operator;

import java.math.BigDecimal;
import java.util.Stack;

public class ElementEvaluatorVisitor implements ElementVisitor {
    @Override
    public void visit(NumeralElement ele, Stack<BigDecimal> st) {
        st.push(ele.getNumber());
    }

    @Override
    public void visit(OperatorElement ele, Stack<BigDecimal> st) {
        BigDecimal op2 = st.peek();
        st.pop();
        BigDecimal op1 = st.peek();
        st.pop();
        st.push(evaluateExpression(op1, op2, ele.getOperator()));
    }

    private BigDecimal evaluateExpression(BigDecimal op1, BigDecimal op2, Operator op) {
        if (op.equals(Operator.ADD)) {
            return op1.add(op2);
        } else if (op.equals(Operator.SUBTRACT)) {
            return op1.subtract(op2);
        } else if (op.equals(Operator.MULTIPLY)) {
            return op1.multiply(op2);
        }
        return op1.divide(op2, 2, BigDecimal.ROUND_HALF_EVEN);
    }
}
