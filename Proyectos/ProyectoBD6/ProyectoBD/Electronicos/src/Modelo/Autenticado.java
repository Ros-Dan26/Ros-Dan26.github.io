package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autenticado {
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean verificarCredenciales(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM EMPLEADO WHERE NOMBRE_EMPLEADO = ? AND ID_EMPLEADO = ?";
        
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Empleado obtenerEmpleadoAutenticado(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM EMPLEADO WHERE NOMBRE_EMPLEADO = ? AND ID_EMPLEADO = ?";
        Empleado empleado = null;

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String idEmpleado = rs.getString("ID_EMPLEADO");
                String nombreEmpleado = rs.getString("NOMBRE_EMPLEADO");
                empleado = new Empleado(idEmpleado, nombreEmpleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar ResultSet, PreparedStatement y Connection en el bloque finally
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return empleado;
    }
}
