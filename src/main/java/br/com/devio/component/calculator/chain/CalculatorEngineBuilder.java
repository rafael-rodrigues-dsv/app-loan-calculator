package br.com.devio.component.calculator.chain;

import java.util.function.Predicate;
import java.util.function.Supplier;

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

    @SafeVarargs
    public final CalculatorEngineBuilder<T> addIf(Predicate<T> condition, Supplier<CalculatorEngine<T>>... handlers) {
        for (Supplier<CalculatorEngine<T>> handler : handlers) {
            add(new CalculatorEngine<T>() {
                @Override
                public T calculate(T result) {
                    if (condition.test(result)) {
                        return handler.get().calculate(result);
                    }
                    return result;
                }

                @Override
                public T calculate(T dataBase, T currentData) {
                    if (condition.test(currentData)) {
                        return handler.get().calculate(dataBase, currentData);
                    }
                    return currentData;
                }

                @Override
                public T calculate(T dataBase, T beforeData, T currentData) {
                    if (condition.test(currentData)) {
                        return handler.get().calculate(dataBase, beforeData, currentData);
                    }
                    return currentData;
                }
            });
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