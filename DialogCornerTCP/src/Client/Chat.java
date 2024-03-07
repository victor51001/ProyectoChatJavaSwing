package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Chat {
    private String nick;
    private Socket server;
    private DataOutputStream salida;
    private DataInputStream entrada;
    HiloMensajes hiloM;
    private JPanel pnlChat;
    private JTextArea txtaText;
    private JTextArea txtaUsuers;
    private JButton bttnEnviar;
    private JTextField txtfMessage;
    private JButton bttnSalir;

    public Chat(String nick, Socket server) {
        this.nick = nick;
        this.server = server;
        try {
            salida = new DataOutputStream(server.getOutputStream());
            entrada = new DataInputStream(server.getInputStream());
            iniciarHiloMensajes();
        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar la conexión del cliente", e);
        }
        bttnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje(txtfMessage.getText());
                txtfMessage.setText("");
            }
        });
        bttnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salida.writeUTF("salir");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                hiloM.interrupt();
                cerrarConexion();
                System.exit(0);
            }
        });
    }

    public void añadirMensaje(String mensaje) {
        txtaText.append(mensaje + "\n");
    }

    private void iniciarHiloMensajes() {
        HiloMensajes hiloMensajes = new HiloMensajes(server, this);
        hiloM = hiloMensajes;
        hiloMensajes.start();
    }

    public void enviarMensaje(String mensaje) {
        añadirMensaje(nick + ": " + mensaje);
        try {
            salida.writeUTF(mensaje);
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar el mensaje", e);
        }
    }

    public void cerrarConexion() {
        try {
            salida.close();
            entrada.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Container getPnlChat() {
        return pnlChat;
    }

    public void actualizarUsuarios(String mensaje) {
        txtaUsuers.setText(mensaje);
    }
}
