package org.flash.talks;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

public abstract class RecursiveCall<T> {
    public abstract T eval();
    public abstract boolean shouldRecur();

    @RequiredArgsConstructor
    public static class Return<T> extends RecursiveCall<T> {

        private final T result;

        @Override
        public T eval() {
            return result;
        }

        @Override
        public boolean shouldRecur() {
            return false;
        }
    }

    @RequiredArgsConstructor
    public static class Recur<T> extends RecursiveCall<T> {

        private final Supplier<RecursiveCall<T>> result;

        @Override
        public T eval() {
            RecursiveCall<T> recursiveCall = this;

            while (recursiveCall.shouldRecur())
                recursiveCall = ((Recur<T>) recursiveCall).result.get();

            return recursiveCall.eval();
        }

        @Override
        public boolean shouldRecur() {
            return true;
        }
    }

    static <T> Return<T> ret(T result) {
        return new Return<>(result);
    }

    static <T> Recur<T> rec(Supplier<RecursiveCall<T>> s) {
        return new Recur<>(s);
    }
}
