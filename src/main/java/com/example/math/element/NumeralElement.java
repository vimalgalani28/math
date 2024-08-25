package com.example.math.element;

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
public class NumeralElement implements Element{
    private BigDecimal number;

    @Override
    public String getString() {
        return number.toString();
    }

    @Override
    public void accept(ElementVisitor visitor, Stack<BigDecimal> st) {
        visitor.visit(this, st);
    }
}
