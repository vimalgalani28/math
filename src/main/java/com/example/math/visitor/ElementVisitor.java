package com.example.math.visitor;

import com.example.math.element.Element;
import com.example.math.element.NumeralElement;
import com.example.math.element.OperatorElement;

import java.math.BigDecimal;
import java.util.Stack;

public interface ElementVisitor {
    public void visit(NumeralElement ele, Stack<BigDecimal> st);
    public void visit(OperatorElement ele, Stack<BigDecimal> st);
}
