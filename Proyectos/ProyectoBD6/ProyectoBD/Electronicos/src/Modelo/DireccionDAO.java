
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
public class DireccionDAO 
{
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public List MostrarDireccion()
    {
        List<Direccion> MostrarDir = new ArrayList<>();
        String sql = "SELECT * FROM DIRECCION";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Direccion dir = new Direccion();
                dir.setID_DIR(rs.getString("ID_DIR"));
                dir.setALCALDIA_MUNICIPIO(rs.getString("ALCALDIA_MUNICIPIO"));
                dir.setCP(rs.getString("CP"));
                dir.setCALLE(rs.getString("CALLE"));
                dir.setNUM_EXT(rs.getString("NUM_EXT"));
                dir.setCOLONIA(rs.getString("COLONIA"));
                dir.setID_ES(rs.getString("ID_ES"));
                MostrarDir.add(dir);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
        }
        
        return MostrarDir;
    }
    
    public boolean AgregarDireccion(Direccion dir)
    {
        String sql = "INSERT INTO DIRECCION (ID_DI, ALCALDIA_MUNICIPIO, CP, CALLE, NUM_EXT, COLONIA, ID_ES) "
                   + "VALUES(?,?,?,?,?,?,?)";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, dir.getID_DIR());
            ps.setString(2, dir.getALCALDIA_MUNICIPIO());
            ps.setString(3, dir.getCP());
            ps.setString(4, dir.getCALLE());
            ps.setString(5, dir.getNUM_EXT());
            ps.setString(6, dir.getCOLONIA());
            ps.setString(7, dir.getID_ES());
            ps.execute();
            return true;
        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean ModificarDireccion(Direccion dir)
    {
        String sql = "UPDATE DIRECCION SET ALCALDIA_MUNICIPIO = ?, CP = ?, CALLE = ?, NUM_EXT = ?, COLONIA = ?, ID_ES = ?"
                    + "WHERE ID_DIR = ?";
        try 
        {
            ps = con.prepareStatement(sql);
            ps.setString(1, dir.getALCALDIA_MUNICIPIO());
            ps.setString(2, dir.getCP());
            ps.setString(3, dir.getCALLE());
            ps.setString(4, dir.getNUM_EXT());
            ps.setString(5, dir.getCOLONIA());
            ps.setString(6, dir.getID_ES());
            ps.setString(7, dir.getID_DIR());
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
    
    public boolean EliminarDireccion(String idDireccion)
    {
        String sql = "DELETE FROM DIRECCION WHERE ID_DIRECCION = ?";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idDireccion);
            
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
    public List<Direccion> BuscarDireccion(String idDireccion, String alcaldiaMunicipio, String cP, String calle ,String numExt, String colonia, String idEstado) 
    {
    List<Direccion> BuscarDir = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM DIRECCION WHERE 1=1");

    if (idDireccion != null && !idDireccion.isEmpty()) 
    {
        sql.append(" AND ID_DIRECCION LIKE ?");
    }

    if (alcaldiaMunicipio != null && !alcaldiaMunicipio.isEmpty()) 
    {
        sql.append(" AND ALCALDIA_MUNICIPIO LIKE ?");
    }

    if (cP != null && !cP.isEmpty()) 
    {
        sql.append(" AND CP LIKE ?");
    }

    if (calle != null && !calle.isEmpty()) 
    {
        sql.append(" AND CALLE LIKE ?");
    }
    
    if (numExt != null && !numExt.isEmpty()) 
    {
        sql.append(" AND NUM_EXT LIKE ?");
    }
    
    if (colonia != null && !colonia.isEmpty()) 
    {
        sql.append(" AND COLONIA LIKE ?");
    }
    
    if (idEstado != null && !idEstado.isEmpty()) 
    {
        sql.append(" AND ID_ESTADO LIKE ?");
    }


    try {
	con = on.getConnection();
        //ps = con.prepareStatement(sql);
        ps = con.prepareStatement(sql.toString());
        

        int paramIndex = 1;

        if (idDireccion != null && !idDireccion.isEmpty()) 
        {
            ps.setString(paramIndex++, "%" + idDireccion + "%");
        }

        if (alcaldiaMunicipio != null && !alcaldiaMunicipio.isEmpty()) 
        {
            ps.setString(paramIndex++, "%" + alcaldiaMunicipio + "%");
        }

        if (cP != null && !cP.isEmpty()) 
        {
            ps.setString(paramIndex++, "%" + cP + "%");
        }

        if (calle != null && !calle.isEmpty()) 
        {
            ps.setString(paramIndex++, "%" + calle + "%");
        }
        
        if (numExt != null && !numExt.isEmpty()) 
        {
            ps.setString(paramIndex++, "%" + numExt + "%");
        }

        if (colonia != null && !colonia.isEmpty()) 
        {
            ps.setString(paramIndex++, "%" + colonia + "%");
        }

        if (idEstado != null && !idEstado.isEmpty()) 
        {
            ps.setString(paramIndex++, "%" + idEstado + "%");
        }
        
        rs = ps.executeQuery();

        while (rs.next()) 
        { 
            Direccion dir = new Direccion();
            
            dir.setID_DIR(rs.getString("ID_DIR"));
            dir.setALCALDIA_MUNICIPIO(rs.getString("ALCALDIA_MUNICIPIO"));
            dir.setCP(rs.getString("CP"));
            dir.setCALLE(rs.getString("CALLE"));
            dir.setNUM_EXT(rs.getString("NUM_EXT"));
            dir.setCOLONIA(rs.getString("COLONIA"));
            dir.setID_ES(rs.getString("ID_ES"));
            BuscarDir.add(dir);
        }

    } 
    catch (SQLException e) 
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
    return BuscarDir;
}
}
