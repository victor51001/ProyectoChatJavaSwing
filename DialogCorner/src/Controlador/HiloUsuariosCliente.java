package Controlador;

import Modelo.Usuario;
import Vista.Chat;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HiloUsuariosCliente implements Runnable{
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private Socket servidor;
    private DataInputStream entrada;
    private Chat chat;
    public HiloUsuariosCliente(Socket servidor, Chat chat) {
        this.servidor = servidor;
        this.chat = chat;
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
                String[] partes = mensaje.split(":");
                if (partes[0].equals("usuario")) {
                    Usuario usuario = new Usuario(partes[1]);
                    usuarios.add(usuario);
                } else if (partes[0].equals("desconectar")) {
                    for (int i = 0; i < usuarios.size(); i++) {
                        if (usuarios.get(i).getNickname().equals(partes[1])) {
                            usuarios.remove(i);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            chat.imprimirUsuarios();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
