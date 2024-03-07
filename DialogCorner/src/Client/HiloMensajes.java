package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloMensajes extends Thread {
    DataInputStream entrada;
    Chat chat;

    public HiloMensajes(Socket server, Chat ch) {
        try {
            entrada = new DataInputStream(server.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        chat = ch;
    }
    @Override
    public void run() {
        try {
            String mensaje;
            while (true) {
                Thread.sleep(0);
                mensaje = entrada.readUTF();
                if (mensaje.split(":")[0].equals("Usuarios conectados")) {
                    chat.actualizarUsuarios(mensaje);
                } else {
                    chat.añadirMensaje(mensaje);
                }
            }
        } catch (IOException | InterruptedException e) {}
    }
}