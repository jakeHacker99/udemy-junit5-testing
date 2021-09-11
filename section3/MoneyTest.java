package com.company.section3;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MoneyTest {
    @Test
    void testMultiplicationDollar() {
        Money five = Money.dollar(5);
        assertEquals( Money.dollar(10), five.times(2));
        assertNotEquals( Money.dollar(15),  five.times(-3));
        assertNotEquals( Money.dollar(10), five.times(11
        ));
    }

    @Test
    void testEqualityDollar() {
        Money five= Money.franc(5);
        assertEquals( Money.dollar(5),  Money.dollar(5));
        assertNotEquals(Money.dollar(5),  Money.dollar(8));
        assertNotEquals( Money.franc(10),  Money.dollar(10));
    }

    @Test
    void testMultiplicationFranc() {
        Franc five = new Franc(5, "CHF");
        assertEquals( Money.franc(10), five.times(2));;
        assertEquals( Money.franc(15), five.times(3));
    }

    @Test
    void testEqualityDollarFranc() {
        assertEquals( Money.franc(5),  Money.franc(5));
        assertNotEquals( Money.franc(5),  Money.franc(8));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());

    }
}
