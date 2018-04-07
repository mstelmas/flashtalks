package org.flash.talks;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListUtils {

    static <T> List<T> list(T... t) {
        return Arrays.asList(t);
    }

    static <T> List<T> rep(T t, int count) {
        return Collections.nCopies(count, t);
    }

    static <T> T head(List<T> list) {
        return list.get(0);
    }

    static <T> List<T> tail(List<T> list) {
        return list.subList(1, list.size());
    }

    private final List<Integer> funnyList = list(1, 2, 3, 4);

    @Test
    public void shouldReturnHeadOfList() {
        assertEquals(Integer.valueOf(1), head(funnyList));
    }

    @Test
    public void shouldReturnTailfOfList() {
        assertEquals(list(2, 3, 4), tail(funnyList));
    }
}
