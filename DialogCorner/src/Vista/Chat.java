package Vista;

import Controlador.HiloEntradaCliente;
import Controlador.HiloSalidaCliente;
import Controlador.HiloUsuariosCliente;
import Modelo.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Chat extends JFrame{
    private String nickname;
    private Socket servidor;
    private HiloEntradaCliente hiloEntrada;
    private HiloSalidaCliente hiloSalida;
    private HiloUsuariosCliente hiloUsuarios;
    private JTextArea txtaMensajes;
    private JTextField txtfMensajes;
    private JButton bttnEnviar;
    private JTextArea txtaUsuarios;
    private JPanel jpChat;
    private JButton button1;

    public Chat(String nickname) {
        try {
            servidor = new Socket("localhost", 6001);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.nickname = nickname;
        bttnEnviar.addActionListener(e -> {
            String mensaje = txtfMensajes.getText();
            if (!mensaje.isEmpty()) {
                txtaMensajes.append(nickname + ": " + mensaje + "\n");
                txtfMensajes.setText("");
            }
        });
        hiloEntrada = new HiloEntradaCliente(servidor, this);
        hiloSalida = new HiloSalidaCliente(servidor, this);
        hiloUsuarios = new HiloUsuariosCliente(servidor, this);
        new Thread(hiloEntrada).start();
        new Thread(hiloSalida).start();
        new Thread(hiloUsuarios).start();
    }
    public JPanel getPnlChat() {
        return jpChat;
    }
    public void agregarMensaje(String mensaje) {
        txtaMensajes.append(mensaje + "\n");
    }
    public String getNickname() {
        return nickname;
    }
    public String getMensaje() {
        return txtfMensajes.getText();
    }
    public void imprimirUsuarios() {
        txtaUsuarios.setText("");
        for (Usuario usuario : hiloUsuarios.getUsuarios()) {
            txtaUsuarios.append(usuario.getNickname() + "\n");
        }
    }
}
