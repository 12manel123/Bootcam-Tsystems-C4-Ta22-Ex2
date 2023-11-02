package Modelo;

public class ClienteModel {
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private int dni;
    private String fecha;

    // Constructor
    public ClienteModel() {
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return "Id: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Direccion: " + direccion + ", DNI: " + dni + ", Fecha:" + fecha;
    }
}