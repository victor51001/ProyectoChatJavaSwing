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
        HolaUsuario ventana = new HolaUsuario();

        ventana.setSize(400,300);
        ventana.setTitle("Ventana de saludos");
        ventana.setResizable(false);
        String cadena = JOptionPane.showInputDialog("Escribe algo");
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
