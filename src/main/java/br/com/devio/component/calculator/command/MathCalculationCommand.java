package br.com.devio.component.calculator.command;

public interface MathCalculationCommand<T, R> {
    R execute(T input);
}