package Modelo;

import Controlador.HiloServidorCliente;

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
                HiloServidorCliente hilo = new HiloServidorCliente(cliente);
                hilo.start();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
