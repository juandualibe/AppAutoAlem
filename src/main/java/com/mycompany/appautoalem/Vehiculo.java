/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appautoalem;

import java.sql.Date;

/**
 *
 * @author juand
 */
public class Vehiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private String fechaEntrega;
    private String estado;
    private String ubicacion;

    // Constructor con los parámetros que necesitas
    public Vehiculo(String matricula, String marca, String modelo, String fechaEntrega, String estado, String ubicacion) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    // Getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca(){
        return marca;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    public String getModelo(){
        return modelo;
    }
    
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    public String getFechaEntrega(){
        return fechaEntrega;
    }
    
    public void setFechaEntrega(String fechaEntrega){
        this.fechaEntrega = fechaEntrega;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public String getUbicacion(){
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }
}
