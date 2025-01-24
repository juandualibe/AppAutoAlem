package com.mycompany.appautoalem;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Cliente {
   public static final String HOST = "Localhost"; // Cambiar a la IP del servidor si está en otra PC
   private static final int PUERTO = 12345;
   
   public static void main(String[] args) {
        JFrame frame = new JFrame("Notificaciones de Vehículos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        // Crear una tabla para mostrar las alertas
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Matrícula");
        modelo.addColumn("Estado");

        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        // Hacer visible la ventana
        frame.setVisible(true);

        try (Socket socket = new Socket(HOST, PUERTO);
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Conectado al servidor");

            while (true) {
                // Leer alertas enviadas por el servidor
                List<Vehiculo> alertas = (List<Vehiculo>) entrada.readObject();

                // Limpiar la tabla
                modelo.setRowCount(0);

                // Si hay alertas, agregarlas a la tabla
                for (Vehiculo v : alertas) {
                    modelo.addRow(new Object[]{v.getMatricula(), v.getEstado()});
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
