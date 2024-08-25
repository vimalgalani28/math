package com.example.math.operator;

public enum Operator {
    ADD('+', 1),
    SUBTRACT('-', 0),
    MULTIPLY('*', 2),
    DIVIDE('/', 3);

    private char symbol;
    private Integer level;

    Operator(char c, int level) {
        this.symbol = c;
        this.level = level;
    }
    public char getSymbol() {
        return symbol;
    }
    public int getLevel() {
        return level;
    }
}
