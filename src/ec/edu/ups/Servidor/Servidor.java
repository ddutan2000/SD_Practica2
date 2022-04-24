package ec.edu.ups.Servidor;

import java.net.UnknownHostException;

import ec.edu.ups.WebSocket.Websocket;

public class Servidor {

    public static void main(String[] args) {
        try {
            new Websocket(443).start();
        } catch (UnknownHostException e) {
        }
    }
}
