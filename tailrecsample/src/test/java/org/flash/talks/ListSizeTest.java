package org.flash.talks;

import org.junit.Test;

import java.util.List;

import static org.flash.talks.ListUtils.rep;
import static org.flash.talks.ListUtils.tail;
import static org.flash.talks.RecursiveCall.ret;
import static org.flash.talks.RecursiveCall.rec;
import static org.junit.Assert.assertEquals;

public class ListSizeTest {

    static <T> long sizeOfList(List<T> list, long acc) {
        return list.isEmpty() ?
                acc :
                sizeOfList(tail(list), 1 + acc);
    }

    static <T> RecursiveCall<Long> safeSizeOfList(List<T> list, long acc) {
        return list.isEmpty() ?
                ret(acc) :
                rec(() -> safeSizeOfList(tail(list), 1 + acc));
    }

    private final List<Integer> listOfMillionElements = rep(1, 1_000_000);

    @Test
    public void goodByeStack() {
        sizeOfList(listOfMillionElements, 0);
    }

    @Test
    public void tailRecursiveCallsFTW() {
        long millionElementsListSize = safeSizeOfList(listOfMillionElements, 0).eval();

        assertEquals(1_000_000, millionElementsListSize);
    }
}
