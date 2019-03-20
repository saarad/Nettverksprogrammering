package Oving4;

import java.io.IOException;
import java.net.*;

class SocketTjener {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;
        try {
            DatagramSocket datagramSocket = new DatagramSocket(PORTNR);
            byte[] sendBytes;
            byte[] recieveBytes = new byte[256];

            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(recieveBytes, 256);
                datagramSocket.receive(datagramPacket);

                String recievedData = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("Fra klient: " + recievedData);

                recievedData = recievedData.replaceAll("\\s+","");
                int sumNumbers = -1000000000;

                if (recievedData.contains("pluss")) {
                    String[] numbers = (recievedData.split("pluss"));
                    sumNumbers = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
                } else if (recievedData.contains("minus")) {
                    String[] numbers = (recievedData.split("minus"));
                    sumNumbers = Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]);
                }

                String outputString = "= " + sumNumbers;

                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();

                sendBytes = outputString.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendBytes, sendBytes.length, inetAddress, port);
                datagramSocket.send(sendPacket);


            }
        } catch (Exception e) {
            System.out.println("error in sockettjener: " + e);
        }

    }
}

