package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class DetalleVentaDAO {
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
     public int obtenerUltimoIdDetalle() {
        int ultimoIddetalle = 0;
        String sql = "SELECT MAX(ID_DETALLEV) FROM DETALLE_VENTA";

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                ultimoIddetalle = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ultimoIddetalle;
    }

    public int obtenerNuevoIdDetalle() {
        // Obtener el último ID_VENTA de la base de datos
        int ultimoIddetalle = obtenerUltimoIdDetalle();

        // Incrementar el último ID_VENTA para la nueva venta
        return ultimoIddetalle + 1;
    }
    
        public int RegistrarDetalle(DetalleVenta Dv){
        String sql = "INSERT INTO DETALLE_VENTA (ID_DETALLEV, CANTIDAD_PRODUCTOS_VENDIDOS,ID_PRODUCTO,ID_VENTA) VALUES(?,?,?,?)";
        try{
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Dv.getID_DETALLEV());
            ps.setInt(2, Dv.getCANTIDAD_PRODUCTOS_VENDIDOS());
            ps.setString(3, Dv.getID_PRODUCTO());
            ps.setInt(4, Dv.getID_VENTA());
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.toString());
        }finally{
           try{
               con.close();
           }catch (SQLException e){
                System.out.println(e.toString());
           }
        }
        return r;
    }
        
   public DetalleVenta buscarDetallePorID(int idDetalle) {
        DetalleVenta detalle = new DetalleVenta();
        String sql = "SELECT * FROM DETALLE_VENTA WHERE ID_DETALLEV = ?";
        
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idDetalle);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                // Crear un objeto Cliente con los datos obtenidos de la base de datos
                detalle.setID_DETALLEV(rs.getInt("D_DETALLEV"));
                detalle.setCANTIDAD_PRODUCTOS_VENDIDOS(rs.getInt("CANTIDAD_PRODUCTOS_VENDIDOS"));
                detalle.setID_PRODUCTO(rs.getString("ID_PRODUCTO"));
                detalle.setID_VENTA(rs.getInt("ID_VENTA"));
                
                // ... y así sucesivamente para cada campo
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return detalle;
    }
    
     public List MostrarDetalleV()
    {
        List<DetalleVenta> MostrarDetalle = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_VENTA";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next())
            {
                DetalleVenta dv = new DetalleVenta();
                
                dv.setID_DETALLEV(rs.getInt("D_DETALLEV"));
                dv.setCANTIDAD_PRODUCTOS_VENDIDOS(rs.getInt("CANTIDAD_PRODUCTOS_VENDIDOS"));
                dv.setID_PRODUCTO(rs.getString("ID_PRODUCTO"));
                dv.setID_VENTA(rs.getInt("ID_VENTA"));
                MostrarDetalle.add(dv);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
        }
        return MostrarDetalle;
    }
    
    public boolean AgregarDetalle(DetalleVenta dv)
    {
        String sql = "INSERT INTO DETALLE_VENTA (ID_DETALLEV, CANTIDAD_PRODUCTOS_VENDIDOS, ID_PRODUCTO, ID_VENTA) "
                   + "VALUES(?,?,?,?)";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, dv.getID_DETALLEV());
            ps.setInt(2, dv.getCANTIDAD_PRODUCTOS_VENDIDOS());
            ps.setString(3, dv.getID_PRODUCTO());
            ps.setInt(4, dv.getID_VENTA());
            ps.execute();
            return true;
        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean ModificarDetalle(DetalleVenta dv)
    {
        String sql = "UPDATE DETALLE_VENTA SET CANTIDAD_PRODUCTOS_VENDIDOS = ?, ID_PRODUCTO = ?, ID_VENTA = ?"
                    + "WHERE ID_DETALLEV = ?";
        try 
        {
            ps = con.prepareStatement(sql);
             ps.setInt(1, dv.getID_DETALLEV());
            ps.setInt(2, dv.getCANTIDAD_PRODUCTOS_VENDIDOS());
            ps.setString(3, dv.getID_PRODUCTO());
            ps.setInt(4, dv.getID_VENTA());
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
    
    public boolean EliminarDetalle(int iddetalle)
    {
        String sql = "DELETE FROM DETALLE_VENTA WHERE ID_DETALLEV = ?";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, iddetalle);

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
    public List<DetalleVenta> BuscarDetalle(String iddetalle, String cantidad, String idpro, String idvent) 
    {
    List<DetalleVenta> BuscarDet = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM DETALLE_VENTA WHERE 1=1");

    if (iddetalle != null && !iddetalle.isEmpty()) 
    {
        sql.append(" AND ID_DETALLEV LIKE ?");
    }

    if (cantidad != null && !cantidad.isEmpty()) 
    {
        sql.append(" AND CANTIDAD_PRODUCTOS_VENDIDOS LIKE ?");
    }

    if (idpro != null && !idpro.isEmpty()) 
    {
        sql.append(" AND ID_PRODUCTO LIKE ?");
    }

    if (idvent != null && !idvent.isEmpty()) 
    {
        sql.append(" AND ID_VENTA LIKE ?");
    }


    try {
	con = on.getConnection();
        //ps = con.prepareStatement(sql);
        ps = con.prepareStatement(sql.toString());
        

        int paramIndex = 1;

        if (iddetalle != null && !iddetalle.isEmpty()) {
            ps.setString(paramIndex++, "%" + iddetalle + "%");
        }

        if (cantidad != null && !cantidad.isEmpty()) {
            ps.setString(paramIndex++, "%" + cantidad + "%");
        }

        if (idpro != null && !idpro.isEmpty()) {
            ps.setString(paramIndex++, "%" + idpro + "%");
        }

        if (idvent != null && !idvent.isEmpty()) {
            ps.setString(paramIndex++, "%" + idvent + "%");
        }
        
        rs = ps.executeQuery();

        while (rs.next()) 
        {
            DetalleVenta dv = new DetalleVenta();
            
                 dv.setID_DETALLEV(rs.getInt("D_DETALLEV"));
                dv.setCANTIDAD_PRODUCTOS_VENDIDOS(rs.getInt("CANTIDAD_PRODUCTOS_VENDIDOS"));
                dv.setID_PRODUCTO(rs.getString("ID_PRODUCTO"));
                dv.setID_VENTA(rs.getInt("ID_VENTA"));
           
 
            BuscarDet.add(dv);
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
    return BuscarDet;
}     
    
}
