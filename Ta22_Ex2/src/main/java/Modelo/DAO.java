package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection connection;

    public DAO() {
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/T22_Cliente";
            String user = "root";
            String password = "password";

            connection = DriverManager.getConnection(jdbcURL, user, password);
            System.out.println("Conectado");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos");
            
        }
    }

    public List<ClienteModel> obtenerClientes() {
        List<ClienteModel> clientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cliente";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ClienteModel cliente = new ClienteModel();
                cliente.setId(result.getInt("id"));
                cliente.setNombre(result.getString("nombre"));
                cliente.setApellido(result.getString("apellido"));
                cliente.setDireccion(result.getString("direccion"));
                cliente.setDni(result.getInt("dni"));
                cliente.setFecha(result.getString("fecha"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
    
 // En ClienteDAO

    public void agregarCliente(ClienteModel cliente) {
        try {
            String sql = "INSERT INTO cliente (nombre, apellido, direccion, dni, fecha) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getDireccion());
            statement.setInt(4, cliente.getDni());
            statement.setString(5, cliente.getFecha());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCliente(ClienteModel cliente) {
        try {
            String sql = "UPDATE cliente SET nombre=?, apellido=?, direccion=?, dni=?, fecha=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getDireccion());
            statement.setInt(4, cliente.getDni());
            statement.setString(5, cliente.getFecha());
            statement.setInt(6, cliente.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCliente(int id) {
        try {
            String sql = "DELETE FROM cliente WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void agregarVideo(VideoModel video) {
        try {
            String sql = "INSERT INTO videos (title, director, cli_id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, video.getTitle());
            statement.setString(2, video.getDirector());
            statement.setInt(3, video.getClienteId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<VideoModel> obtenerVideos() {
        List<VideoModel> videos = new ArrayList<>();

        try {
            // Realiza la consulta SQL para obtener los videos desde la base de datos
            String sql = "SELECT * FROM videos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                VideoModel video = new VideoModel();
                video.setId(result.getInt("id"));
                video.setTitle(result.getString("title"));
                video.setDirector(result.getString("director"));
                video.setClienteId(result.getInt("cli_id"));

                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videos;
    }


    public void actualizarVideo(VideoModel video) {
        try {
            String sql = "UPDATE videos SET title=?, director=?, cli_id=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, video.getTitle());
            statement.setString(2, video.getDirector());
            statement.setInt(3, video.getClienteId());
            statement.setInt(4, video.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarVideo(int id) {
        try {
            String sql = "DELETE FROM videos WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeVideo(int id) {
        try {
            String sql = "SELECT id FROM videos WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            return result.next(); // Devuelve true si se encuentra al menos un resultado
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // En caso de error, asumimos que el video no existe
    }

    
}
