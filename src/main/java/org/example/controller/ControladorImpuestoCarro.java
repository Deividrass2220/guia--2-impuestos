package org.example.controller;

import org.example.model.Vehiculo;
import org.example.model.CalculadoraImpuestos;
import org.example.view.VistaImpuestoCarro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorImpuestoCarro {
    
    private VistaImpuestoCarro vista;
    
    /**
     * Constructor - inicializa el controlador con la vista
     * @param vista La instancia de VistaImpuestoCarro
     */
    public ControladorImpuestoCarro(VistaImpuestoCarro vista) {
        this.vista = vista;
        
        // Agregar listeners de eventos
        this.vista.agregarListenerCalcular(new ListenerAccionCalcular());
    }
    
    /**
     * Clase interna para manejar los clics del botón calcular
     */
    private class ListenerAccionCalcular implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            calcularImpuesto();
        }
    }
    
    /**
     * Método principal para manejar el cálculo de impuestos
     * Valida entrada, crea objeto vehículo, calcula impuesto y muestra resultados
     */
    private void calcularImpuesto() {
        try {
            // Validar entradas primero
            if (!vista.validarEntradas()) {
                return; // Validación falló, mensaje de error ya mostrado
            }
            
            // Extraer datos de entrada de la vista
            String marca = vista.getMarca();
            String modelo = vista.getModelo();
            int anio = Integer.parseInt(vista.getAnio());
            int cilindraje = Integer.parseInt(vista.getCilindraje());
            double avaluoComercial = Double.parseDouble(vista.getAvaluo());
            String tipoUso = vista.getTipoUso();
            
            // Crear objeto vehículo
            Vehiculo vehiculo = new Vehiculo(marca, modelo, anio, cilindraje, avaluoComercial, tipoUso);
            
            // Calcular impuesto usando el modelo
            double montoImpuesto = CalculadoraImpuestos.calcularImpuesto(vehiculo);
            
            // Obtener detalles del cálculo
            String detallesCalculo = CalculadoraImpuestos.obtenerDetallesCalculo(vehiculo);
            
            // Formatear y mostrar resultados
            String resultado = formatearResultado(vehiculo, montoImpuesto, detallesCalculo);
            vista.mostrarResultado(resultado);
            
        } catch (NumberFormatException ex) {
            vista.mostrarError("Formato de número inválido en uno o más campos. Por favor revise su entrada.");
        } catch (Exception ex) {
            vista.mostrarError("Ocurrió un error durante el cálculo: " + ex.getMessage());
        }
    }
    
    /**
     * Formatear el resultado del cálculo para mostrar
     * @param vehiculo El objeto vehículo
     * @param montoImpuesto El monto del impuesto calculado
     * @param detalles El desglose detallado del cálculo
     * @return Cadena de resultado formateada
     */
    private String formatearResultado(Vehiculo vehiculo, double montoImpuesto, String detalles) {
        StringBuilder resultado = new StringBuilder();
        
        // Encabezado de información del vehículo
        resultado.append("=== RESULTADO DEL CÁLCULO DE IMPUESTO VEHICULAR ===\n\n");
        
        // Detalles del vehículo
        resultado.append("Información del Vehículo:\n");
        resultado.append("Marca: ").append(vehiculo.getMarca()).append("\n");
        resultado.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
        resultado.append("Año: ").append(vehiculo.getAnio()).append("\n");
        resultado.append("Edad: ").append(vehiculo.getEdad()).append(" años\n");
        resultado.append("Cilindraje: ").append(vehiculo.getCilindraje()).append(" cc\n");
        resultado.append("Avalúo Comercial: $").append(String.format("%.2f", vehiculo.getAvaluoComercial())).append("\n");
        resultado.append("Tipo de Uso: ").append(capitalizar(vehiculo.getTipoUso())).append("\n\n");
        
        // Detalles del cálculo
        resultado.append(detalles).append("\n\n");
        
        // Resultado final destacado
        resultado.append("=====================================\n");
        resultado.append("MONTO TOTAL DEL IMPUESTO: $").append(String.format("%.2f", montoImpuesto)).append("\n");
        resultado.append("=====================================\n");
        
        return resultado.toString();
    }
    
    /**
     * Capitalizar la primera letra de una cadena
     * @param str Cadena de entrada
     * @return Cadena capitalizada
     */
    private String capitalizar(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    /**
     * Iniciar la aplicación haciendo visible la vista
     */
    public void iniciar() {
        vista.setVisible(true);
    }
    
    /**
     * Obtener la instancia de vista (útil para pruebas o acceso externo)
     * @return La instancia de VistaImpuestoCarro
     */
    public VistaImpuestoCarro getVista() {
        return vista;
    }
}