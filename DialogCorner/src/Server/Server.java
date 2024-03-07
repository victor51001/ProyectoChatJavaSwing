package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PUERTO = 12345;
    private static List<HiloCliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            while (true) {
                Socket cliente = server.accept();
                System.out.println("Nuevo cliente conectado");

                HiloCliente hiloCliente = new HiloCliente(cliente);
                clientes.add(hiloCliente);
                new Thread(hiloCliente).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void difundirMensaje(String mensaje, HiloCliente emisor) {
        for (HiloCliente cliente : clientes) {
            if (!cliente.equals(emisor)) {
                cliente.mandarMensaje(emisor.getNick() + ": " + mensaje);
            }
        }
    }

    public static void difundirUsuariosConectados() {
        StringBuilder listaUsuarios = new StringBuilder("Usuarios conectados: ");
        for (HiloCliente cliente : clientes) {
            listaUsuarios.append(cliente.getNick()).append(", ");
        }
        listaUsuarios.setLength(listaUsuarios.length() - 2);

        for (HiloCliente client : clientes) {
            client.mandarMensaje(listaUsuarios.toString());
        }
    }

    public static void eliminarCliente(HiloCliente cliente) {
        clientes.remove(cliente);
    }
    public static List<HiloCliente> getClientes() {
        return clientes;
    }
}

