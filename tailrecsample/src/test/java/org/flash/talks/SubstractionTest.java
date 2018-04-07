package org.flash.talks;

import org.junit.Test;

import static org.flash.talks.RecursiveCall.ret;
import static org.flash.talks.RecursiveCall.rec;
import static org.junit.Assert.assertEquals;

public class SubstractionTest {

    static int substract(int x, int y) {
        return y == 0 ?
                x :
                substract(x - 1, y - 1);
    }

    static RecursiveCall<Integer> safeSubstract(int x, int y) {
        return y == 0 ?
                ret(x) :
                rec(() -> safeSubstract(x - 1, y - 1));
    }

    @Test
    public void goodByeStack() {
        substract(1, 1_000_000);
    }

    @Test
    public void iWantTheDifferenceGodDammit() {
        Integer diff = safeSubstract(1, 1_000_000).eval();

        assertEquals(Integer.valueOf(-999_999), diff);
    }
}
