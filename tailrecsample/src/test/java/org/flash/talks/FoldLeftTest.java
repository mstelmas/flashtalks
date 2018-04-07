package org.flash.talks;

import org.junit.Test;

import java.util.List;
import java.util.function.Function;

import static org.flash.talks.ListUtils.head;
import static org.flash.talks.ListUtils.rep;
import static org.flash.talks.ListUtils.tail;
import static org.flash.talks.RecursiveCall.ret;
import static org.flash.talks.RecursiveCall.rec;
import static org.junit.Assert.assertEquals;


public class FoldLeftTest {

    static <T, U> U foldLeft(List<T> list, U identity, Function<U, Function<T, U>> f) {
        return list.isEmpty() ?
                identity :
                foldLeft(tail(list), f.apply(identity).apply(head(list)), f);
    }

    static <T, U> RecursiveCall<U> safeFoldLeft(List<T> list, U identify, Function<U, Function<T, U>> f) {
        return list.isEmpty() ?
                ret(identify) :
                rec(() -> safeFoldLeft(tail(list), f.apply(identify).apply(head(list)), f));
    }


    private final List<Integer> huuuuuuugeListOfOnes = rep(1, 1_000_000);

    @Test
    public void goodByeStack() {
        foldLeft(huuuuuuugeListOfOnes, 0, x -> y -> x + y);
    }

    @Test
    public void leftFoldingLikeABoss() {
        Integer sumOfMillionOnes = safeFoldLeft(huuuuuuugeListOfOnes, 0, x -> y -> x + y).eval();

        assertEquals(Integer.valueOf(1_000_000), sumOfMillionOnes);
    }
}
