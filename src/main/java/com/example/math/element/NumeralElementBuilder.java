package com.example.math.element;

import com.example.math.exception.InvalidInputException;

import java.math.BigDecimal;

public class NumeralElementBuilder {
    private NumeralElement numeralElement;
    private Integer fraction;

    public NumeralElementBuilder() {
        this.numeralElement = new NumeralElement(BigDecimal.valueOf(0));
        this.fraction = 1;
    }

    public NumeralElementBuilder addDigit(char ch) {
        if (fraction > 100l) {
            throw new InvalidInputException("Max 2 Floating point precision is allowed");
        }
        if (fraction.equals(1)) {
            this.numeralElement.setNumber(
                this.numeralElement
                    .getNumber()
                    .multiply(BigDecimal.valueOf(10))
                    .add(BigDecimal.valueOf(Character.getNumericValue(ch)))
            );
        } else  {
            this.numeralElement.setNumber(
                this.numeralElement
                    .getNumber()
                    .add(
                        BigDecimal.valueOf(Character.getNumericValue(ch))
                            .divide(BigDecimal.valueOf(fraction))
                    )
            );
            fraction *= 10;
        }
        return this;
    }

    public NumeralElementBuilder fraction() {
        this.fraction *= 10;
        return this;
    }

    public NumeralElement get() {
        return numeralElement;
    }
}
