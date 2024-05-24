package org.example;

import java.util.ArrayList;

public class KolekcijaImenaIPrezimena implements Kolekcija {

    private ArrayList<String> imena;
    private ArrayList<String> prezimena;

    public KolekcijaImenaIPrezimena(){
        this.imena=new ArrayList<>();
        this.prezimena=new ArrayList<>();
    }

    public KolekcijaImenaIPrezimena(ArrayList<String> a, ArrayList<String> b){
        this.imena=a;
        this.prezimena=b;
    }

    public void dodajImePrezime(String ime,String prezime){
        imena.add(ime);
        prezimena.add(prezime);
    }

    public int getIndexNajduzegPara(){

        if (imena == null || prezimena == null || imena.size() != prezimena.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        String longest = "";
        int longestIndex=0;

        for(int i=0; i<imena.size(); i++){
            String current = imena.get(i) + prezimena.get(i);
            if(current.length() > longest.length()){
                longest=current;
                longestIndex=i;
            }
        }
        return longestIndex;
    }
    @Override
    public String getNajduzeIme(){
        int index = this.getIndexNajduzegPara();
        return this.getImeiPrezime(index);
    }
    public String getImeiPrezime(int i){
        return imena.get(i) + " " + prezimena.get(i);
    }



}
