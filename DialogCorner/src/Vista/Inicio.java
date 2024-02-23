package Vista;

import javax.swing.*;

public class Inicio extends JFrame{
    private JTextField txtfNick;
    private JPanel pnlCampo;
    private JButton bttnEntrar;
    private JPanel mainPanel;

    public Inicio() {
        setContentPane(mainPanel);
        bttnEntrar.addActionListener(e -> {
            String nickname = txtfNick.getText();
            if (nickname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo de nickname no puede estar vacío");
            } else {
                if (Controlador.ControladorUsuarios.usuarios.stream().anyMatch(usuario -> usuario.getNickname().equals(nickname))) {
                    JOptionPane.showMessageDialog(null, "El nickname ya está en uso");
                } else {
                    Controlador.ControladorUsuarios.usuarios.add(new Modelo.Usuario(nickname));
                    //avisar a los clientes
                    JFrame frame = new JFrame("Chat - " + nickname);
                    frame.setContentPane(new Chat(nickname).getPnlChat());
                    frame.setSize(400, 300);
                    frame.setResizable(false);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
            }
        });
    }
}
