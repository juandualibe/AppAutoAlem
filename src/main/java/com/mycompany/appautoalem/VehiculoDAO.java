
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
                        rs.getString("estado"),
                        rs.getString("ubicacion")
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
        String sql = "INSERT INTO Vehiculos (matricula, marca, modelo, fecha_entrega, estado, ubicacion) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());

            // Convertir la fecha a tipo java.sql.Date
            // Convertir la fecha y hora a tipo java.sql.Timestamp
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");  // Incluye la hora
            java.util.Date fecha = inputFormat.parse(vehiculo.getFechaEntrega());  // Suponiendo que vehiculo.getFechaEntrega() es la fecha y hora en formato String
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(fecha.getTime());  // Utilizamos Timestamp en lugar de Date


            stmt.setTimestamp(4, sqlTimestamp); // Establece la fecha como java.sql.Date
            stmt.setString(5, vehiculo.getEstado());
            stmt.setString(6, vehiculo.getUbicacion());

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

    // Método para actualizar el estado y la ubicacion de un vehículo
    public void actualizar(String matricula, String nuevoEstado, String nuevaUbicacion) {
    StringBuilder sql = new StringBuilder("UPDATE Vehiculos SET ");
    List<Object> parametros = new ArrayList<>();

    // Verificamos si el nuevo estado no es null ni vacío y lo agregamos a la consulta
    if (nuevoEstado != null && !nuevoEstado.isEmpty()) {
        sql.append("estado = ?, ");
        parametros.add(nuevoEstado);
    }

    // Verificamos si la nueva ubicación no es null ni vacía y lo agregamos a la consulta
    if (nuevaUbicacion != null && !nuevaUbicacion.isEmpty()) {
        sql.append("ubicacion = ?, ");
        parametros.add(nuevaUbicacion);
    }

    // Quitamos la última coma
    sql.delete(sql.length() - 2, sql.length());

    sql.append(" WHERE matricula = ?");

    // Agregar matrícula al final de los parámetros
    parametros.add(matricula);

    try (Connection conn = ConexionBD.conectar(); 
         PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

        // Establecemos los parámetros de la consulta
        for (int i = 0; i < parametros.size(); i++) {
            stmt.setObject(i + 1, parametros.get(i));
        }

        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al actualizar el estado o la ubicación: " + e.getMessage());
    }
}
    public List<Vehiculo> listar() {
        return obtenerVehiculos(); // Utiliza el mismo método de obtenerVehiculos
    }
}

