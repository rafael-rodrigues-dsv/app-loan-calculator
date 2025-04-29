package br.com.example.domain.calculator.component.chain;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.component.chain.CalculatorEngineBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorEngineBuilderTest {

    private CalculatorEngineBuilder<String> builder;

    @BeforeEach
    public void setUp() {
        builder = new CalculatorEngineBuilder<>();
    }

    @Test
    public void testEmptyChainSingleCalculate() {
        CalculatorEngine<String> engine = builder.build();
        assertEquals("Test", engine.calculate("Test"));
    }

    @Test
    public void testEmptyChainCalculateWithTwoParameters() {
        CalculatorEngine<String> engine = builder.build();
        assertEquals("Test", engine.calculate("Base", "Test"));
    }

    @Test
    public void testEmptyChainCalculateWithThreeParameters() {
        CalculatorEngine<String> engine = builder.build();
        assertEquals("Test", engine.calculate("Base", "Before", "Test"));
    }

    @Test
    public void testSingleCalculate() {
        builder.add(new MockCalculator());
        CalculatorEngine<String> engine = builder.build();
        assertEquals("Test + Single", engine.calculate("Test"));
    }

    @Test
    public void testDoubleCalculate() {
        builder.add(new MockCalculator()).add(new MockCalculator());
        CalculatorEngine<String> engine = builder.build();
        assertEquals("Test + Single + Single", engine.calculate("Test"));
    }

    @Test
    public void testCalculateWithTwoParameters() {
        builder.add(new MockCalculator());
        CalculatorEngine<String> engine = builder.build();
        assertEquals("Base + Test + Double", engine.calculate("Base", "Test"));
    }

    @Test
    public void testCalculateWithThreeParameters() {
        builder.add(new MockCalculator());
        CalculatorEngine<String> engine = builder.build();
        assertEquals("Base + Before + Test + Triple", engine.calculate("Base", "Before", "Test"));
    }

    private static class MockCalculator extends CalculatorEngine<String> {
        @Override
        public String calculate(String result) {
            return result + " + Single";
        }

        @Override
        public String calculate(String dataBase, String currentData) {
            return dataBase + " + " + currentData + " + Double";
        }

        @Override
        public String calculate(String dataBase, String beforeData, String currentData) {
            return dataBase + " + " + beforeData + " + " + currentData + " + Triple";
        }
    }
}