package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTwo {
    private static final int PORT = 8002;

    public static void main(String[] args) {
        System.out.println("ДЗ № 28, «Основы работы с сетью. Модель OSI, TCP, UDP», Задача 2" +
                "\nЗапускаем сервер, ждём клиента...\n");
        String city = null;
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    if (city == null) {
                        out.println("???");
                        city = in.readLine();
                        System.out.printf("Соединение установлено. Город клиента: %s\n", city);
                        out.println("OK");
                    } else {
                        city = getCity(city, out, in);
                        System.out.printf("Город клиента (port is %d): %s\n", clientSocket.getPort(), city);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private static String getCity(String city, PrintWriter out, BufferedReader in) throws IOException {
        out.println(city);
        String newCity = in.readLine();
        if (city.charAt(city.length() - 1) == newCity.charAt(0)) {
            city = newCity;
            out.println("OK");
        } else out.println("NOT OK");
        return city;
    }
}
