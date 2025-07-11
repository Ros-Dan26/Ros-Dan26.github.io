package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAO {
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarSucursal(Sucursal sucursal) {
        String sql = "INSERT INTO SUCURSAL (ID_DE_LA_SUCURSAL, NOMBRE_SUCURSAL, TELEFONO_S, CORREO_S, PAGINA_WEB_ID_PW) VALUES (?,?,?,?,?)";
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, sucursal.getID_DE_LA_SUCURSAL());
            ps.setString(2, sucursal.getNOMBRE_SUCURSAL());
            ps.setString(3, sucursal.getTELEFONO_S());
            ps.setString(4, sucursal.getCORREO_S());
            ps.setString(5, sucursal.getPAGINA_WEB_ID_PW());

            ps.execute();
            return true;

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

    public List<Sucursal> ListarSucursales() {
        List<Sucursal> listaSucursales = new ArrayList<>();
        String sql = "SELECT * FROM SUCURSAL";
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setID_SUCURSAL(rs.getString("ID_DE_LA_SUCURSAL"));
                sucursal.setNOMBRE_SUCURSAL(rs.getString("NOMBRE_SUCURSAL"));
                sucursal.setTELEFONO_S(rs.getString("TELEFONO_S"));
                sucursal.setCORREO_S(rs.getString("CORREO_S"));
                sucursal.setPAGINA_WEB_ID_PW(rs.getString("PAGINA_WEB_ID_PW"));

                listaSucursales.add(sucursal);
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
        return listaSucursales;
    }

    public boolean EliminarSucursal(String idSucursal) {
        String sql = "DELETE FROM SUCURSAL WHERE ID_DE_LA_SUCURSAL=?";

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idSucursal);

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

    public boolean ModificarSucursal(Sucursal sucursal) {
        String sql = "UPDATE SUCURSAL SET NOMBRE_SUCURSAL=?, TELEFONO_S=?, CORREO_S=?, PAGINA_WEB_ID_PW=? WHERE ID_DE_LA_SUCURSAL=?";

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sucursal.getNOMBRE_SUCURSAL());
            ps.setString(2, sucursal.getTELEFONO_S());
            ps.setString(3, sucursal.getCORREO_S());
            ps.setString(4, sucursal.getPAGINA_WEB_ID_PW());
            ps.setString(5, sucursal.getID_DE_LA_SUCURSAL());

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

   public List<Sucursal> BuscarSucursal(String id_sucursal, String nombre_sucursal, String telefono_s, String correo_s, String id_pw) {
    List<Sucursal> listaSucursales = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM SUCURSAL WHERE 1=1");

    if (id_sucursal != null && !id_sucursal.isEmpty()) {
        sql.append(" AND ID_DE_LA_SUCURSAL LIKE ?");
    }

    if (nombre_sucursal != null && !nombre_sucursal.isEmpty()) {
        sql.append(" AND NOMBRE_SUCURSAL LIKE ?");
    }

    if (telefono_s != null && !telefono_s.isEmpty()) {
        sql.append(" AND TELEFONO_S LIKE ?");
    }

    if (correo_s != null && !correo_s.isEmpty()) {
        sql.append(" AND CORREO_S LIKE ?");
    }

    if (id_pw != null && !id_pw.isEmpty()) {
        sql.append(" AND ID_PW LIKE ?");
    }

    try {
        con = on.getConnection();
        ps = con.prepareStatement(sql.toString());

        int paramIndex = 1;

        if (id_sucursal != null && !id_sucursal.isEmpty()) {
            ps.setString(paramIndex++, "%" + id_sucursal + "%");
        }

        if (nombre_sucursal != null && !nombre_sucursal.isEmpty()) {
            ps.setString(paramIndex++, "%" + nombre_sucursal + "%");
        }

        if (telefono_s != null && !telefono_s.isEmpty()) {
            ps.setString(paramIndex++, "%" + telefono_s + "%");
        }

        if (correo_s != null && !correo_s.isEmpty()) {
            ps.setString(paramIndex++, "%" + correo_s + "%");
        }

        if (id_pw != null && !id_pw.isEmpty()) {
            ps.setString(paramIndex++, "%" + id_pw + "%");
        }

        rs = ps.executeQuery();

        while (rs.next()) {
            Sucursal sucursal = new Sucursal();
            sucursal.setID_SUCURSAL(rs.getString("ID_DE_LA_SUCURSAL"));
            sucursal.setNOMBRE_SUCURSAL(rs.getString("NOMBRE_SUCURSAL"));
            sucursal.setTELEFONO_S(rs.getString("TELEFONO_S"));
            sucursal.setCORREO_S(rs.getString("CORREO_S"));
            sucursal.setPAGINA_WEB_ID_PW(rs.getString("PAGINA_WEB_ID_PW"));

            listaSucursales.add(sucursal);
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
    return listaSucursales;
}

}

