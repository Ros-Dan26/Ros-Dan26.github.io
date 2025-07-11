package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentasDAO 
{
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;

    // Otros métodos y propiedades...

    public void registrarVenta(Ventas venta) 
    {
        try 
        {
            String query = "INSERT INTO VENTAS (ID_VENTA, FECHA_VENTA, ID_CLIENTE, ID_EMPLEADO, ID_M, TOTAL_VENTA) VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, venta.getID_VENTA());
            ps.setDate(2, java.sql.Date.valueOf(venta.getFECHA_VENTA()));
            ps.setString(3, venta.getID_CLIENTE());
            ps.setString(4, venta.getID_EMPLEADO());
            ps.setString(5, venta.getID_M());
            ps.setInt(6, venta.getTOTAL_VENTA());

            // Ejecutar la consulta
            ps.executeUpdate();

            // Cerrar recursos
            ps.close();
            con.close();

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

}

