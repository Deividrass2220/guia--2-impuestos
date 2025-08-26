package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaImpuestoCarro extends JFrame {

    // Componentes de entrada
    private JTextField campoMarca;
    private JTextField campoModelo;
    private JTextField campoAnio;
    private JTextField campoCilindraje;
    private JTextField campoAvaluo;
    private JComboBox<String> comboTipoUso;

    // Botones
    private JButton botonCalcular;
    private JButton botonLimpiar;

    // Área de resultados
    private JTextArea areaResultado;

    public VistaImpuestoCarro() {
        inicializarComponentes();
        configurarDiseno();
        configurarVentana();
    }

    /**
     * Inicializa todos los componentes de la GUI
     */
    private void inicializarComponentes() {
        // Campos de texto
        campoMarca = new JTextField(20);
        campoModelo = new JTextField(20);
        campoAnio = new JTextField(20);
        campoCilindraje = new JTextField(20);
        campoAvaluo = new JTextField(20);

        // ComboBox tipo de uso
        comboTipoUso = new JComboBox<>(new String[]{"Particular", "Publico"});

        // Botones
        botonCalcular = new JButton("Calcular Impuesto");
        botonLimpiar = new JButton("Limpiar Campos");

        // Área de resultados
        areaResultado = new JTextArea(10, 40);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        areaResultado.setBorder(BorderFactory.createTitledBorder("Resultados del Cálculo"));
    }

    /**
     * Configura el diseño de la ventana
     */
    private void configurarDiseno() {
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel de entrada
        JPanel panelEntrada = crearPanelEntrada();

        // Panel de botones
        JPanel panelBotones = crearPanelBotones();

        // Panel de resultados
        JPanel panelResultado = new JPanel(new BorderLayout());
        panelResultado.add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        // Agregar paneles
        panelPrincipal.add(panelEntrada, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(panelResultado, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    /**
     * Crea el panel de entrada con todos los campos
     */
    private JPanel crearPanelEntrada() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Información del Vehículo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Marca
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        panel.add(campoMarca, gbc);

        // Modelo
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panel.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        panel.add(campoModelo, gbc);

        // Año
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Año:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoAnio, gbc);

        // Cilindraje
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Cilindraje (cc):"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoCilindraje, gbc);

        // Avalúo Comercial
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Avalúo Comercial ($):"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoAvaluo, gbc);

        // Tipo de Uso
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Tipo de Uso:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboTipoUso, gbc);

        return panel;
    }

    /**
     * Crea el panel de botones
     */
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(botonCalcular);
        panel.add(botonLimpiar);

        // Acción para limpiar campos
        botonLimpiar.addActionListener(e -> limpiarCampos());

        return panel;
    }

    /**
     * Configura propiedades de la ventana
     */
    private void configurarVentana() {
        setTitle("Sistema de Cálculo de Impuestos de Vehículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        pack();
        setLocationRelativeTo(null); // Centrar ventana
        setMinimumSize(new Dimension(500, 600));
    }

    // Métodos getters para obtener valores de entrada
    public String getMarca() { return campoMarca.getText().trim(); }
    public String getModelo() { return campoModelo.getText().trim(); }
    public String getAnio() { return campoAnio.getText().trim(); }
    public String getCilindraje() { return campoCilindraje.getText().trim(); }
    public String getAvaluo() { return campoAvaluo.getText().trim(); }
    public String getTipoUso() { return comboTipoUso.getSelectedItem().toString().toLowerCase(); }

    /**
     * Mostrar resultados en el área de texto
     */
    public void mostrarResultado(String resultado) {
        areaResultado.setText(resultado);
    }

    /**
     * Mostrar mensaje de error
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Mostrar mensaje de éxito
     */
    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Limpiar todos los campos
     */
    public void limpiarCampos() {
        campoMarca.setText("");
        campoModelo.setText("");
        campoAnio.setText("");
        campoCilindraje.setText("");
        campoAvaluo.setText("");
        comboTipoUso.setSelectedIndex(0);
        areaResultado.setText("");
    }

    /**
     * Agregar listener al botón Calcular
     */
    public void agregarListenerCalcular(ActionListener listener) {
        botonCalcular.addActionListener(listener);
    }

    /**
     * Validar entradas
     */
    public boolean validarEntradas() {
        if (getMarca().isEmpty()) {
            mostrarError("Por favor ingrese la marca del vehículo.");
            campoMarca.requestFocus();
            return false;
        }
        if (getModelo().isEmpty()) {
            mostrarError("Por favor ingrese el modelo del vehículo.");
            campoModelo.requestFocus();
            return false;
        }
        try {
            int anio = Integer.parseInt(getAnio());
            if (anio < 1900 || anio > 2024) {
                mostrarError("Por favor ingrese un año válido (1900-2024).");
                campoAnio.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("Por favor ingrese un año válido.");
            campoAnio.requestFocus();
            return false;
        }
        try {
            int cil = Integer.parseInt(getCilindraje());
            if (cil <= 0) {
                mostrarError("Ingrese un cilindraje mayor que 0.");
                campoCilindraje.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("Por favor ingrese un cilindraje válido.");
            campoCilindraje.requestFocus();
            return false;
        }
        try {
            double val = Double.parseDouble(getAvaluo());
            if (val <= 0) {
                mostrarError("Ingrese un avalúo comercial mayor que 0.");
                campoAvaluo.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarError("Por favor ingrese un avalúo válido.");
            campoAvaluo.requestFocus();
            return false;
        }
        return true;
    }
}
