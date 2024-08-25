package com.example.math.expression;

import com.example.math.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PostFixEvaluatorTest {
    @Autowired
    private PostFixEvaluator postFixEvaluator;


    @Test
    public void testEvaluate_withInputHavingOneBinaryExp_returnsValid() {
        postFixEvaluator.evaluate("1+2");
    }

    @Test
    public void testEvaluate_withInputHavingAdditionAndSubtraction_returnsValid() {
        // a+b-c-d -> ab+c-d-
        postFixEvaluator.evaluate("1+2-3-4");
    }
    @Test
    public void testEvaluate_withInputHavingAdditionAndSubtraction1_returnsValid() {
        // a+b-c+d -> ab+cd+-
        postFixEvaluator.evaluate("1+2-3+4");
    }
    @Test
    public void testEvaluate_withInputHavingAdditionAndSubtraction2_returnsValid() {
        // a+b-c+d+e -> ab+cd+e+-
        postFixEvaluator.evaluate("1+2-3+4+5");
    }
    @Test
    public void testEvaluate_withInputHavingAdditionAndMultiplication_returnsValid() {
        // ab+c- Ex: abc*+
        postFixEvaluator.evaluate("1+2*3");
    }
    @Test
    public void testEvaluate_withInputHavingAdditionSubtractionAndMultiplication_returnsValid() {
        // a + b - c * d -> ab+cd*-
        postFixEvaluator.evaluate("1+2-3*4");
    }
    @Test
    public void testEvaluate_withInputHavingAdditionSubtractionAndMultiplication1_returnsValid() {
        // a * b - c + d -> ab*cd+-
        postFixEvaluator.evaluate("1*2-3+4");
    }
    @Test
    public void testEvaluate_withInputHavingRepeatativeOperator_returnsValid() {
        // a/b/c/d -> ab/c/d/
        postFixEvaluator.evaluate("1/2/3/4");
    }
    @Test
    public void testEvaluate_withMultiDigitInput_returnsValid() {
        postFixEvaluator.evaluate("10-2+300");
    }
    @Test
    public void testEvaluate_withFractionInput_returnsValid() {
        postFixEvaluator.evaluate("10.23-2.46");
    }
}
