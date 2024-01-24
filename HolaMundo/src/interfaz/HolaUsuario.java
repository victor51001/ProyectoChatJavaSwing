package interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HolaUsuario extends JFrame{
    private JPanel mainPanel;
    private JLabel prompt;
    private JTextField tuNombre;
    private JButton btnSaludar;

    public HolaUsuario() {
        setContentPane(mainPanel);
        btnSaludar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnSaludar,"Hola " + tuNombre.getText());
            }
        });
    }

    public static void main(String[] args) {
        HolaUsuario h = new HolaUsuario();
        h.setSize(400,300);
        h.setTitle("Ventana de saludos");
        h.setVisible(true);
        h.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}