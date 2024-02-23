package Modelo;

import Controlador.HiloEntradaServidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    public static ArrayList<Socket> clientes = new ArrayList<Socket>();
    public static void main(String[] args) {
        ServerSocket servidor = null;
        try {
            servidor = new ServerSocket(6001);
            while (true) {
                Socket cliente = servidor.accept();
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
