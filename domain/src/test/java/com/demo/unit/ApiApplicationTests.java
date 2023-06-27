package com.demo.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiApplicationTests {

    @Test
    void Test() {
        // 준비
        double first = 10l;
        double second = 20l;

        // 실행
        double result = Calculator.add(first, second);

        // 검증
        Assertions.assertEquals(30, result);
    }

    public static class Calculator {
        public static double add(double value1, double value2) {
            return value1 + value2;
        }
    }
}
