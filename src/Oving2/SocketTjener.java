package Oving2;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class SocketTjener {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;
        Socket forbindelse;
        try{
                ServerSocket tjener = new ServerSocket(PORTNR);

            while(true){
                forbindelse = tjener.accept();  // venter inntil noen tar kontakt
                Thread nyKlient = new ThreadClientManager(forbindelse);
                nyKlient.start();
            }


        } catch(Exception e) {
            System.out.println("error in sockettjener: " + e);
        }

    }
}

