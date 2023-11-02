package Modelo;

public class VideoModel {
    private int id;
    private String title;
    private String director;
    private int clienteId;

    // Constructor
    public VideoModel() {
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + title + ", Director: " + director + ", ID del Cliente: " + clienteId;
    }
}
