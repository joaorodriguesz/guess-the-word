package org.guestheword;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static String findBeforeFirstColon(String text) {
        int indexOfColon = text.indexOf(":");

        if (indexOfColon != -1) {
            return text.substring(0, indexOfColon);
        } else {
            return text;
        }
    }

    public static String findAfterFirstSemicolon(String texto) {
        Integer index = texto.indexOf(":");

        if (index != -1) {
            return texto.substring(index + 1).trim();
        } else {
            return "";
        }
    }
    public static void start(String host, Integer port) {
        try {
            Socket socket = new Socket(host, port);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String response;
            String userChoice;
            Boolean exit = false;
            do {
                userChoice = userInput.readLine();
                output.println(userChoice);
                response = input.readLine();

                switch (findBeforeFirstColon(response)) {
                    case "Dica":
                        System.out.println(response);
                        System.out.println("Digite a palvra abaixo:");
                        continue;
                    case "Resultado":
                        if(findAfterFirstSemicolon(response).equals("correta") || findAfterFirstSemicolon(response).equals("tentativasEsgotadas")){
                            System.out.println("Palavra " + findAfterFirstSemicolon(response) + "!");
                            System.out.println("Jogar novamente? (s/n)");
                        } else {
                            System.out.println("Contem: " + findAfterFirstSemicolon(response));
                        }
                        continue;
                    case "Erro":
                        System.out.println(findAfterFirstSemicolon(response));
                        System.out.println("Palavra " + findAfterFirstSemicolon(response) + "!");
                        System.out.println("Jogar novamente? (s/n)");
                        continue;
                }
            } while (!exit);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

