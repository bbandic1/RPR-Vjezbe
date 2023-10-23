package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Integer> listaBrojeva = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for(;;) {
            System.out.print("Unesite broj ili 'stop' za kraj unosa: ");
            String unos = scanner.next();
            if (unos.equalsIgnoreCase("stop")) {
                break;
            } else {
                try {
                    int broj = Integer.parseInt(unos);
                    listaBrojeva.add(broj);
                } catch (NumberFormatException e) {
                    System.out.println("Niste unijeli ispravan broj. Pokušajte ponovo.");
                }
            }
        }
        if (!listaBrojeva.isEmpty()) {
            int min = listaBrojeva.get(0);
            int max = listaBrojeva.get(0);
            int sum = 0;

            for (int broj : listaBrojeva) {
                if (broj < min) {
                    min = broj;
                }
                if (broj > max) {
                    max = broj;
                }
                sum += broj;
            }

            double mean = (double) sum / listaBrojeva.size();
            double var = 0;

            for (int broj : listaBrojeva) {
                var += Math.pow(broj - mean, 2);
            }

            double sd = Math.sqrt(var / listaBrojeva.size());

            System.out.println("Minimum: " + min);
            System.out.println("Maximum: " + max);
            System.out.println("Srednja vrijednost: " + mean);
            System.out.println("Standardna devijacija: " + sd);
        } else {
            System.out.println("Niste unijeli nijedan broj.");
        }
    }
}