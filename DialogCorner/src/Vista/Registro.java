package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Registro extends JFrame{
    private JPanel pnlRegistro;
    private JPanel pnlCampos;
    private JTextField txtfNickname;
    private JPasswordField pssfClave1;
    private JPasswordField pssfClave2;
    private JButton bttnAceptar;
    private JButton bttnCancelar;


    public Registro() {
        JFrame frame = new JFrame("Registro");
        frame.setContentPane(pnlRegistro);
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        bttnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        bttnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Arrays.equals(pssfClave1.getPassword(), pssfClave2.getPassword())) {
                    String nick = txtfNickname.getText();
                    String clave1 = new String(pssfClave1.getPassword());
                    JOptionPane.showMessageDialog(frame, "Usuario registrado");
                    frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden");
                }
            }
        });
    }
    public JPanel getPnlRegistro() {
        return pnlRegistro;
    }
    public void validarCampos() {
        String nick = txtfNickname.getText();
        String clave1 = new String(pssfClave1.getPassword());
        String clave2 = new String(pssfClave2.getPassword());
        if (System.getProperty("os.name").equals("Windows")) {

        } else {

        }
    }
}
