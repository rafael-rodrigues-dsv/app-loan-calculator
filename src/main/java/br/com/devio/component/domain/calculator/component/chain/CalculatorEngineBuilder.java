package br.com.devio.component.domain.calculator.component.chain;

public class CalculatorEngineBuilder<T> {
    private static class Node<T> {
        CalculatorEngine<T> handler;
        Node<T> next;
    }

    private Node<T> head;
    private Node<T> tail;

    public CalculatorEngineBuilder<T> add(CalculatorEngine<T> handler) {
        Node<T> newNode = new Node<>();
        newNode.handler = handler;

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        return this;
    }

    public CalculatorEngine<T> build() {
        return new CalculatorEngine<T>() {
            @Override
            public T calculate(T result) {
                Node<T> current = head;
                while (current != null) {
                    result = current.handler.calculate(result);
                    current = current.next;
                }
                return result;
            }

            @Override
            public T calculate(T dataBase, T currentData) {
                Node<T> current = head;
                while (current != null) {
                    currentData = current.handler.calculate(dataBase, currentData);
                    current = current.next;
                }
                return currentData;
            }

            @Override
            public T calculate(T dataBase, T beforeData, T currentData) {
                Node<T> current = head;
                while (current != null) {
                    currentData = current.handler.calculate(dataBase, beforeData, currentData);
                    current = current.next;
                }
                return currentData;
            }
        };
    }
}
