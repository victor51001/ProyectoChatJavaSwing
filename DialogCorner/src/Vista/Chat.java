package Vista;

import Controlador.HiloCliente;
import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Chat extends JFrame{
    private String nickname;
    private Socket servidor;
    private HiloCliente hiloCliente;
    private JTextArea txtaMensajes;
    private JTextField txtfMensajes;
    private JButton bttnEnviar;
    private JTextArea txtaUsuarios;
    private JPanel jpChat;
    private JButton bttnSalir;
    private ArrayList<String> usuarios = new ArrayList<>();

    public Chat(String nickname, Socket servidor) {
        this.servidor = servidor;
        this.nickname = nickname;
        bttnEnviar.addActionListener(e -> {
            String mensaje = txtfMensajes.getText();
            if (!mensaje.isEmpty()) {
                DataOutputStream salida;
                try {
                    salida = new DataOutputStream(servidor.getOutputStream());
                    salida.writeInt(11);
                    salida.writeUTF(nickname + ": " + mensaje);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                txtaMensajes.append(nickname + ": " + mensaje + "\n");
                txtfMensajes.setText("");
            }
        });
        bttnSalir.addActionListener(e -> {
            DataOutputStream salida = null;
            try {
                salida = new DataOutputStream(servidor.getOutputStream());
                salida.writeInt(12);
                salida.writeUTF(nickname);
                System.exit(0);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        hiloCliente = new HiloCliente(servidor, this);
        hiloCliente.start();
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
        for (String usuario : usuarios) {
            txtaUsuarios.append(usuario + "\n");
        }
    }
    public void agregarUsuario(String usuario) {
        usuarios.add(usuario);
    }
    public void eliminarUsuario(String usuario) {
        usuarios.remove(usuario);
    }
}
