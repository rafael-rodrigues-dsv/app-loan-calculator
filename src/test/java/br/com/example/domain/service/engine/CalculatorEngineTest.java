package br.com.example.domain.service.engine;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorEngineTest {

    private MockCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new MockCalculator();
    }

    @Test
    public void testSingleCalculate() {
        String result = calculator.calculate("Test");
        assertEquals("Test", result);
    }

    @Test
    public void testCalculateWithTwoParameters() {
        String result = calculator.calculate("Base", "Test");
        assertEquals("Test", result);
    }

    @Test
    public void testCalculateWithThreeParameters() {
        String result = calculator.calculate("Base", "Before", "Test");
        assertEquals("Test", result);
    }

    private static class MockCalculator extends CalculatorEngine<String> {
        // This mock implementation uses the default behavior provided by CalculatorEngine
    }
}