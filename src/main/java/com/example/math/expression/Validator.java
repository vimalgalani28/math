package com.example.math.expression;

import com.example.math.exception.InvalidInputException;
import com.example.math.operator.OperatorHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class Validator {
    private Boolean expressionStartsOrEndsWithOperator(String exp) {
        return Objects.nonNull(OperatorHelper.getOperator(exp.charAt(0))) ||
            Objects.nonNull(OperatorHelper.getOperator(exp.charAt(exp.length() - 1))) ;
    }
    public void validateExpression(String exp) {
        if (StringUtils.isEmpty(exp)) {
            throw new InvalidInputException("Input Cannot be Null or Empty");
        }
        if (expressionStartsOrEndsWithOperator(exp)) {
            throw new InvalidInputException("Input Cannot Start or end with operator");
        }
    }
}
