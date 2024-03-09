package Server;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PUERTO = 12345;
    private static Map<InetAddress,Integer> clientes = new HashMap<InetAddress,Integer>();
    private static Set<String> usuarios = new HashSet<String>();

    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(PUERTO);
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteE = new DatagramPacket(buffer, buffer.length);
                server.receive(paqueteE);
                String mensaje = new String(paqueteE.getData()).trim();
                switch (mensaje.split(":")[0]) {
                    case "Nick":
                        boolean libre = usuarios.add(mensaje.split(":")[1]);
                        byte[] bufferS = String.valueOf(libre).getBytes();
                        server.send(new DatagramPacket(bufferS, bufferS.length, paqueteE.getAddress(), paqueteE.getPort()));
                        if (libre) {
                            clientes.put(paqueteE.getAddress(), paqueteE.getPort());
                            difundirUsuariosConectados(server);
                        }
                        break;
                    case "Salir":
                        clientes.remove(paqueteE.getAddress());
                        usuarios.remove(mensaje.split(":")[1]);
                        difundirUsuariosConectados(server);
                        break;
                    case "Mensaje":
                        mensaje = mensaje.split(":")[1] + ": " + mensaje.split(":")[2];
                        System.out.println(mensaje);
                        for (Map.Entry<InetAddress,Integer> cliente : clientes.entrySet()) {
                            InetAddress ip = cliente.getKey();
                            int puerto = cliente.getValue();
                            if ((!ip.equals(paqueteE.getAddress())) || puerto != paqueteE.getPort()) {
                                server.send(new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, ip, puerto));
                            }
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void difundirUsuariosConectados(DatagramSocket server) {
        StringBuilder listaUsuarios = new StringBuilder("Usuarios conectados: ");
        for (String usuario : usuarios) {
            listaUsuarios.append(usuario).append(", ");
        }
        listaUsuarios.setLength(listaUsuarios.length() - 2);
        byte[] buffer = listaUsuarios.toString().getBytes();
        for (Map.Entry<InetAddress,Integer> cliente : clientes.entrySet()) {
            InetAddress ip = cliente.getKey();
            int puerto = cliente.getValue();
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, ip, puerto);
            try {
                server.send(paquete);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

