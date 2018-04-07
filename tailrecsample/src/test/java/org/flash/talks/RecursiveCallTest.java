package org.flash.talks;

import org.junit.Test;

import static org.flash.talks.RecursiveCall.ret;
import static org.flash.talks.RecursiveCall.rec;
import static org.junit.Assert.assertEquals;

public class RecursiveCallTest {

    static RecursiveCall<Long> factorial(long n) {
        return factorial(n, 1);
    }

    static RecursiveCall<Long> factorial(long n, long acc) {
        return n <= 1 ?
                ret(acc) :
                rec(() -> factorial(n - 1, n * acc));
    }

    @Test
    public void factorialOf1() {
        /* Return(result = 1) */
        RecursiveCall<Long> factorialOf1 = factorial(1);

        assertEquals(Long.valueOf(1), factorialOf1.eval());
    }

    @Test
    public void factorialOf2() {
        /* Recur(result = () -> factorial(1, 2)) */
        RecursiveCall<Long> factorialOf2 = factorial(2);

        /* factorial(1, 2) => Return(result = 2) */
        assertEquals(Long.valueOf(2), factorialOf2.eval());
    }

    @Test
    public void factorialOf3() {
        /* Recur(result = () -> factorial(2, 3)) */
        RecursiveCall<Long> factorialOf3 = factorial(3);

        /*
          factorial(2, 3) => Recur(result = () -> factorial(1, 6))
          factorial(1, 6) => Return(result = 6)
         */
        assertEquals(Long.valueOf(6), factorialOf3.eval());
    }
}
