#!/bin/bash

# Sistema de Cálculo de Impuestos de Vehículos - Script de Compilación y Ejecución

echo "=== Sistema de Cálculo de Impuestos de Vehículos ==="
echo "Compilando archivos Java..."

# Crear directorio de salida
mkdir -p out

# Compilar todos los archivos Java
javac -d out src/**/*.java src/*.java

if [ $? -eq 0 ]; then
    echo "¡Compilación exitosa!"
    echo "Ejecutando la aplicación..."
    
    # Ejecutar la aplicación
    cd out
    java MainImpuestoCarro
else
    echo "Compilación falló. Por favor revise los errores."
fi