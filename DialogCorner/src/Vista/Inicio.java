package Vista;

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

    public Inicio() {
        setContentPane(mainPanel);
        bttnEntrar.addActionListener(e -> {
            Socket servidor;
            DataOutputStream salida;
            DataInputStream entrada;
            try {
                servidor = new Socket("localhost", 6001);
                salida = new DataOutputStream(servidor.getOutputStream());
                entrada = new DataInputStream(servidor.getInputStream());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            String nickname = txtfNick.getText();
            if (nickname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo de nickname no puede estar vacío");
            } else {
                try {
                    salida.writeUTF(nickname);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    if (entrada.readInt() == 1) {
                        JOptionPane.showMessageDialog(null, "El nickname ya está en uso");
                    } else {
                        JFrame frame = new JFrame("Chat - " + nickname);
                        frame.setContentPane(new Chat(nickname,servidor).getPnlChat());
                        frame.setSize(400, 300);
                        frame.setResizable(false);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
