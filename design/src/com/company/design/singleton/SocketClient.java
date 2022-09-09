package com.company.design.singleton;

public class SocketClient {

    private static SocketClient socketClient = null;

    private SocketClient(){
    // Default 생성자를 private으로 막아줘야 singleton
    }

    public static SocketClient getInstance() {
        if (socketClient == null){
            socketClient = new SocketClient();
        }
        return socketClient;
    }

    public void connect(){
        System.out.println("connect");
    }
}
