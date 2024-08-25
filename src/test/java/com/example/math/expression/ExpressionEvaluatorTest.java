package com.example.math.expression;

import com.example.math.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class ExpressionEvaluatorTest {
    @Autowired
    private ExpressionEvaluator expressionEvaluator;

    @Test
    public void testEvaluate_withNullInput_throwsEx() {
        assertThrows(InvalidInputException.class, () -> expressionEvaluator.evaluate(null));
    }

    @Test
    public void testEvaluate_withEmptyInput_throwsEx() {
        assertThrows(InvalidInputException.class, () -> expressionEvaluator.evaluate(""));
    }

    @Test
    public void testEvaluate_withExpStartingWithOperator_throwsEx() {
        assertThrows(InvalidInputException.class, () -> expressionEvaluator.evaluate("+123"));
    }

    @Test
    public void testEvaluate_withExpEndingWithOperator_throwsEx() {
        assertThrows(InvalidInputException.class, () -> expressionEvaluator.evaluate("123+"));
    }
    @Test
    public void testEvaluate_withExpHavingMoreThanTwoFloatPointsDecimal_throwsEx() {
        assertThrows(InvalidInputException.class, () -> expressionEvaluator.evaluate("1.234"));
    }

    @Test
    public void testEvaluate_withSingleNumber_returnsValid() {
        BigDecimal ans = expressionEvaluator.evaluate("1");
        assertEquals(BigDecimal.valueOf(1), ans);
    }

    @Test
    public void testEvaluate_withInputHavingOneBinaryExp_returnsValid() {
        BigDecimal ans = expressionEvaluator.evaluate("1+2");
        assertEquals(BigDecimal.valueOf(3), ans);
    }

    @Test
    public void testEvaluate_withInputHavingAdditionAndSubtraction_returnsValid() {
        // a+b-c-d -> ab+c-d-
        BigDecimal ans = expressionEvaluator.evaluate("1+2-3-4");
        assertEquals(BigDecimal.valueOf(-4), ans);
    }
    @Test
    public void testEvaluate_withInputHavingAdditionAndSubtraction1_returnsValid() {
        // a+b-c+d -> ab+cd+-
        BigDecimal ans = expressionEvaluator.evaluate("1+2-3+4");
        assertEquals(BigDecimal.valueOf(-4), ans);
    }
    @Test
    public void testEvaluate_withInputHavingAdditionAndSubtraction2_returnsValid() {
        // a+b-c+d+e -> ab+cd+e+-
        BigDecimal ans = expressionEvaluator.evaluate("1+2-3+4+5");
        assertEquals(BigDecimal.valueOf(-9), ans);
    }
    @Test
    public void testEvaluate_withInputHavingAdditionAndMultiplication_returnsValid() {
        // ab+c- Ex: abc*+
        BigDecimal ans = expressionEvaluator.evaluate("1+2*3");
        assertEquals(BigDecimal.valueOf(7), ans);
    }
    @Test
    public void testEvaluate_withInputHavingAdditionSubtractionAndMultiplication_returnsValid() {
        // a + b - c * d -> ab+cd*-
        BigDecimal ans = expressionEvaluator.evaluate("1+2-3*4");
        assertEquals(BigDecimal.valueOf(-9), ans);
    }
    @Test
    public void testEvaluate_withInputHavingAdditionSubtractionAndMultiplication1_returnsValid() {
        // a * b - c + d -> ab*cd+-
        BigDecimal ans = expressionEvaluator.evaluate("1*2-3+4");
        assertEquals(BigDecimal.valueOf(-5), ans);
    }
    @Test
    public void testEvaluate_withInputHavingRepeatativeOperator_returnsValid() {
        // a/b/c/d -> ab/c/d/
        BigDecimal ans = expressionEvaluator.evaluate("1/2/3/4");
        assertEquals(BigDecimal.valueOf(0.04), ans);
    }
    @Test
    public void testEvaluate_withMultiDigitInput_returnsValid() {
        BigDecimal ans = expressionEvaluator.evaluate("10-2+300");
        assertEquals(BigDecimal.valueOf(-292), ans);
    }
    @Test
    public void testEvaluate_withFractionInput_returnsValid() {
        BigDecimal ans = expressionEvaluator.evaluate("10.23-2.46");
        assertEquals(BigDecimal.valueOf(7.77), ans);
    }
}
