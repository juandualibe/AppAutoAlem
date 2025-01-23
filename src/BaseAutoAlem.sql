-- Crear la base de datos
CREATE DATABASE autoalem;

-- Usar la base de datos creada
USE autoalem;

-- Tabla Vehículos
CREATE TABLE Vehiculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(20) UNIQUE,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    fecha_entrega DATE,
    estado VARCHAR(50)
);

-- Tabla Seguimiento
CREATE TABLE Seguimiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_vehiculo INT,
    fecha DATE,
    detalle TEXT, -- Detalles del estado del vehículo, comentarios, etc.
    FOREIGN KEY (id_vehiculo) REFERENCES Vehiculos(id)
);

INSERT INTO Vehiculos (matricula, marca, modelo, fecha_entrega, estado) VALUES
('ABC123', 'Toyota', 'Corolla', '2025-02-15', 'En espera'),
('XYZ789', 'Honda', 'Civic', '2025-03-05', 'En reparación'),
('LMN456', 'Ford', 'Focus', '2025-02-28', 'Listo para entrega'),
('DEF321', 'Chevrolet', 'Cruze', '2025-02-10', 'En espera'),
('PQR654', 'BMW', '320i', '2025-03-01', 'En reparación');