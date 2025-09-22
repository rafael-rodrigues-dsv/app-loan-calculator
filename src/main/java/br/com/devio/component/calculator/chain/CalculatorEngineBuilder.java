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
        if (handler == null) {
            throw new IllegalArgumentException("Handler cannot be null");
        }
        
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
                private final CalculatorEngine<T> cachedHandler = handler.get();
                
                @Override
                public T calculate(T result) {
                    if (condition.test(result)) {
                        return cachedHandler.calculate(result);
                    }
                    return result;
                }

                @Override
                public T calculate(T dataBase, T currentData) {
                    if (condition.test(currentData)) {
                        return cachedHandler.calculate(dataBase, currentData);
                    }
                    return currentData;
                }

                @Override
                public T calculate(T dataBase, T beforeData, T currentData) {
                    if (condition.test(currentData)) {
                        return cachedHandler.calculate(dataBase, beforeData, currentData);
                    }
                    return currentData;
                }
            });
        }
        return this;
    }

    public CalculatorEngine<T> build() {
        return new ChainCalculatorEngine();
    }

    private class ChainCalculatorEngine extends CalculatorEngine<T> {
        @Override
        public T calculate(T result) {
            return traverseChain((current, data) -> current.handler.calculate(data), result);
        }

        @Override
        public T calculate(T dataBase, T currentData) {
            return traverseChain((current, data) -> current.handler.calculate(dataBase, data), currentData);
        }

        @Override
        public T calculate(T dataBase, T beforeData, T currentData) {
            return traverseChain((current, data) -> current.handler.calculate(dataBase, beforeData, data), currentData);
        }

        private T traverseChain(ChainFunction<T> function, T data) {
            Node<T> current = head;
            while (current != null) {
                data = function.apply(current, data);
                current = current.next;
            }
            return data;
        }
    }

    @FunctionalInterface
    private interface ChainFunction<T> {
        T apply(Node<T> node, T data);
    }
}