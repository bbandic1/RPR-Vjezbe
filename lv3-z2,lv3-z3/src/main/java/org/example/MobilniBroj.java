package org.example;

public class MobilniBroj extends TelefonskiBroj {
    public MobilniBroj(int mobilnaMreza, String broj) {
        this.MobilnaMreza = mobilnaMreza;
        this.Broj = String.format("%03d", mobilnaMreza) + "/" + broj;
    }
    int MobilnaMreza;
    private String Broj;
    @Override
    public String ispisi() {
        return Broj;
    }

    @Override
    public int hashCode(){
        return Broj.hashCode();
    }
}