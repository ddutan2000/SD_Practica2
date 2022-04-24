package ec.edu.ups.WebSocket;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import ec.edu.ups.imprimir.Imprimir;

public class Websocket extends WebSocketServer {

    public Websocket(int puerto) throws UnknownHostException {
        super(new InetSocketAddress(puerto));
        System.out.println("Recibiendo conexiones en el puerto " + puerto);
    }

    @Override
    public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
        System.out.println("Conexcion desactivada");

    }

    @Override
    public void onError(WebSocket arg0, Exception e) {
        System.out.println("Ha ocurrido un error de conexcion");
    }

    @Override
    public void onMessage(WebSocket webSocket, String mensaje) {
        System.out.println("Imprimir");
        Imprimir imp = new Imprimir();
        imp.imprimirTexto(mensaje);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake arg1) {
        System.out.println("Conexcion activada");
    }

}
