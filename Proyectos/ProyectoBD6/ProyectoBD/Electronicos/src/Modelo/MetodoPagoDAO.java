package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class MetodoPagoDAO {

    Connection con;
    Conexion on = new Conexion();
    PreparedStatement ps;

    // Constructor que inicializa la conexión
    public MetodoPagoDAO() {
        this.con = on.getConnection();
    }

    // Método para listar los métodos de pago desde la base de datos
    public List<String> listarMetodosDePago() {
        List<String> metodosDePago = new ArrayList<>();
        String query = "SELECT METODO_DE_PAGO FROM METODO";

        try {
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Agregar cada método de pago a la lista
                String nombreMetodo = rs.getString("nombre_metodo");
                metodosDePago.add(nombreMetodo);
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

        return metodosDePago;
    }

    // Otros métodos de tu clase MetodoPagoDAO...

    public void listarMetodosDePago(JComboBox<String> cbxMetodoPago) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
