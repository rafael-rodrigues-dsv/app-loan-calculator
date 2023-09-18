package br.com.example.domain.service.engine;

public class CalculatorEngineBuilder<T> {
    private CalculatorEngine<T> head;
    private CalculatorEngine<T> tail;

    public CalculatorEngineBuilder<T> add(CalculatorEngine<T> handler) {
        if (head == null) {
            head = tail = handler;
        } else {
            tail.setNextEngine(handler);
            tail = handler;
        }
        return this;
    }

    public CalculatorEngine<T> build() {
        return head;
    }
}
