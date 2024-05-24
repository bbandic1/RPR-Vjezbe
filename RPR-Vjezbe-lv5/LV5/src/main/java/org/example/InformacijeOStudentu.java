package org.example;

public class InformacijeOStudentu extends LicneInformacije implements Informacije, MozeOcijeniti{
    String godinaStudija;
    String brojIndexa;

    public InformacijeOStudentu(String godinaStudija, String brojindexa,
                                String ime, String prezime){
        this.brojIndexa=brojindexa;
        this.godinaStudija=godinaStudija;
        this.setIme(ime);
        this.setPrezime(prezime);
    }

    @Override
    public String predstavi(){
        return getIme()+ " " + getPrezime()+" "+getGodinaStudija()+getBrojIndexa();
    }
    @Override
    public Ocjena ocijeni(int x){
        LicneInformacije osoba=new LicneInformacije(this.getIme(),this.getPrezime());
        return new Ocjena(osoba,x);
    }
    public String getGodinaStudija(){
        return this.godinaStudija;
    }
    public String getBrojIndexa(){
        return this.brojIndexa;
    }

    public void setGodinaStudija(String godinaStudija){
        this.godinaStudija=godinaStudija;
    }
    public void setBrojIndexa(String brojIndexa){
        this.brojIndexa=brojIndexa;
    }

}
