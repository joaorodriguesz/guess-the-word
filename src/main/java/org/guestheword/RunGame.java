package org.guestheword;

public class RunGame {
    public static void main(String[] args) {
        String port = "8088";
        new Thread(() -> Server.start(Integer.valueOf(port))).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> Client.start("localhost", Integer.valueOf(port))).start();
    }
}

