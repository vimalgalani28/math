package com.example.math.element;

import com.example.math.operator.Operator;
import com.example.math.visitor.ElementVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Stack;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperatorElement implements Element{
    private Operator operator;

    @Override
    public String getString() {
        return "" + operator.getSymbol();
    }

    @Override
    public void accept(ElementVisitor visitor, Stack<BigDecimal> st) {
        visitor.visit(this, st);
    }
}
