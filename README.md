# Sistema de Cálculo de Impuestos de Vehículos

Una aplicación Java Swing que implementa el patrón de diseño MVC (Modelo-Vista-Controlador) para calcular impuestos de vehículos basado en varios factores.

## Características

- **Cálculo Automático**: Basado en la edad del vehículo, cilindraje, avalúo comercial y tipo de uso
- **Interfaz Amigable**: GUI Swing con validación de entrada
- **Desglose Detallado**: Muestra los detalles del cálculo paso a paso
- **Tasas Actualizables**: Tasas de impuesto y multiplicadores fácilmente configurables

## Estructura del Proyecto

```
src/
├── MainImpuestoCarro.java           # Clase principal para ejecutar la aplicación
├── model/
│   ├── Vehiculo.java                # Modelo de datos del vehículo
│   └── CalculadoraImpuestos.java    # Lógica de cálculo de impuestos
├── view/
│   └── VistaImpuestoCarro.java      # Interfaz GUI Swing
└── controller/
    └── ControladorImpuestoCarro.java # Controlador MVC
```

## Lógica de Cálculo de Impuestos

El sistema calcula el impuesto basado en:

1. **Tasa Base de Impuesto**: 2% del avalúo comercial
2. **Multiplicador por Edad**: Disminuye con la edad del vehículo (depreciación)
   - 0 años: 1.0 (100%)
   - 1 año: 0.95 (95%)
   - 2 años: 0.90 (90%)
   - 10+ años: 0.50 (50%)
3. **Multiplicador por Cilindraje**:
   - ≤ 1000cc: 0.8 (80%)
   - 1001-2000cc: 1.0 (100%)
   - 2001-3000cc: 1.3 (130%)
   - > 3000cc: 1.6 (160%)
4. **Multiplicador por Tipo de Uso**:
   - Particular: 1.0 (100%)
   - Público: 1.5 (150%)

## Cómo Ejecutar

### Opción 1: Usando el script (Mac/Linux)
```bash
./compile_and_run.sh
```

### Opción 2: Compilación manual
```bash
# Crear directorio de salida
mkdir out

# Compilar todos los archivos Java
javac -d out src/**/*.java src/*.java

# Ejecutar la aplicación
cd out
java MainImpuestoCarro
```

### Opción 3: Usando un IDE
1. Importar el proyecto en su IDE Java (Eclipse, IntelliJ IDEA, etc.)
2. Establecer la carpeta fuente como `src/`
3. Ejecutar `MainImpuestoCarro.java`

## Uso

1. **Iniciar la aplicación**
2. **Llenar la información del vehículo**:
   - Marca (ej. Toyota)
   - Modelo (ej. Camry)
   - Año (ej. 2020)
   - Cilindraje en cc (ej. 2000)
   - Avalúo Comercial en dólares (ej. 25000)
   - Tipo de Uso (Particular o Público)
3. **Hacer clic en "Calcular Impuesto"** para ver los resultados
4. **Ver el desglose detallado** en el área de resultados
5. **Usar "Limpiar Campos"** para restablecer todas las entradas

## Validación de Entrada

La aplicación valida:
- Todos los campos están llenos
- El año está entre 1900-2024
- El cilindraje es un número positivo
- El avalúo comercial es un número positivo

## Ejemplo de Cálculo

Para un Toyota Camry 2020 (Uso particular, 2000cc, valor $25,000):
- Impuesto base: $25,000 × 2% = $500
- Multiplicador por edad (4 años): 0.80
- Multiplicador por cilindraje (2000cc): 1.0
- Multiplicador por tipo de uso (particular): 1.0
- **Impuesto final: $500 × 0.80 × 1.0 × 1.0 = $400**

## Requisitos

- Java 8 o superior
- Sin dependencias externas (usa solo librerías estándar de Java)

## Arquitectura MVC

- **Modelo**: Las clases `Vehiculo` y `CalculadoraImpuestos` manejan la lógica de negocio
- **Vista**: La clase `VistaImpuestoCarro` gestiona la GUI Swing
- **Controlador**: La clase `ControladorImpuestoCarro` coordina entre el Modelo y la Vista