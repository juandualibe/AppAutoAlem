
package com.mycompany.appautoalem;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    // Método para obtener los vehículos de la base de datos
    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM Vehiculos";
        
        try (Connection conn = ConexionBD.conectar(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("fecha_entrega"),
                        rs.getString("estado")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los vehículos: " + e.getMessage());
        }
        return vehiculos;
    }

    // Método para agregar un vehículo a la base de datos
    public void agregar(Vehiculo vehiculo) {
        String sql = "INSERT INTO Vehiculos (matricula, marca, modelo, fecha_entrega, estado) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());

            // Convertir la fecha a tipo java.sql.Date
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fecha = inputFormat.parse(vehiculo.getFechaEntrega());
            java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());

            stmt.setDate(4, sqlDate); // Establece la fecha como java.sql.Date
            stmt.setString(5, vehiculo.getEstado());

            stmt.executeUpdate();
        } catch (SQLException | java.text.ParseException e) {
            System.out.println("Error al agregar el vehículo: " + e.getMessage());
        }
    }

    // Método para eliminar un vehículo por matrícula
    public void eliminar(String matricula) {
        String sql = "DELETE FROM Vehiculos WHERE matricula = ?";
        
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el vehículo: " + e.getMessage());
        }
    }

    // Método para actualizar el estado de un vehículo
    public void actualizarEstado(String matricula, String nuevoEstado) {
        String sql = "UPDATE Vehiculos SET estado = ? WHERE matricula = ?";
        
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado);
            stmt.setString(2, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado: " + e.getMessage());
        }
    }
    public List<Vehiculo> listar() {
        return obtenerVehiculos(); // Utiliza el mismo método de obtenerVehiculos
    }
}

