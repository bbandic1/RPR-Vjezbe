package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class LicneInformacije implements Informacije {
    private String ime;
    private String prezime;

    public LicneInformacije()
    {
    }

    public LicneInformacije(String ime, String prezime) {
    }

    @Override
    public String predstavi() {
        return getIme() + " "+ getPrezime();
    }

    public String getIme()
    {
        return this.ime;
    }
    public String getPrezime()
    {
        return this.prezime;
    }

    public void setIme(String ime)
    {
        this.ime=ime;
    }

    public void setPrezime(String prezime)
    {
        this.ime=ime;
    }
}