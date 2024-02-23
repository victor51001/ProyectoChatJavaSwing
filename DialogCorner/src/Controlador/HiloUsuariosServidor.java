package Controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class HiloUsuariosServidor implements Runnable{
    private Socket cliente;
    private DataInputStream entrada;
    private DataOutputStream salida;
    public HiloUsuariosServidor(Socket cliente) {
        this.cliente = cliente;
    }
    @Override
    public void run() {

    }
}
