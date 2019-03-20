package Oving4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class SocketKlient {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;

        /* Bruker en scanner til å lese fra kommandovinduet */
        Scanner leserFraKommandovindu = new Scanner(System.in);
        System.out.print("Oppgi navnet på maskinen der tjenerprogrammet kjører: ");
        String tjenermaskin = leserFraKommandovindu.nextLine();

        try (
                /* Åpner en forbindelse for kommunikasjon med tjenerprogrammet */
                InputStreamReader leseforbindelse = new InputStreamReader(System.in);
                BufferedReader leseren = new BufferedReader(leseforbindelse);
                DatagramSocket datagramSocket = new DatagramSocket();
        ) {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");

            byte[] sendBytes;
            byte[] receiveBytes = new byte[256];

            System.out.println("Nå er forbindelsen opprettet.");


            while (true) {
                String melding = leseren.readLine();
                if (melding.equals("close")) {
                    datagramSocket.close();
                    return;
                }

                sendBytes = melding.getBytes();

                DatagramPacket datagramPacket = new DatagramPacket(sendBytes, 0, sendBytes.length, inetAddress, PORTNR);
                datagramSocket.send(datagramPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
                datagramSocket.receive(receivePacket);

                String svar = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Svar fra server: " + svar);

            }

        } catch (Exception e) {
            System.out.println("error in sockettjener: " + e);
        }
    }
}
