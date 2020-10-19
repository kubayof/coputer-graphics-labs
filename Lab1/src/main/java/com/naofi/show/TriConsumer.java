package com.naofi.show;

@FunctionalInterface
public interface TriConsumer<U, V, Z> {
    void accept(U u, V v, Z z);
}
