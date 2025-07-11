package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarProveedor(Proveedor prov) {
        String sql = "INSERT INTO PROVEEDOR (ID_PROVEEDOR, NOMBRE_PROVEEDOR, TELEFONO_P, CORREO_P, CONTACTO, ID_PRODUCTO) VALUES (?,?,?,?,?,?)";
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, prov.getID_PROVEEDOR());
            ps.setString(2, prov.getNOMBRE_PROVEEDOR());
            ps.setString(3, prov.getTELEFONO_P());
            ps.setString(4, prov.getCORREO_P());
            ps.setString(5, prov.getCONTACTO());
            ps.setString(6, prov.getID_PRODUCTO());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public List<Proveedor> ListarProveedores() {
        List<Proveedor> ListaProv = new ArrayList<>();
        String sql = "SELECT * FROM PROVEEDOR";
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor prov = new Proveedor();
                prov.setID_PROVEEDOR(rs.getString("ID_PROVEEDOR"));
                prov.setNOMBRE_PROVEEDOR(rs.getString("NOMBRE_PROVEEDOR"));
                prov.setTELEFONO_P(rs.getString("TELEFONO_P"));
                prov.setCORREO_P(rs.getString("CORREO_P"));
                prov.setCONTACTO(rs.getString("CONTACTO"));
                prov.setID_PRODUCTO(rs.getString("ID_PRODUCTO"));

                ListaProv.add(prov);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaProv;
    }

    public boolean EliminarProveedor(String idProveedor) {
        String sql = "DELETE FROM PROVEEDOR WHERE ID_PROVEEDOR=?";

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idProveedor);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            // Cerrar recursos en el bloque finally
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public boolean ModificarProveedor(Proveedor prov) {
        String sql = "UPDATE PROVEEDOR SET NOMBRE_PROVEEDOR=?, TELEFONO_P=?, CORREO_P=?, CONTACTO=?, ID_PRODUCTO=? WHERE ID_PROVEEDOR=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getNOMBRE_PROVEEDOR());
            ps.setString(2, prov.getTELEFONO_P());
            ps.setString(3, prov.getCORREO_P());
            ps.setString(4, prov.getCONTACTO());
            ps.setString(5, prov.getID_PRODUCTO());
            ps.setString(6, prov.getID_PROVEEDOR());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            // Cerrar recursos en el bloque finally
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    public List<Proveedor> BuscarProveedor(String idProveedor, String nombreProveedor, String telefonoProveedor, String correoProveedor, String contactoProveedor, String idProductoProveedor) {
    List<Proveedor> ListaProv = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM PROVEEDOR WHERE 1=1");

    if (idProveedor != null && !idProveedor.isEmpty()) {
        sql.append(" AND ID_PROVEEDOR LIKE ?");
    }

    if (nombreProveedor != null && !nombreProveedor.isEmpty()) {
        sql.append(" AND NOMBRE_PROVEEDOR LIKE ?");
    }

    if (telefonoProveedor != null && !telefonoProveedor.isEmpty()) {
        sql.append(" AND TELEFONO_P LIKE ?");
    }

    if (correoProveedor != null && !correoProveedor.isEmpty()) {
        sql.append(" AND CORREO_P LIKE ?");
    }

    if (contactoProveedor != null && !contactoProveedor.isEmpty()) {
        sql.append(" AND CONTACTO LIKE ?");
    }

    if (idProductoProveedor != null && !idProductoProveedor.isEmpty()) {
        sql.append(" AND ID_PRODUCTO LIKE ?");
    }

    try {
        con = on.getConnection();
        ps = con.prepareStatement(sql.toString());

        int paramIndex = 1;

        if (idProveedor != null && !idProveedor.isEmpty()) {
            ps.setString(paramIndex++, "%" + idProveedor + "%");
        }

        if (nombreProveedor != null && !nombreProveedor.isEmpty()) {
            ps.setString(paramIndex++, "%" + nombreProveedor + "%");
        }

        if (telefonoProveedor != null && !telefonoProveedor.isEmpty()) {
            ps.setString(paramIndex++, "%" + telefonoProveedor + "%");
        }

        if (correoProveedor != null && !correoProveedor.isEmpty()) {
            ps.setString(paramIndex++, "%" + correoProveedor + "%");
        }

        if (contactoProveedor != null && !contactoProveedor.isEmpty()) {
            ps.setString(paramIndex++, "%" + contactoProveedor + "%");
        }

        if (idProductoProveedor != null && !idProductoProveedor.isEmpty()) {
            ps.setString(paramIndex++, "%" + idProductoProveedor + "%");
        }

        rs = ps.executeQuery();

        while (rs.next()) {
            Proveedor prov = new Proveedor();
            prov.setID_PROVEEDOR(rs.getString("ID_PROVEEDOR"));
            prov.setNOMBRE_PROVEEDOR(rs.getString("NOMBRE_PROVEEDOR"));
            prov.setTELEFONO_P(rs.getString("TELEFONO_P"));
            prov.setCORREO_P(rs.getString("CORREO_P"));
            prov.setCONTACTO(rs.getString("CONTACTO"));
            prov.setID_PRODUCTO(rs.getString("ID_PRODUCTO"));

            ListaProv.add(prov);
        }

    } catch (SQLException e) {
        System.out.println(e.toString());
    } finally {
        // Cerrar recursos en el bloque finally
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    return ListaProv;
}

}

