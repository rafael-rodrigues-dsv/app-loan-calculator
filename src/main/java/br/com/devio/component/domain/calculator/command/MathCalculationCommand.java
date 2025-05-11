package br.com.devio.component.domain.calculator.command;

public interface MathCalculationCommand<T, R> {
    R execute(T input);
}