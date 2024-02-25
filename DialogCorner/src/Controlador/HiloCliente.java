package Controlador;

import Vista.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloCliente extends Thread{
    private Socket servidor;
    private Chat chat;
    private DataOutputStream salida;
    private DataInputStream entrada;
    public HiloCliente(Socket servidor, Chat chat) {
        this.servidor = servidor;
        this.chat = chat;
        try {
            salida = new DataOutputStream(servidor.getOutputStream());
            entrada = new DataInputStream(servidor.getInputStream());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            int tamaño = entrada.readInt();
            for (int i = 0; i < tamaño; i++) {
                chat.agregarUsuario(entrada.readUTF());
            }
            chat.imprimirUsuarios();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                int orden = entrada.readInt();
                System.out.println("Cliente " + chat.getNickname() + " " + orden);
                switch (orden) {
                    case 21:
                        chat.agregarMensaje(entrada.readUTF());
                        break;
                    case 22:
                        chat.eliminarUsuario(entrada.readUTF());
                        chat.imprimirUsuarios();
                        break;
                    case 23:
                        chat.agregarUsuario(entrada.readUTF());
                        chat.imprimirUsuarios();
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
