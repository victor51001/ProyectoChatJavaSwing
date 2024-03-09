package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class HiloMensajes extends Thread {
    Chat chat;
    DatagramSocket socket;

    public HiloMensajes(DatagramSocket sock, Chat ch) {
        socket = sock;
        chat = ch;
    }
    @Override
    public void run() {
        try {
            String mensaje;
            byte[] buffer = new byte[1024];
            while (true) {
                Thread.sleep(0);
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);
                mensaje = new String(paquete.getData()).trim();
                if (mensaje.split(":")[0].equals("Usuarios conectados")) {
                    chat.actualizarUsuarios(mensaje);
                } else {
                    chat.añadirMensaje(mensaje.split(":")[1]);
                }
            }
        } catch (IOException | InterruptedException e) {}
    }
}