/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appautoalem;

/**
 *
 * @author juand
 */


import java.util.ArrayList;
import java.util.List;

public class VehiculoController {
    // Suponiendo que tienes una clase VehiculoDAO para interactuar con la base de datos.
    private VehiculoDAO vehiculoDAO;

    public VehiculoController() {
        vehiculoDAO = new VehiculoDAO();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculoDAO.agregar(vehiculo);
    }

    public void eliminarVehiculo(String matricula) {
        vehiculoDAO.eliminar(matricula);
    }

    public void actualizarEstado(String matricula, String nuevoEstado) {
        vehiculoDAO.actualizarEstado(matricula, nuevoEstado);
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculoDAO.listar();
    }
}

