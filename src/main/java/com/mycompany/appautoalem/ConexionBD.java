/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appautoalem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static Connection conectar() {
        Connection conn = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // URL de conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/autoalem";
            String usuario = "root"; // Cambia el usuario si es necesario
            String contraseña = "root"; // Cambia la contraseña si es necesario
            
            // Establecer la conexión
            conn = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa!");
            
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }
}
