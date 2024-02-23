package Controlador;

import Vista.Chat;

import java.io.DataOutputStream;
import java.net.Socket;

public class HiloSalidaCliente implements Runnable{
    private Socket servidor;
    private String mensaje;
    private DataOutputStream salida;
    private Chat chat;
    private String nickname;

    public HiloSalidaCliente(Socket servidor, Chat chat) {
        this.servidor = servidor;
        this.chat = chat;
        this.nickname = chat.getNickname();
        try {
            salida = new DataOutputStream(servidor.getOutputStream());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            mensaje = chat.getMensaje();
            if (!mensaje.isEmpty()) {
                try {
                    salida.writeUTF(nickname + ": " + mensaje);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
