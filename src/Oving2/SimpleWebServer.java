package Oving2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleWebServer {

    public static void main(String[] args) throws IOException {
        final int PORTNR = 80;
        try {
            ServerSocket tjener = new ServerSocket(PORTNR);
            System.out.println("Startet server");
            Socket forbindelse = tjener.accept();  // venter inntil noen tar kontakt

            InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
            BufferedReader leseren = new BufferedReader(leseforbindelse);
            PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

            ArrayList<String> headers = new ArrayList<>();
            String line = leseren.readLine();

            while (line != null) {

                if(line.equals("")){
                    skriveren.println("HTTP/1.1 200 OK");
                    skriveren.println("Content-Type: text/html");
                    skriveren.println();
                    skriveren.println("<html><body>");
                    skriveren.println("<h1>Dette er en overskrift</h1>");
                    skriveren.println("<img src=\"data:image/jpeg;base64,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\">");
                    skriveren.println("<p  style=\"color:blue;\">Gutta gutta gutta</p>");
                    
                    skriveren.println("Header fra klient er: ");
                    skriveren.println("<ul>");

                    for (int i = 0; i < headers.size(); i++) {
                        skriveren.println("<li>" + headers.get(i) + "</li>");
                    }

                    skriveren.println("</ul>");
                    skriveren.println("</body></html>");

                    leseren.close();
                    skriveren.close();
                    forbindelse.close();
                    return;
                } else {
                    headers.add(line);
                }
                line = leseren.readLine();
            }


        } catch (Exception e) {
            System.out.println("error in simplewebserver " + e);
        }

    }
}
