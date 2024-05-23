package org.example;

public class FiksniBroj extends TelefonskiBroj{

    public FiksniBroj(Grad grad, String broj) {
        this.grad = grad;
        this.broj = broj;
    }
    public enum Grad {SARAJEVO, ZENICA, MOSTAR, TUZLA, BIHAC, TRAVNIK, BRCKO, GORAZDE, LIVNO, ODZAK, LJUBUSKI}
    protected Grad grad;
    private String broj;
    @Override
    public String ispisi(){
        String pozBroj="";
        switch (grad){
            case SARAJEVO -> pozBroj = "033";
            case TUZLA -> pozBroj = "035";
            case ZENICA ->  pozBroj = "032";
            case BRCKO -> pozBroj = "049";
            case BIHAC -> pozBroj = "037";
            case GORAZDE -> pozBroj = "038";
            case MOSTAR -> pozBroj = "036";
            case LIVNO -> pozBroj = "034";
            case ODZAK -> pozBroj = "031";
            case TRAVNIK -> pozBroj = "030";
            case LJUBUSKI -> pozBroj = "039";
        }
        return pozBroj + "/" + broj;
    }
    @Override
    public int hashCode(){
        return grad.hashCode() + broj.hashCode();
    }

}