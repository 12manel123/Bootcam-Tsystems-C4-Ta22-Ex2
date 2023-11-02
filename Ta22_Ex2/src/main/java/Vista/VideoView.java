package Vista;

import Controlador.VideoController;
import Modelo.VideoModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VideoView {
    private JFrame frame;
    private JTextArea videoOutputArea;
    private VideoController videoController;
    private JButton mostrarVideosButton;
    private JButton agregarVideoButton;
    private JButton editarVideoButton;
    private JButton eliminarVideoButton;

    public VideoView() {
        frame = new JFrame("Gestión de Videos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(740, 390);
        frame.getContentPane().setLayout(new GridLayout(2, 2)); // Cambiamos el diseño a 2x2

        // Rejilla 3: Área de salida (videoOutputArea)
        videoOutputArea = new JTextArea();
        frame.getContentPane().add(new JScrollPane(videoOutputArea)); // Añadir al panel principal

        // Rejilla 2: Panel de opciones para videos
        JPanel videoMenuPanel = new JPanel(new GridLayout(3, 1));
        agregarVideoButton = new JButton("Agregar Video");
        videoMenuPanel.add(agregarVideoButton);
        editarVideoButton = new JButton("Editar Video");
        videoMenuPanel.add(editarVideoButton);
        eliminarVideoButton = new JButton("Eliminar Video");
        videoMenuPanel.add(eliminarVideoButton);
        frame.getContentPane().add(videoMenuPanel); // Añadir al panel principal

        frame.setVisible(true);
    }

    public void setVideoController(VideoController videoController) {
        this.videoController = videoController;
    }

    public void mostrarVideos() {
        videoController.mostrarVideos();
    }

    public void mostrarVideosEnVista(List<VideoModel> videos) {
        StringBuilder builder = new StringBuilder();
        for (VideoModel video : videos) {
            builder.append(video.toString()).append("\n");
        }
        videoOutputArea.setText(builder.toString());
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getMostrarVideosButton() {
        return mostrarVideosButton;
    }

    public JButton getAgregarVideoButton() {
        return agregarVideoButton;
    }

    public JButton getEditarVideoButton() {
        return editarVideoButton;
    }

    public JButton getEliminarVideoButton() {
        return eliminarVideoButton;
    }
    public String pedirTituloVideo() {
        return JOptionPane.showInputDialog(frame, "Ingrese el título del video:");
    }

    public String pedirDirectorVideo() {
        return JOptionPane.showInputDialog(frame, "Ingrese el director del video:");
    }
    public String pedirIdVideo() {
        return JOptionPane.showInputDialog(frame, "Ingrese el ID del video:");
    }

    public int pedirClienteIdVideo() {
        String clienteIdText = JOptionPane.showInputDialog(frame, "Ingrese el ID del cliente asociado al video:");
        try {
            return Integer.parseInt(clienteIdText);
        } catch (NumberFormatException ex) {
            return -1; // Puedes manejar el error como desees en el controlador.
        }
    }
}
