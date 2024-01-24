package util;

import interfaz.HolaUsuario;

import javax.swing.*;

public class MainHolaMundo {
    public static void main(String[] args) {
        HolaUsuario ventana = new HolaUsuario();

        ventana.setSize(400,300);
        ventana.setTitle("Ventana de saludos");
        ventana.setResizable(false);
        String cadena = JOptionPane.showInputDialog("Escribe algo");
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //aa
    }
}
