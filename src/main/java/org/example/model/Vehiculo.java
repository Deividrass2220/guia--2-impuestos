package org.example.model;

public class Vehiculo {
    private String marca;
    private String modelo;
    private int anio;
    private int cilindraje; // en cc
    private double avaluoComercial;
    private String tipoUso; // "particular" o "publico"

    // Constructor
    public Vehiculo(String marca, String modelo, int anio, int cilindraje,
                    double avaluoComercial, String tipoUso) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.cilindraje = cilindraje;
        this.avaluoComercial = avaluoComercial;
        this.tipoUso = tipoUso;
    }

    // Getters
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public double getAvaluoComercial() {
        return avaluoComercial;
    }

    public String getTipoUso() {
        return tipoUso;
    }

    // Setters
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public void setAvaluoComercial(double avaluoComercial) {
        this.avaluoComercial = avaluoComercial;
    }

    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    // Calcular edad del vehículo
    public int getEdad() {
        return 2024 - anio;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", cilindraje=" + cilindraje +
                ", avaluoComercial=" + avaluoComercial +
                ", tipoUso='" + tipoUso + '\'' +
                '}';
    }
}