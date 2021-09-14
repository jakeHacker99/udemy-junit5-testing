package com.company.section5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {


    IndexController controller;


    @BeforeEach
    void setUp(){
        controller = new IndexController();
    }

    @Test
    @DisplayName("Test index")
    void index() {

        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "will fail hardly");
        assertEquals("index", controller.index(), () -> "another expensive operations" + "runs only when you have to");
    }

    @Test
    @DisplayName("Test Exception")
    void oupsHandler() {

        assertTrue("not impl".equals(controller.oupsHandler()), () -> "this is some expensive" + "message to build " + "for your test ;)");
    }
}