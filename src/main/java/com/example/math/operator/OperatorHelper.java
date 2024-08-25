package com.example.math.operator;

public class OperatorHelper {
    public static Operator getOperator(char ch) {
        for (Operator op : Operator.values()) {
            if (op.getSymbol() == ch) {
                return op;
            }
        }
        return null;
    }

    public static Boolean isOperator(char ch) {
        for (Operator op : Operator.values()) {
            if (op.getSymbol() == ch) {
                return true;
            }
        }
        return false;
    }
}
