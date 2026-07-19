package com.example.springtest.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void testAdd() {
        int result = calculatorService.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    void testAddWithNegativeNumbers() {
        int result = calculatorService.add(-2, -3);
        assertEquals(-5, result);
    }
}
