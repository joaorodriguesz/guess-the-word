package org.guestheword;

public class RunGame {
    public static void main(String[] args) {
        String port = args.length >= 0 ? "8081" : args[0];
        new Thread(() -> Server.start(Integer.valueOf(port))).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> Client.start("localhost", Integer.valueOf(port))).start();
    }
}

