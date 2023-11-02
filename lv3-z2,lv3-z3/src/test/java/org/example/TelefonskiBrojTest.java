package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelefonskiBrojTest {

    @Test
    void ispisi() {
        FiksniBroj fiksniBroj = new FiksniBroj(FiksniBroj.Grad.SARAJEVO, "123-456");
        TelefonskiBroj telefonskiBroj = fiksniBroj;
        assertEquals("033/123-456", telefonskiBroj.ispisi());
    }

    @Test
    void testHashCode() {
    }
}