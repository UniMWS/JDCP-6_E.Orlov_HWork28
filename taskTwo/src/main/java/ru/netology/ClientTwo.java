package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTwo {
    private static final String HOST = "localhost";
    private static final int PORT = 8002;

    public static void main(String[] args) {
        System.out.println("ДЗ № 28, «Основы работы с сетью. Модель OSI, TCP, UDP», Задача 2" +
                "\nЗапускаем клиента, стучимся на сервер...\n");
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String city = in.readLine();
            System.out.printf("Соединение установлено.\nГород: %s, следующий город:", city);

            Scanner sc = new Scanner(System.in);
            String newCity = sc.nextLine();
            out.println(newCity);

            String response = in.readLine();
            System.out.println(response);

        } catch (IOException e) {
            throw new RuntimeException(e + " - не достучались...");
        }
    }
}
