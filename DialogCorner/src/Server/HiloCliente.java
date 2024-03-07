package Server;
import java.io.*;
import java.net.Socket;

public class HiloCliente implements Runnable {
    private Socket cliente;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private String nick;

    public HiloCliente(Socket socket) {
        this.cliente = socket;
        try {
            salida = new DataOutputStream(socket.getOutputStream());
            entrada = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

    private boolean nickOcupado(String username) {
        for (HiloCliente client : Server.getClientes()) {
            if ((!client.equals(this)) && client.getNick().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void mandarMensaje(String mensaje) {
        try {
            salida.writeUTF(mensaje);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            nick = entrada.readUTF();
            if (nickOcupado(nick)) {
                salida.writeBoolean(true);
            } else {
                salida.writeBoolean(false);
                Server.difundirUsuariosConectados();
                String mensaje;
                while (true) {
                    mensaje = entrada.readUTF();
                    if (!mensaje.equals("salir")) {
                        Server.difundirMensaje(mensaje, this);
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Cliente desconectado");
            Server.eliminarCliente(this);
            Server.difundirUsuariosConectados();
            try {
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
