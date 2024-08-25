package com.example.math.operand;

import com.example.math.operator.Operator;

public class OperandBuilder {
    private Long number;
    public OperandBuilder() {
        this.number = 0l;
    }
    public OperandBuilder addDigit(char digit) {
        this.number = (this.number * 10) + Character.getNumericValue(digit);
        return this;
    }
    public Long get() {
        return number;
    }
}
