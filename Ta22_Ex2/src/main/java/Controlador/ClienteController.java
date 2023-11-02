package Controlador;

import Modelo.DAO;
import Modelo.ClienteModel;
import Vista.ClienteView;
import Vista.VideoView;

import java.util.List;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteController {
    private DAO clienteDAO;
    private ClienteView clienteView;

    public ClienteController(DAO clienteDAO, ClienteView clienteView) {
    	// TODO: Poner ActionListeners aqui
        this.clienteDAO = clienteDAO;
        this.clienteView = clienteView;
        this.clienteView.setControlador(this);

        this.clienteView.getAgregarClienteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = clienteView.getNombreField().getText();
                String apellido = clienteView.getApellidoField().getText();
                String direccion = clienteView.getDireccionField().getText();
                String dniText = clienteView.getDniField().getText();
                String fecha = clienteView.getFechaField().getText();

                if (!nombre.isEmpty() && !apellido.isEmpty() && !direccion.isEmpty() && !dniText.isEmpty() && !fecha.isEmpty()) {
                    if (dniText.length() == 8) { // Verifica que la longitud sea 8
                        try {
                            int dni = Integer.parseInt(dniText);
                            ClienteModel cliente = new ClienteModel();
                            cliente.setNombre(nombre);
                            cliente.setApellido(apellido);
                            cliente.setDireccion(direccion);
                            cliente.setDni(dni);
                            cliente.setFecha(fecha);
                            clienteDAO.agregarCliente(cliente);
                            clienteView.limpiarCampos();
                            mostrarClientes();
                        } catch (NumberFormatException ex) {
                            // Mostrar un mensaje de error si el campo DNI no es un número válido
                            JOptionPane.showMessageDialog(clienteView.getFrame(), "El campo DNI debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(clienteView.getFrame(), "El campo DNI debe tener exactamente 8 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Mostrar un mensaje de error si algún campo está vacío
                    JOptionPane.showMessageDialog(clienteView.getFrame(), "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.clienteView.getEditarClienteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idText = JOptionPane.showInputDialog(clienteView.getFrame(), "Ingrese el ID del cliente que desea editar:");
                if (idText != null && !idText.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idText);
                        String nombre = JOptionPane.showInputDialog(clienteView.getFrame(), "Ingrese el nuevo nombre del cliente:");
                        String apellido = JOptionPane.showInputDialog(clienteView.getFrame(), "Ingrese el nuevo apellido del cliente:");
                        String direccion = JOptionPane.showInputDialog(clienteView.getFrame(), "Ingrese la nueva dirección del cliente:");
                        String dniText = JOptionPane.showInputDialog(clienteView.getFrame(), "Ingrese el nuevo DNI del cliente:");
                        String fecha = JOptionPane.showInputDialog(clienteView.getFrame(), "Ingrese la nueva fecha de nacimiento (YYYY-MM-DD) del cliente:");

                        if (nombre != null && apellido != null && direccion != null && dniText != null && fecha != null) {
                            if (dniText.length() == 8) { // Verifica que la longitud sea 8
                                try {
                                    int dni = Integer.parseInt(dniText);

                                    ClienteModel cliente = new ClienteModel();
                                    cliente.setId(id);
                                    cliente.setNombre(nombre);
                                    cliente.setApellido(apellido);
                                    cliente.setDireccion(direccion);
                                    cliente.setDni(dni);
                                    cliente.setFecha(fecha);

                                    clienteDAO.actualizarCliente(cliente);
                                    clienteView.limpiarCampos();
                                    mostrarClientes();
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(clienteView.getFrame(), "El campo DNI debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(clienteView.getFrame(), "El campo DNI debe tener exactamente 8 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(clienteView.getFrame(), "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(clienteView.getFrame(), "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        this.clienteView.getEliminarClienteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idText = JOptionPane.showInputDialog(clienteView.getFrame(), "Ingrese el ID del cliente que desea eliminar:");
                if (idText != null && !idText.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idText);
                        int confirm = JOptionPane.showConfirmDialog(clienteView.getFrame(), "¿Está seguro de que desea eliminar al cliente con ID " + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                        	clienteDAO.eliminarCliente(id);

                            // Limpiar campos y actualizar la lista de clientes
                            clienteView.limpiarCampos();
                            mostrarClientes();
                        }
                    } catch (NumberFormatException ex) {
                        // Mostrar un mensaje de error si el ID no es un número válido
                        JOptionPane.showMessageDialog(clienteView.getFrame(), "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.clienteView.getvideosButton().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarVideosButtonClicked();
        	}
        });
    }

    public void iniciar() {
        clienteView.mostrarClientes(); // Muestra los clientes al iniciar la aplicación
    }

    public void mostrarClientes() {
        List<ClienteModel> clientes = clienteDAO.obtenerClientes();
        clienteView.mostrarClientesEnVista(clientes);
    }
    private void mostrarVideosButtonClicked() {
        // Código para abrir VideoView
        VideoView videoView = new VideoView();
        DAO videoDAO = new DAO();
        VideoController videoController = new VideoController(videoDAO, videoView);
        videoController.iniciar();
    }
}
