/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appautoalem;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 *
 * @author juand
 */
public class PanelVehiculos extends JPanel {
    private JTable table;
    private VehiculoDAO vehiculoDAO;

    public PanelVehiculos() {
        vehiculoDAO = new VehiculoDAO();
        setLayout(new BorderLayout());

        // Obtener los vehículos
        List<Vehiculo> vehiculos = vehiculoDAO.obtenerVehiculos();
        
        // Definir las columnas para la tabla
        String[] columnas = {"ID", "Matricula", "Marca", "Modelo", "Estado", "Fecha de entrega", "Ubicacion"};
        
        // Crear los datos de la tabla
        Object[][] datos = new Object[vehiculos.size()][8];
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            datos[i] = new Object[]{
                v.getMatricula(),
                v.getMarca(),
                v.getModelo(),               
                v.getFechaEntrega(),                
                v.getEstado(),
                v.getUbicacion()
            };
        }
        
        // Crear la tabla
        table = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Agregar la tabla al panel
        add(scrollPane, BorderLayout.CENTER);
    }
}
