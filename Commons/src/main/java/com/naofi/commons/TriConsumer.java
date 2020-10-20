package com.naofi.commons;

@FunctionalInterface
public interface TriConsumer<U, V, Z> {
    void accept(U u, V v, Z z);
}
