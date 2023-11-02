package Controlador;

import Modelo.DAO;
import Modelo.VideoModel;
import Vista.VideoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

public class VideoController {
    private DAO videoDAO;
    private VideoView videoView;

    public VideoController(DAO videoDAO, VideoView videoView) {
        this.videoDAO = videoDAO;
        this.videoView = videoView;
        this.videoView.setVideoController(this);

        this.videoView.getAgregarVideoButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String title = videoView.pedirTituloVideo();
                String director = videoView.pedirDirectorVideo();
                int clienteId = videoView.pedirClienteIdVideo();

                if (clienteId != -1) {
                    VideoModel video = new VideoModel();
                    video.setTitle(title);
                    video.setDirector(director);
                    video.setClienteId(clienteId);

                    videoDAO.agregarVideo(video);
                    mostrarVideos();
                } else {
                    JOptionPane.showMessageDialog(videoView.getFrame(), "El ID del cliente debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.videoView.getEditarVideoButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idText = videoView.pedirIdVideo();

                if (idText != null && !idText.isEmpty()) {
                    int id = Integer.parseInt(idText);

                    // Comprobar si el video con el ID especificado existe
                    if (videoDAO.existeVideo(id)) {
                        String title = videoView.pedirTituloVideo();
                        String director = videoView.pedirDirectorVideo();
                        int clienteId = videoView.pedirClienteIdVideo();

                        if (clienteId != -1) {
                            VideoModel video = new VideoModel();
                            video.setId(id);
                            video.setTitle(title);
                            video.setDirector(director);
                            video.setClienteId(clienteId);

                            videoDAO.actualizarVideo(video);
                            mostrarVideos();
                        } else {
                            JOptionPane.showMessageDialog(videoView.getFrame(), "El ID del cliente debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(videoView.getFrame(), "El video con ID " + id + " no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.videoView.getEliminarVideoButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String idText = videoView.pedirIdVideo();
            	
                if (idText != null && !idText.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idText);
                        int confirm = JOptionPane.showConfirmDialog(videoView.getFrame(), "¿Está seguro de que desea eliminar el video con ID " + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                        
                        if (confirm == JOptionPane.YES_OPTION) {
                            videoDAO.eliminarVideo(id);
                            mostrarVideos();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(videoView.getFrame(), "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }

    public void mostrarVideos() {
        List<VideoModel> videos = videoDAO.obtenerVideos();
        videoView.mostrarVideosEnVista(videos);
    }



    public void iniciar() {
        videoView.mostrarVideos();
    }
}
