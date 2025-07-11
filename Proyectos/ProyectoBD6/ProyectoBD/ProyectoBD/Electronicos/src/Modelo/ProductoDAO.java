
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO 
{
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProductos(Producto pro)
    {
        String sql = " INSERT INTO PRODUCTO (ID_PRODUCTO, NOMBRE_PRODUCTO, CATEGORÍA, PRECIO,MODELO, MARCA, DESCRIPCION,CARACTERISTICAS, NOMBRE_DEL_SO, INVENTARIO_EN_STOCK ) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, pro.getID_PRODUCTO());
            ps.setString(2, pro.getNOMBRE_PRODUCTO());
            ps.setString(3, pro.getCATEGORÍA());
            ps.setInt(4, pro.getPRECIO());
            ps.setString(5, pro.getMODELO());
            ps.setString(6, pro.getMARCA());
            ps.setString(7, pro.getDESCRIPCION());
            ps.setString(8, pro.getCARACTERISTICAS());
            ps.setString(9, pro.getNOMBRE_DEL_SO());
            ps.setInt(10,pro.getINVENTARIO_EN_STOCK());
            ps.execute();
            return true;

        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
            return false;
        }
    }
    
       public List ListarProductos() 
       {
        List<Producto> ListaPro = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTO";
        try 
        {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) 
            {
                Producto pro = new Producto();
                pro.setID_PRODUCTO(rs.getString("ID_PRODUCTO"));
                pro.setNOMBRE_PRODUCTO(rs.getString("NOMBRE_PRODUCTO"));
                pro.setCATEGORÍA(rs.getString("CATEGORÍA"));
                pro.setPRECIO(rs.getInt("PRECIO"));
                pro.setMODELO(rs.getString("MODELO"));
                pro.setMARCA(rs.getString("MARCA"));
                pro.setDESCRIPCION(rs.getString("DESCRIPCION"));
                pro.setCARACTERISTICAS(rs.getString("CARACTERISTICAS"));
                pro.setNOMBRE_DEL_SO(rs.getString("NOMBRE_DEL_SO"));
                pro.setINVENTARIO_EN_STOCK(rs.getInt("INVENTARIO_EN_STOCK"));
                ListaPro.add(pro);
            }

        } 
        catch (SQLException e) 
        {
            System.out.println(e.toString());
        } 
        return ListaPro;
    }
       
       public boolean EliminarProducto(String idProducto) 
       {
            String sql = "DELETE FROM PRODUCTO WHERE ID_PRODUCTO=?";
    
    try 
    {
        con = on.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, idProducto);

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
       public boolean ModificarProducto(Producto pro) 
       {
    String sql = "UPDATE PRODUCTO SET NOMBRE_PRODUCTO=?, CATEGORÍA=?, PRECIO=?, MODELO=?, MARCA=?, DESCRIPCION=?, CARACTERISTICAS=?, NOMBRE_DEL_SO=?, INVENTARIO_EN_STOCK=? WHERE ID_PRODUCTO=?";
    
    try 
    {
        ps = con.prepareStatement(sql);
        ps.setString(1, pro.getNOMBRE_PRODUCTO());
        ps.setString(2, pro.getCATEGORÍA());
        ps.setInt(3, pro.getPRECIO());
        ps.setString(4, pro.getMODELO());
        ps.setString(5, pro.getMARCA());
        ps.setString(6, pro.getDESCRIPCION());
        ps.setString(7, pro.getCARACTERISTICAS());
        ps.setString(8, pro.getNOMBRE_DEL_SO());
        ps.setInt(9, pro.getINVENTARIO_EN_STOCK());
        ps.setString(10, pro.getID_PRODUCTO());
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

    
}
