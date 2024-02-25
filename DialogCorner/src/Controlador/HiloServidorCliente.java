package Controlador;

import Modelo.Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class HiloServidorCliente extends Thread{

    private ArrayList<String> usuarios = new ArrayList<String>();
    private Socket cliente;
    public HiloServidorCliente(Socket cliente) {
        this.cliente = cliente;
    }
    @Override
    public void run() {
        DataInputStream entrada = null;
        DataOutputStream salida = null;
        try {
            entrada = new DataInputStream(cliente.getInputStream());
            String nickname = entrada.readUTF();
            salida = new DataOutputStream(cliente.getOutputStream());
            boolean existe = false;
            for (String usuario : usuarios) {
                if (usuario.equals(nickname)) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                salida.writeInt(1);
            } else {
                salida.writeInt(0);
                usuarios.add(nickname);
                salida.writeInt(usuarios.size());
                for (String usuario : usuarios) {
                    salida.writeUTF(usuario);
                }
                ArrayList<DataOutputStream> salidas = new ArrayList<DataOutputStream>();
                for (int i = 0; i < Servidor.clientes.size(); i++) {
                    salidas.add(new DataOutputStream(Servidor.clientes.get(i).getOutputStream()));
                    salidas.get(i).writeInt(23);
                    salidas.get(i).writeUTF(nickname);
                }
                salidas.clear();
                Servidor.clientes.add(cliente);
                while (true) {
                    int orden = entrada.readInt();
                    System.out.println(orden);
                    switch (orden) {
                        case 11:
                            String mensaje = entrada.readUTF();
                            for (int i=0; i < Servidor.clientes.size(); i++) {
                                if (Servidor.clientes.get(i) != cliente) {
                                    salidas.add(new DataOutputStream(Servidor.clientes.get(i).getOutputStream()));
                                    salidas.get(i).writeInt(21);
                                    salidas.get(i).writeUTF(nickname + ": " + mensaje);
                                }
                            }
                            salidas.clear();
                            break;
                        case 12:
                            nickname = entrada.readUTF();
                            Servidor.clientes.remove(cliente);
                            usuarios.remove(nickname);
                            for (int i=0; i < Servidor.clientes.size(); i++) {
                                salidas.add(new DataOutputStream(Servidor.clientes.get(i).getOutputStream()));
                                salidas.get(i).writeInt(22);
                                salidas.get(i).writeUTF(nickname);
                            }
                            salidas.clear();
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
