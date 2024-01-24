package Vista;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame{
    private JTextField txtfNick;
    private JPasswordField pssfClave;
    private JButton bttnInicio;
    private JPanel pnlCampos;
    private JLabel lblNoUser;
    private JPanel pnlLogin;

    public Login() {
        lblNoUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("Registro");
                frame.setContentPane(new Registro().getPnlRegistro());
                frame.setSize(400, 300);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
