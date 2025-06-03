package com.example.demo;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.io.IOException;
import io.github.cdimascio.dotenv.Dotenv;

public class UDPClient {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String serverIp = dotenv.get("SERVER_IP");
        int serverPort = Integer.parseInt(dotenv.get("SERVER_PORT"));
        
        String guess = "404";
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetSocketAddress destination = new InetSocketAddress(serverIp, serverPort);
            byte[] sendData = guess.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, destination);
            System.out.println("Sending guess: " + guess);
            clientSocket.send(packet);
            byte[] receiveData = new byte[100];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            System.out.println("Received: " + new String(receiveData));

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
