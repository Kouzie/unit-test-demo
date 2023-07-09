package com.demo.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ApiApplicationTests {

    @ParameterizedTest
    @CsvSource(value = {
            "10,20,30",
            "3,5,8"
    })
    void Test(double first, double second, double expect) {

        // 실행
        double result = Calculator.add(first, second);

        // 검증
        Assertions.assertEquals(expect, result);
    }

    public static class Calculator {
        public static double add(double value1, double value2) {
            return value1 + value2;
        }
    }
}
