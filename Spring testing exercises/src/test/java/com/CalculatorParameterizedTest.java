package com.example.springtest.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 9: Parameterized Test with JUnit.
 * Runs the same test logic against multiple input/output pairs.
 */
class CalculatorParameterizedTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "-1, 1, 0",
            "0, 0, 0",
            "100, 200, 300"
    })
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculatorService.add(a, b));
    }
}
