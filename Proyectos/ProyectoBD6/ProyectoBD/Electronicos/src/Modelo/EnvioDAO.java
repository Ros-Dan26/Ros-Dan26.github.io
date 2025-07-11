package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnvioDAO {
    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarEnvio(Envio envio) {
        String sql = "INSERT INTO ENVIOS (ID_ENVIO, FECHA, ID_VENTA, ID_DIR, ID_EMPLEADO) VALUES (?,?,?,?,?)";
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, envio.getID_ENVIO());
            ps.setDate(2, new java.sql.Date(envio.getFECHA().getTime()));
            ps.setInt(3, envio.getID_VENTA());
            ps.setString(4, envio.getID_DIR());
            ps.setString(5, envio.getID_EMPLEADO());

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
    public List<Envio> ListarEnvios() {
        List<Envio> listaEnvios = new ArrayList<>();
        String sql = "SELECT * FROM ENVIOS";
        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Envio envio = new Envio();
                envio.setID_ENVIO(rs.getString("ID_ENVIO"));
               envio.setFECHA(rs.getDate("FECHA"));
                envio.setID_VENTA(rs.getInt("ID_VENTA"));
                envio.setID_DIR(rs.getString("ID_DIR"));
                envio.setID_EMPLEADO(rs.getString("ID_EMPLEADO"));

                listaEnvios.add(envio);
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
        return listaEnvios;
    }

    public boolean EliminarEnvio(String idEnvio) {
        String sql = "DELETE FROM ENVIOS WHERE ID_ENVIO=?";

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idEnvio);

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

    public boolean ModificarEnvio(Envio envio) {
        String sql = "UPDATE ENVIOS SET FECHA=?, ID_VENTA=?, ID_DIR=?, ID_EMPLEADO=? WHERE ID_ENVIO=?";

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(envio.getFECHA().getTime()));
            ps.setInt(2, envio.getID_VENTA());
            ps.setString(3, envio.getID_DIR());
            ps.setString(4, envio.getID_EMPLEADO());
            ps.setString(5, envio.getID_ENVIO());

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

    public List<Envio> BuscarEnvio(String idEnvio, String fecha, String idVenta, String idDir, String idEmpleado) {
        List<Envio> listaEnvios = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ENVIOS WHERE 1=1");

        if (idEnvio != null && !idEnvio.isEmpty()) {
            sql.append(" AND ID_ENVIO LIKE ?");
        }

        if (fecha != null && !fecha.isEmpty()) {
            sql.append(" AND FECHA LIKE ?");
        }

        if (idVenta != null && !idVenta.isEmpty()) {
            sql.append(" AND ID_VENTA LIKE ?");
        }

        if (idDir != null && !idDir.isEmpty()) {
            sql.append(" AND ID_DIR LIKE ?");
        }

        if (idEmpleado != null && !idEmpleado.isEmpty()) {
            sql.append(" AND ID_EMPLEADO LIKE ?");
        }

        try {
            con = on.getConnection();
            ps = con.prepareStatement(sql.toString());

            int paramIndex = 1;

            if (idEnvio != null && !idEnvio.isEmpty()) {
                ps.setString(paramIndex++, "%" + idEnvio + "%");
            }

            if (fecha != null && !fecha.isEmpty()) {
                ps.setString(paramIndex++, "%" + fecha + "%");
            }

            if (idVenta != null && !idVenta.isEmpty()) {
                ps.setString(paramIndex++, "%" + idVenta + "%");
            }

            if (idDir != null && !idDir.isEmpty()) {
                ps.setString(paramIndex++, "%" + idDir + "%");
            }

            if (idEmpleado != null && !idEmpleado.isEmpty()) {
                ps.setString(paramIndex++, "%" + idEmpleado + "%");
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Envio envio = new Envio();
                envio.setID_ENVIO(rs.getString("ID_ENVIO"));
                envio.setFECHA(rs.getDate("FECHA"));

                envio.setID_VENTA(rs.getInt("ID_VENTA"));
                envio.setID_DIR(rs.getString("ID_DIR"));
                envio.setID_EMPLEADO(rs.getString("ID_EMPLEADO"));

                listaEnvios.add(envio);
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
        return listaEnvios;
    }
}
