
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Rosal
 */
public class ClienteDAO 
{
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public List MostrarCliente()
    {
        List<Cliente> MostrarCli = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next())
            {
                Cliente cli = new Cliente();
                cli.setID_CLIENTE(rs.getString("ID_CLIENTE"));
                cli.setNOMBRE_CLIENTE(rs.getString("NOMBRE_CLIENTE"));
                cli.setTELEFONO_CLIENTE(rs.getString("TELEFONO_CLIENTE"));
                cli.setCORREO_CLIENTE(rs.getString("CORREO_CLIENTE"));
                MostrarCli.add(cli);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
        }
        return MostrarCli;
    }
    
    public boolean AgregarCliente(Cliente cli)
    {
        String sql = "INSERT INTO CLIENTE (ID_CLIENTE, NOMBRE_CLIENTE, TELEFONO_CLIENTE, CORREO_CLIENTE) "
                   + "VALUES(?,?,?,?)";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, cli.getID_CLIENTE());
            ps.setString(2, cli.getNOMBRE_CLIENTE());
            ps.setString(3, cli.getTELEFONO_CLIENTE());
            ps.setString(4, cli.getCORREO_CLIENTE());
            ps.execute();
            return true;
        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean ModificarCliente(Cliente cli)
    {
        String sql = "UPDATE CLIENTE SET NOMBRE_CLIENTE = ?, TELEFONO_CLIENTE = ?, CORREO_CLIENTE = ?"
                    + "WHERE ID_CLIENTE = ?";
        try 
        {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNOMBRE_CLIENTE());
            ps.setString(2, cli.getTELEFONO_CLIENTE());
            ps.setString(3, cli.getCORREO_CLIENTE());
            ps.setString(4, cli.getID_CLIENTE());
            ps.execute();
            return true;
        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
            return false;
        }
        finally 
        {
        // Cerrar recursos en el bloque finally
            try 
            {
                con.close();
            }    
            catch (SQLException e) 
            {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean EliminarCliente(String idCliente)
    {
        String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idCliente);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
            return false;
        }
        finally 
        {
        // Cerrar recursos en el bloque finally
            try 
            {
                if (ps != null) 
                {
                    ps.close();
                }
                if (con != null) 
                {
                    con.close();
                }
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.toString());
            }
        }
    }
    
    // Metodo Buscar
    public List<Cliente> BuscarCliente(String idCliente, String nombreCliente, String telefonoCliente, String correoCliente) 
    {
    List<Cliente> BuscarCli = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM CLIENTE WHERE 1=1");

    if (idCliente != null && !idCliente.isEmpty()) 
    {
        sql.append(" AND ID_CLIENTE LIKE ?");
    }

    if (nombreCliente != null && !nombreCliente.isEmpty()) 
    {
        sql.append(" AND NOMBRE_CLIENTE LIKE ?");
    }

    if (telefonoCliente != null && !telefonoCliente.isEmpty()) 
    {
        sql.append(" AND TELEFONO_CLIENTE LIKE ?");
    }

    if (correoCliente != null && !correoCliente.isEmpty()) 
    {
        sql.append(" AND CORREO_CLIENTE LIKE ?");
    }


    try {
	con = on.getConnection();
        //ps = con.prepareStatement(sql);
        ps = con.prepareStatement(sql.toString());
        

        int paramIndex = 1;

        if (idCliente != null && !idCliente.isEmpty()) {
            ps.setString(paramIndex++, "%" + idCliente + "%");
        }

        if (nombreCliente != null && !nombreCliente.isEmpty()) {
            ps.setString(paramIndex++, "%" + nombreCliente + "%");
        }

        if (telefonoCliente != null && !telefonoCliente.isEmpty()) {
            ps.setString(paramIndex++, "%" + telefonoCliente + "%");
        }

        if (correoCliente != null && !correoCliente.isEmpty()) {
            ps.setString(paramIndex++, "%" + correoCliente + "%");
        }
        
        rs = ps.executeQuery();

        while (rs.next()) 
        {
            Cliente cli = new Cliente();
           
            cli.setID_CLIENTE(rs.getString("ID_CLIENTE"));
            cli.setNOMBRE_CLIENTE(rs.getString("NOMBRE_CLIENTE"));
            cli.setTELEFONO_CLIENTE(rs.getString("TELEFONO_CLIENTE"));
            cli.setCORREO_CLIENTE(rs.getString("CORREO_CLIENTE"));
            BuscarCli.add(cli);
        }

    } catch (SQLException e) 
    {
        System.out.println(e.toString());
    } 
    finally 
    {
        // Cerrar recursos en el bloque finally
        try 
        {
            if (ps != null) 
            {
                ps.close();
            }
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
        }
    }
    return BuscarCli;
}
}
