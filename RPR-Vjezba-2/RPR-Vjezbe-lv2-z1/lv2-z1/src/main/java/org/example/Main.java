package org.example;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Unesi vrijednost: ");
        int n= scanner.nextInt();
        double f=1;
        for(int i=2;i<=n;i++)
        {
            f=f*i;
        }
        double vr=Math.sin(n);
        System.out.print(f + " " + vr);

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        }
    }
