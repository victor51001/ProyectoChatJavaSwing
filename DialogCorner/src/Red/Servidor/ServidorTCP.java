package Red.Servidor;

import java.net.ServerSocket;

public class ServidorTCP {
    public static void main(String[] args) {
        ServerSocket servidor = null;
        try {
            servidor = new ServerSocket(6001);
            while (true) {

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
