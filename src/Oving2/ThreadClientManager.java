package Oving2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadClientManager extends Thread {

    Socket forbindelse;

    public ThreadClientManager(Socket forbindelse) {
        this.forbindelse = forbindelse;
    }

    @Override
    public void run() {
        try (
                InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
                BufferedReader leseren = new BufferedReader(leseforbindelse);
                PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);
        ) {
            System.out.println("Logg for tjenersiden. Nå venter vi...");


            /* Sender innledning til klienten */
            skriveren.println("Hei, du har kontakt med tjenersiden!");

            String enLinje = " ";


            int tall1;
            int tall2;
            String operator;
            while (enLinje != null) {  // forbindelsen på klientsiden er lukket

                try {

                    skriveren.println("Skriv ett tall");
                    enLinje = leseren.readLine();
                    tall1 = Integer.parseInt(enLinje);

                    System.out.println("En klient skrev: " + enLinje);
                    tall1 = Integer.parseInt(enLinje);

                    skriveren.println("pluss eller minus? ");  // sender svar til klienten
                    operator = leseren.readLine();
                    enLinje = operator;
                    skriveren.println("Skriv enda et tall");
                    enLinje = leseren.readLine();
                    tall2 = Integer.parseInt(enLinje);

                    if (operator.toLowerCase().equals("pluss")) {
                        skriveren.println(tall1 + " + " + tall2 + "= " + (tall1 + tall2));
                    } else if (operator.toLowerCase().equals("minus")) {
                        skriveren.println(tall1 + " - " + tall2 + "= " + (tall1 - tall2));
                    } else {
                        skriveren.println("Skriv enten 'pluss' eller 'minus'.");
                    }

                } catch (Exception e) {
                    System.out.println("error in while: " + e);
                    skriveren.println("error:Noe gikk galt, bruk kun tall og husk å skrive enten 'pluss' eller 'minus' ");
                }

            }

        } catch (Exception e) {
            System.out.println("error in sockettjener: " + e);
        }

    }

}
