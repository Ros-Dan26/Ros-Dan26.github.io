package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
    private Connection con;

    public Connection getConnection() 
    {
        try 
        {
            // Cargar el controlador de Oracle JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establecer la conexión a la base de datos Oracle
            String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
            String usuario = "usuario_venta";
            String contraseña = "12345";
            
            con = DriverManager.getConnection(url, usuario, contraseña);
            return con;
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            e.printStackTrace(); // Puedes cambiar esto por un manejo más adecuado del error
        }
        return null;
    }

    public void cerrarConexion() 
    {
        try 
        {
            if (con != null && !con.isClosed()) 
            {
                con.close();
            }
        } catch (SQLException e) 
        {
            e.printStackTrace(); // Puedes cambiar esto por un manejo más adecuado del error
        }
    }
}
