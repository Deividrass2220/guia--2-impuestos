package model;

public class CalculadoraImpuestos {

    // Tasas de impuesto base (porcentajes)
    private static final double TASA_IMPUESTO_BASE = 0.02; // 2% tasa base
    private static final double MULTIPLICADOR_USO_PARTICULAR = 1.0;
    private static final double MULTIPLICADOR_USO_PUBLICO = 1.5;

    // Factores de depreciación según antigüedad
    private static final double[] MULTIPLICADORES_EDAD = {
        1.0,  // 0 años (nuevo)
        0.95, // 1 año
        0.90, // 2 años
        0.85, // 3 años
        0.80, // 4 años
        0.75, // 5 años
        0.70, // 6 años
        0.65, // 7 años
        0.60, // 8 años
        0.55, // 9 años
        0.50  // 10+ años
    };

    // Multiplicadores según cilindraje
    private static final double MULTIPLICADOR_MOTOR_PEQUENIO = 0.8;  // ≤ 1000cc
    private static final double MULTIPLICADOR_MOTOR_MEDIANO = 1.0;   // 1001-2000cc
    private static final double MULTIPLICADOR_MOTOR_GRANDE = 1.3;    // 2001-3000cc
    private static final double MULTIPLICADOR_MOTOR_LUJO = 1.6;      // > 3000cc

    /**
     * Calcula el impuesto para un vehículo dado
     * @param vehiculo El vehículo sobre el cual calcular el impuesto
     * @return El monto del impuesto calculado
     */
    public static double calcularImpuesto(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo");
        }

        // Inicia con el avalúo comercial y la tasa base
        double impuestoBase = vehiculo.getAvaluoComercial() * TASA_IMPUESTO_BASE;

        // Aplicar depreciación por antigüedad
        double multiplicadorEdad = obtenerMultiplicadorEdad(vehiculo.getEdad());
        impuestoBase *= multiplicadorEdad;

        // Aplicar multiplicador por cilindraje
        double multiplicadorMotor = obtenerMultiplicadorCilindraje(vehiculo.getCilindraje());
        impuestoBase *= multiplicadorMotor;

        // Aplicar multiplicador por tipo de uso
        double multiplicadorUso = obtenerMultiplicadorUso(vehiculo.getTipoUso());
        impuestoBase *= multiplicadorUso;

        return Math.round(impuestoBase * 100.0) / 100.0; // Redondear a 2 decimales
    }

    /**
     * Obtiene el multiplicador según la edad del vehículo
     * @param edad Edad del vehículo en años
     * @return Multiplicador por edad
     */
    private static double obtenerMultiplicadorEdad(int edad) {
        if (edad < 0) return 1.0;
        if (edad >= MULTIPLICADORES_EDAD.length) {
            return MULTIPLICADORES_EDAD[MULTIPLICADORES_EDAD.length - 1];
        }
        return MULTIPLICADORES_EDAD[edad];
    }

    /**
     * Obtiene el multiplicador según cilindraje
     * @param cilindraje Cilindraje del motor en cc
     * @return Multiplicador por cilindraje
     */
    private static double obtenerMultiplicadorCilindraje(int cilindraje) {
        if (cilindraje <= 1000) {
            return MULTIPLICADOR_MOTOR_PEQUENIO;
        } else if (cilindraje <= 2000) {
            return MULTIPLICADOR_MOTOR_MEDIANO;
        } else if (cilindraje <= 3000) {
            return MULTIPLICADOR_MOTOR_GRANDE;
        } else {
            return MULTIPLICADOR_MOTOR_LUJO;
        }
    }

    /**
     * Obtiene el multiplicador según tipo de uso
     * @param tipoUso Tipo de uso del vehículo ("particular" o "publico")
     * @return Multiplicador por uso
     */
    private static double obtenerMultiplicadorUso(String tipoUso) {
        if ("publico".equalsIgnoreCase(tipoUso)) {
            return MULTIPLICADOR_USO_PUBLICO;
        } else {
            return MULTIPLICADOR_USO_PARTICULAR;
        }
    }

    /**
     * Devuelve el desglose detallado del cálculo para mostrar en la interfaz
     * @param vehiculo El vehículo a calcular
     * @return Cadena con los detalles del cálculo
     */
    public static String obtenerDetallesCalculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            return "Datos de vehículo inválidos";
        }

        StringBuilder detalles = new StringBuilder();
        double impuestoBase = vehiculo.getAvaluoComercial() * TASA_IMPUESTO_BASE;

        detalles.append("Detalles del Cálculo:\n");
        detalles.append(String.format("Avalúo Comercial: $%.2f\n", vehiculo.getAvaluoComercial()));
        detalles.append(String.format("Tasa Base de Impuesto: %.1f%%\n", TASA_IMPUESTO_BASE * 100));
        detalles.append(String.format("Impuesto Inicial: $%.2f\n", impuestoBase));

        double multiplicadorEdad = obtenerMultiplicadorEdad(vehiculo.getEdad());
        detalles.append(String.format("Multiplicador por Edad (%d años): %.2f\n", vehiculo.getEdad(), multiplicadorEdad));

        double multiplicadorMotor = obtenerMultiplicadorCilindraje(vehiculo.getCilindraje());
        detalles.append(String.format("Multiplicador por Cilindraje (%dcc): %.2f\n", vehiculo.getCilindraje(), multiplicadorMotor));

        double multiplicadorUso = obtenerMultiplicadorUso(vehiculo.getTipoUso());
        detalles.append(String.format("Multiplicador por Uso (%s): %.2f\n", vehiculo.getTipoUso(), multiplicadorUso));

        double impuestoFinal = calcularImpuesto(vehiculo);
        detalles.append(String.format("\nMonto Final del Impuesto: $%.2f", impuestoFinal));

        return detalles.toString();
    }
}
