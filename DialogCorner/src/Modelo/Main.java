package Modelo;

import Vista.Inicio;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Inicio inicio = new Inicio();
        inicio.setContentPane(inicio.getContentPane());
        inicio.setSize(400, 300);
        inicio.setTitle("Ventana de inicio");
        inicio.setResizable(false);
        inicio.setVisible(true);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
