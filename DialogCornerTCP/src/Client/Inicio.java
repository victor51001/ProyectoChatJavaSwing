package Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Inicio extends JFrame{
    private JTextField txtfNick;
    private JPanel pnlCampo;
    private JButton bttnEntrar;
    private JPanel mainPanel;

    public static void main(String[] args) {
        Inicio ventana = new Inicio();
        ventana.setSize(400,300);
        ventana.setTitle("Ventana de inicio");
        ventana.setResizable(false);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Inicio() {
        setContentPane(mainPanel);
        bttnEntrar.addActionListener(e -> {
            Socket servidor = null;
            try {
                servidor = new Socket("localhost", 12345);
                DataOutputStream salida = new DataOutputStream(servidor.getOutputStream());
                DataInputStream entrada = new DataInputStream(servidor.getInputStream());

                String nickname = txtfNick.getText();
                if (nickname.isEmpty()) {
                    mostrarError("El campo de nickname no puede estar vacío");
                } else {
                    salida.writeUTF(nickname);
                    salida.flush();
                    if (entrada.readBoolean()) {
                        mostrarError("El nickname ya está en uso");
                    } else {
                        iniciarChat(nickname, servidor);
                    }
                }

                } catch (IOException ex) {
                mostrarError("Error al conectarse al servidor: " + ex.getMessage());
                }
        });
    }
    private void iniciarChat(String nickname, Socket servidor) {
        JFrame frame = new JFrame("Chat - " + nickname);
        frame.setContentPane(new Chat(nickname, servidor).getPnlChat());
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        dispose();
    }
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}