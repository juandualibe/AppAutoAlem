package com.mycompany.appautoalem;

import java.io.*;
import java.net.*;
import java.util.List;
import javax.net.ssl.SSLServerSocket;


public class Servidor {
    private static final int PUERTO = 12345;
    
    
    
    public static void main(String[] args){
        try(ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");
            while(true){
                Socket cliente = servidor.accept(); //Aceptar la conexión de un Cliente
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());
                
                //Crear un Hilo para manejar al Cliente
                new Thread(() -> manejarCliente(cliente)).start();                
            }
        }
        catch(IOException e){
            System.err.println("Error en el servidor: " + e.getMessage());            
        }
    }
    
    private static void manejarCliente(Socket cliente){
        try (ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream())) {
            while(true){
                //Aqui consultamos la base de datos para los estados
                List<Vehiculo> vehiculos = VehiculoDAO.obtenerVehiculos(); 
                
                //Filtramos vehículos según los estados que interesan
                List<Vehiculo> alertas = vehiculos.stream()
                        .filter(v -> v.getEstado().equalsIgnoreCase("Señado") || v.getEstado().equalsIgnoreCase("En preparación"))
                        .toList();
                
                //Enviar la lista de alertas al Cliente
                salida.writeObject(alertas);
                salida.flush();
                
                //Esperar un tiempo antes de enviar automáticamente
                Thread.sleep(5000);
            }            
        }
        catch(IOException | InterruptedException ex){
            System.err.println("Error con el cliente: " + ex.getMessage());
        }
        finally{
            try{
                cliente.close();
            }
            catch(IOException ex){
                System.err.println("Error al cerrar el cliente: " + ex.getMessage());
            }
        }
    }
}
