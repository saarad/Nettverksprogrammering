package Oving2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketKlient {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;

        /* Bruker en scanner til å lese fra kommandovinduet */
        Scanner leserFraKommandovindu = new Scanner(System.in);
        System.out.print("Oppgi navnet på maskinen der tjenerprogrammet kjører: ");
        String tjenermaskin = leserFraKommandovindu.nextLine();

        try(
                /* Setter opp forbindelsen til tjenerprogrammet */
                Socket forbindelse = new Socket(tjenermaskin, PORTNR);
                /* Åpner en forbindelse for kommunikasjon med tjenerprogrammet */
                InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
                BufferedReader leseren = new BufferedReader(leseforbindelse);
                PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

        ){
            System.out.println("Nå er forbindelsen opprettet.");
            /* Leser innledning fra tjeneren og skriver den til kommandovinduet */
            String innledning1 = leseren.readLine();
            String innledning2 = leseren.readLine();
            System.out.println(innledning1 + "\n" + innledning2);

            /* Leser tekst fra kommandovinduet (brukeren) */
            String enLinje = leserFraKommandovindu.nextLine();
            int counter = 0;
            while (!enLinje.equals("")) {
                skriveren.println(enLinje);  // sender teksten til tjeneren
                String respons = leseren.readLine();  // mottar respons fra tjeneren
                System.out.println("Fra tjenerprogrammet: " + respons);
                counter++;
                if(counter == 3 || respons.substring(0, 5).equals("error")){
                    respons = leseren.readLine();  // mottar respons fra tjeneren
                    System.out.println("Fra tjenerprogrammet: " + respons);
                    counter = 0;
                }
                enLinje = leserFraKommandovindu.nextLine();
            }
        } catch(Exception e){
            System.out.println("error in sockettjener: " + e);
        }
    }
}
