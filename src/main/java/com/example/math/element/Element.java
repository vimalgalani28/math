package com.example.math.element;

import com.example.math.visitor.ElementVisitor;

import java.math.BigDecimal;
import java.util.Stack;

public interface Element {
    public String getString();
    public void accept(ElementVisitor visitor, Stack<BigDecimal> st);
}
