package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JComboBox;
public class VentasDAO {
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;

    // Otros métodos y propiedades...
    
    
        public Ventas buscarVentaPorID(int idVenta) {
        Ventas venta = new Ventas();
        String sql = "SELECT * FROM VENTA WHERE ID_VENTA = ?";
        
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                // Crear un objeto Cliente con los datos obtenidos de la base de datos
                venta.setID_VENTA(rs.getInt("ID_VENTA"));
                venta.setID_CLIENTE(rs.getString("ID_CLIENTE"));
                venta.setNOMBRE_EMPLEADO(rs.getString("NOMBRE_EMPLEADO"));
                venta.setID_M(rs.getString("ID_M"));
                venta.setTOTAL_VENTA(rs.getFloat("TOTAL_VENTA"));
                
                
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
        
        return venta;
    }
    
    public void MetodoPago(JComboBox  <String>Pago){
        String sql = "SELECT  METODO FROM METODO_DE_PAGO";
        try{
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
            Pago.addItem(rs.getString("METODO"));
        }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public int RegistrarVenta(Ventas v) {
    String sql = "INSERT INTO VENTA (ID_VENTA,ID_CLIENTE,NOMBRE_EMPLEADO,ID_M,TOTAL_VENTA,FECHA_VENTA) VALUES(?,?,?,?,?,?)";
    
    try {
        con = on.getConnection();
        
        // Verificar si la conexión es null
        if (con != null) {
            ps = con.prepareStatement(sql);
            
            // Asignar valores a los parámetros
            ps.setInt(1, v.getID_VENTA());
            ps.setString(2, v.getID_CLIENTE());
            ps.setString(3, v.getNOMBRE_EMPLEADO());
            ps.setString(4, v.getID_M());
            ps.setFloat(5, v.getTOTAL_VENTA());

            // Obtener la fecha de la venta y convertirla al formato adecuado
            java.util.Date utilDate = v.getFECHA_VENTA();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(6, sqlDate);

            // Verificar si ps es null antes de ejecutar la consulta
            if (ps != null) {
                ps.execute();
            } else {
                System.out.println("Error: PreparedStatement es null.");
            }
        } else {
            System.out.println("Error: Connection es null.");
        }
    } catch (SQLException e) {
        // Manejar la excepción y mostrar información detallada
        e.printStackTrace();
        System.out.println("Error al ejecutar la consulta: " + e.getMessage());
    } finally {
        // Cerrar recursos en el bloque finally
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return r;
}

 public int obtenerUltimoIdVenta() {
        int ultimoIdVenta = 0;
        String sql = "SELECT MAX(ID_VENTA) FROM VENTA";

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                ultimoIdVenta = rs.getInt(1);
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

        return ultimoIdVenta;
    }

    public int obtenerNuevoIdVenta() {
        // Obtener el último ID_VENTA de la base de datos
        int ultimoIdVenta = obtenerUltimoIdVenta();

        // Incrementar el último ID_VENTA para la nueva venta
        return ultimoIdVenta + 1;
    }
    
    public int IdVenta(){
        int id = 0;
        String sql ="SELECT MAX (ID_VENTA) FROM VENTA";
                try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
                return id;
    }
    
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
    
    public boolean ActualizarStock(int cant, String cod ){
        String sql = "UPDATE PRODUCTO SET INVENTARIO_EN_STOCK=? WHERE ID_PRODUCTO =?";
        try{
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    


}

