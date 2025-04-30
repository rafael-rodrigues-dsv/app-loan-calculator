package br.com.devio.component.domain.calculator.component.chain;

public abstract class CalculatorEngine<T> {
    public T calculate(T result) {
        return result;
    }

    public T calculate(T dataBase, T currentData) {
        return currentData;
    }

    public T calculate(T dataBase, T beforeData, T currentData) {
        return currentData;
    }
}
