package br.com.devio.entrypoint.command;

public interface ServiceCommand<T, R> {
    default R execute(T input) {
        throw new UnsupportedOperationException("Operação não suportada.");
    }
}
