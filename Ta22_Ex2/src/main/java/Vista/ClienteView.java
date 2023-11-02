package Vista;

import Controlador.ClienteController;
import Modelo.ClienteModel;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//Intento de implementar lombok
@Data
public class ClienteView {
    private JFrame frame;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField direccionField;
    private JTextField dniField;
    private JTextField fechaField;
    private JTextArea outputArea;
    private ClienteController controlador;
    private static JButton mostrarClientesButton;
    private static JButton agregarClienteButton;
    private static JButton editarClienteButton;
    private static JButton eliminarClienteButton;
    private static JButton videosButton;



    public ClienteView() {
        frame = new JFrame("Gestión de Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(740, 390);
        frame.getContentPane().setLayout(new GridLayout(2, 2)); // Cambiamos el diseño a 2x2

        // Rejilla 1: Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(6, 2)); // 6 filas, 2 columnas
        inputPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        inputPanel.add(apellidoField);
        inputPanel.add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        inputPanel.add(direccionField);
        inputPanel.add(new JLabel("DNI:"));
        dniField = new JTextField();
        inputPanel.add(dniField);
        inputPanel.add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
        fechaField = new JTextField();
        inputPanel.add(fechaField);
        frame.getContentPane().add(inputPanel); // Añadir al panel principal

        // Rejilla 2: Panel de opciones
        JPanel menuPanel = new JPanel(new GridLayout(4, 1));
        agregarClienteButton = new JButton("Agregar Cliente");
        menuPanel.add(agregarClienteButton);
        editarClienteButton = new JButton("Editar Cliente");
        menuPanel.add(editarClienteButton);
        eliminarClienteButton = new JButton("Eliminar Cliente");
        menuPanel.add(eliminarClienteButton);
        
        videosButton = new JButton("Ver videos");
        menuPanel.add(videosButton);
        
        frame.getContentPane().add(menuPanel); // Añadir al panel principal

        // Rejilla 3: Área de salida (outputArea)
        outputArea = new JTextArea();
        frame.getContentPane().add(new JScrollPane(outputArea)); // Añadir al panel principal

        frame.setVisible(true);
    }
    public void setControlador(ClienteController controlador) {
        this.controlador = controlador;
    }

    public void mostrarClientes() {
        controlador.mostrarClientes();
    }

    public void mostrarClientesEnVista(List<ClienteModel> clientes) {
        StringBuilder builder = new StringBuilder();
        for (ClienteModel cliente : clientes) {
            builder.append(cliente.toString()).append("\n");
        }
        outputArea.setText(builder.toString());
    }

    public void limpiarCampos() {
        nombreField.setText("");
        apellidoField.setText("");
        direccionField.setText("");
        dniField.setText("");
        fechaField.setText("");
    }
    public JButton getMostrarClientesButton() {
        return mostrarClientesButton;
    }

    public JButton getAgregarClienteButton() {
        return agregarClienteButton;
    }

    public JButton getEditarClienteButton() {
        return editarClienteButton;
    }

    public JButton getEliminarClienteButton() {
        return eliminarClienteButton;
    }
    public JButton getvideosButton() {
        return videosButton;
    }
    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getApellidoField() {
        return apellidoField;
    }

    public JTextField getDireccionField() {
        return direccionField;
    }

    public JTextField getDniField() {
        return dniField;
    }

    public JTextField getFechaField() {
        return fechaField;
    }
    public JFrame getFrame() {
        return frame;
    }

}
