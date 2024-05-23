package org.example;

public class MedunarodniBroj extends TelefonskiBroj{
    public MedunarodniBroj(String drzava, String broj) {
        this.drzava = drzava;
        this.broj = drzava + "/" + broj;
    }
    String drzava;
    private String broj;

    @Override
    public String ispisi() {
        return broj;
    }

    @Override
    public int hashCode(){
        return broj.hashCode();
    }
}