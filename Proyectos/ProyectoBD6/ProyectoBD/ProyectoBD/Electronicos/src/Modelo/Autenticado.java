package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Autenticado 
{
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;

    public boolean verificarCredenciales(String nombreUsuario, String contrasena) 
    {
        String sql = "SELECT * FROM EMPLEADO WHERE NOMBRE_EMPLEADO = ? AND ID_EMPLEADO = ?";
        
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            // Utiliza executeQuery para consultas SELECT
            // Utiliza executeUpdate para consultas que modifican datos
            // Utiliza execute para consultas que no sabes si son SELECT o no
            return ps.executeQuery().next();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            // Aquí deberías manejar la excepción de manera apropiada, por ejemplo, lanzando una nueva excepción
        }
        return false;
    }
}
