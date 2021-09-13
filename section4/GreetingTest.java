package com.company.section4;

import org.junit.jupiter.api.*;

class GreetingTest {

    private Greeting greeting;


    @BeforeAll
    static void beforeAll() {
        System.out.println("I run 1st");

    }

    @BeforeEach
    void setUp() {
        System.out.println("### This runs before each ###");
        greeting = new Greeting();
    }

    @Test
    void helloWorld() {
        System.out.println(greeting.helloWorld());
    }

    @Test
    void helloName() {
        System.out.println(greeting.helloName("jake"));
    }@Test
    void helloBawoer() {
        System.out.println(greeting.helloName(" Bawoeer dräng"));
    }

    @AfterEach
    void tearDown() {
        System.out.println("### This runs after  each ###");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("I run last");
    }
}
