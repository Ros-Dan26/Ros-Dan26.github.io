package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DetalleCompraDAO {
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarDetalleCompra(DetalleCompra detalleCompra) {
        String sql = "INSERT INTO DETALLE_DE_COMPRA (ID_DETALLEC, CANTIDAD_COMPRA, ID_COMPRA, ID_PRODUCTO) VALUES (?,?,?,?)";
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, detalleCompra.getIdDetalleCompra());
            ps.setString(2, detalleCompra.getCantidadCompra());
            ps.setString(3, detalleCompra.getIdCompra());
            ps.setString(4, detalleCompra.getIdProducto());

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

    public List<DetalleCompra> ListarDetalleCompra() {
    List<DetalleCompra> listaDetalleCompra = new ArrayList<>();
    String sql = "SELECT * FROM DETALLE_DE_COMPRA";
    try {
        con = on.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setIdDetalleCompra(rs.getString("ID_DETALLEC"));
            detalleCompra.setCantidadCompra(rs.getString("CANTIDAD_COMPRA"));
            detalleCompra.setIdCompra(rs.getString("ID_COMPRA"));
            detalleCompra.setIdProducto(rs.getString("ID_PRODUCTO"));

            listaDetalleCompra.add(detalleCompra);
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
    return listaDetalleCompra;
}


    public boolean EliminarDetalleCompra(String idDetalleCompra) {
        String sql = "DELETE FROM DETALLE_DE_COMPRA WHERE ID_DETALLEC=?";

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idDetalleCompra);

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

    public boolean ModificarDetalleCompra(DetalleCompra detalleCompra) {
        String sql = "UPDATE DETALLE_DE_COMPRA SET CANTIDAD_COMPRA=?, ID_COMPRA=?, ID_PRODUCTO=? WHERE ID_DETALLEC=?";

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, detalleCompra.getCantidadCompra());
            ps.setString(2, detalleCompra.getIdCompra());
            ps.setString(3, detalleCompra.getIdProducto());
            ps.setString(4, detalleCompra.getIdDetalleCompra());

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
        public List<DetalleCompra> BuscarDetalleCompra(String idDetalleCompra, String cantidadCompra, String idCompra, String idProducto) {
    List<DetalleCompra> listaDetalleCompra = new ArrayList<>();
    String sql = "SELECT * FROM DETALLE_DE_COMPRA WHERE ID_DETALLEC LIKE ? AND CANTIDAD_COMPRA LIKE ? AND ID_COMPRA LIKE ? AND ID_PRODUCTO LIKE ?";
    try {
        con = on.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + idDetalleCompra + "%");
        ps.setString(2, "%" + cantidadCompra + "%");
        ps.setString(3, "%" + idCompra + "%");
        ps.setString(4, "%" + idProducto + "%");
        
        rs = ps.executeQuery();

        while (rs.next()) {
            DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setIdDetalleCompra(rs.getString("ID_DETALLEC"));
            detalleCompra.setCantidadCompra(rs.getString("CANTIDAD_COMPRA"));
            detalleCompra.setIdCompra(rs.getString("ID_COMPRA"));
            detalleCompra.setIdProducto(rs.getString("ID_PRODUCTO"));

            listaDetalleCompra.add(detalleCompra);
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
    return listaDetalleCompra;
}
}
