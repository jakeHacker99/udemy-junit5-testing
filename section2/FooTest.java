package com.company.section2;

import static org.junit.jupiter.api.Assertions.*;
class FooTest {

    @org.junit.jupiter.api.Test
    void foo() {
        Foo foo = new Foo();
        String result = foo.getFoo();
        assertEquals("fooBar", result);
    }

}