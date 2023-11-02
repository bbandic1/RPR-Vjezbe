package org.example;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class FiksniBrojTest {


    @org.junit.jupiter.api.Test
    void ispisi() {
        FiksniBroj.Grad grad= FiksniBroj.Grad.valueOf("SARAJEVO");
        String broj= "123-456";
        FiksniBroj tel = new FiksniBroj(grad,broj);
        assertEquals("033/123-456",tel.ispisi());
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
    }
}