package Controlador;

import Vista.Chat;

import java.io.DataInputStream;
import java.net.Socket;

public class HiloEntradaCliente implements Runnable{
    private Socket servidor;
    private DataInputStream entrada;
    private Chat chat;
    private String nickname;
    public HiloEntradaCliente(Socket servidor, Chat chat) {
        this.servidor = servidor;
        this.chat = chat;
        this.nickname = chat.getNickname();
        try {
            entrada = new DataInputStream(servidor.getInputStream());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    @Override
    public void run() {
        while (true) {
            try {
                String mensaje = entrada.readUTF();
                chat.agregarMensaje(mensaje);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
