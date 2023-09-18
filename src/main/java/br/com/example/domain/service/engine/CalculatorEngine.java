package br.com.example.domain.service.engine;

public interface CalculatorEngine<T> {
    void setNextEngine(CalculatorEngine<T> nextEngine);
    T calculate(T result);
}
