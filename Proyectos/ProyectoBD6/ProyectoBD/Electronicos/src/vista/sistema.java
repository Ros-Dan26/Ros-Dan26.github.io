
package vista;

import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Ventas;
import Modelo.VentasDAO;
import Modelo.Empleado;
import Modelo.MetodoPago;
import Modelo.MetodoPagoDAO;
import Modelo.Autenticado;
import Modelo.Envio;
import Modelo.EnvioDAO;
import Modelo.DetalleCompra;
import Modelo.DetalleCompraDAO;
import Modelo.DetalleVenta;
import Modelo.DetalleVentaDAO;
import Modelo.Direccion;
import Modelo.DireccionDAO;
import Modelo.Proveedor;
import Modelo.ProveedorDao;
import Modelo.Sucursal;
import Modelo.SucursalDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author renem
 */
public class sistema extends javax.swing.JFrame {
    
    Producto pro = new Producto();
    ProductoDAO prodao = new ProductoDAO();
    Ventas ven = new Ventas();
    VentasDAO vendao = new VentasDAO();
    DetalleVenta dv = new DetalleVenta();
    DetalleVentaDAO dvdao = new DetalleVentaDAO();
    Empleado emp = new Empleado();
    Autenticado au = new Autenticado();
    Cliente cli = new Cliente();
    ClienteDAO clidao = new ClienteDAO();
    MetodoPago mp = new MetodoPago();
    Proveedor Proveedor = new Proveedor();
    ProveedorDao ProveedorDao = new ProveedorDao();
    DetalleCompra detalleCompra = new DetalleCompra(); // Asegúrate de tener una clase DetalleCompra y un objeto DetalleCompraDAO instanciado
    DetalleCompraDAO detalleCompraDAO = new DetalleCompraDAO(); // Asegúrate de tener una clase DetalleCompraDAO instanciado
    Envio Envio = new Envio();
    EnvioDAO EnvioDAO = new EnvioDAO();
    Direccion dir = new Direccion();
    DireccionDAO dirdao = new DireccionDAO();
    Sucursal sucursal = new Sucursal();
    SucursalDAO sucursalDAO = new SucursalDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();

    int item;
    float Totalpagar = 0; 
    public sistema() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtIdDetalleV.setVisible(false);
        AutoCompleteDecorator.decorate(cbxMetodoPago);
        vendao.MetodoPago(cbxMetodoPago);
        mostrarFechaActual();
    }
    
     private Empleado empleadoAutenticado;
     private String contrasena;
     private VentasDAO ventasDAO;
     public sistema(Empleado empleadoAutenticado,String contrasena) {
        initComponents();
        this.empleadoAutenticado = empleadoAutenticado;
        this.contrasena = contrasena;

        // Establece el nombre del empleado en el campo txtVendedorVenta
        txtVendedorVenta.setText(empleadoAutenticado.getNOMBRE_EMPLEADO());
        ventasDAO = new VentasDAO();

        // Llama al método para llenar el JComboBox de métodos de pago
        cargarMetodosDePago();
        mostrarFechaActual();
        realizarNuevaVenta();
        DetalleV();        
        // Otros códigos de configuración de la ventana...

        this.setLocationRelativeTo(null);
    }

private void mostrarFechaActual() {
    // Obtiene la fecha actual en formato Date
    Date fechaActual = new Date();

    // Convierte la fecha a un formato deseado, por ejemplo, "dd/MM/yyyy"
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String fechaFormateada = formatter.format(fechaActual);

    // Asigna la fecha al TextField
    txtFechaVenta.setText(fechaFormateada);
}
     private void cargarMetodosDePago(){
        // Suponiendo que tienes un JComboBox llamado comboBoxMetodosPago en tu interfaz
         AutoCompleteDecorator.decorate(cbxMetodoPago);
        ventasDAO.MetodoPago(cbxMetodoPago);
    }
         // ... Otro código de la clase ...
        private void realizarNuevaVenta() {
        // Obtener el nuevo ID_VENTA
        int nuevoIdVenta = ventasDAO.obtenerNuevoIdVenta();
        // Mostrar el nuevo ID_VENTA en el JTextField txtIdVenta
        txtIdVenta.setText(String.valueOf(nuevoIdVenta));

        // Crear un objeto Ventas con los datos de la nueva venta
        Ventas nuevaVenta = new Ventas();
        nuevaVenta.setID_VENTA(nuevoIdVenta);
        // Configurar otros atributos de la nueva venta...

        // Actualizar la GUI u otras operaciones necesarias después de la venta
        // ...
    }
        
          private void DetalleV() {
        // Obtener el nuevo ID_VENTA
        int ultimoIddetalle = dvdao.obtenerNuevoIdDetalle();
        // Mostrar el nuevo ID_VENTA en el JTextField txtIdVenta
        txtIdDetalleV.setText(String.valueOf(ultimoIddetalle));
        
     
    }

    public void ListaPro(){
        List<Producto> ListarPro = prodao.ListarProductos();
        modelo = (DefaultTableModel) TableProducto.getModel();
        Object[] ob = new Object[10];
        
        for(int i=0; i<ListarPro.size();i++){
            ob[0] = ListarPro.get(i).getID_PRODUCTO();
            ob[1] = ListarPro.get(i).getNOMBRE_PRODUCTO();
            ob[2] = ListarPro.get(i).getCATEGORÍA();
            ob[3] = ListarPro.get(i).getPRECIO();
            ob[4] = ListarPro.get(i).getMODELO();
            ob[5] = ListarPro.get(i).getMARCA();
            ob[6] = ListarPro.get(i).getDESCRIPCION();
            ob[7] = ListarPro.get(i).getCARACTERISTICAS();
            ob[8] = ListarPro.get(i).getNOMBRE_DEL_SO();
            ob[9] = ListarPro.get(i).getINVENTARIO_EN_STOCK();
            modelo.addRow(ob);
        }
        TableProducto.setModel(modelo);
    }
    
    public void LimpiarTable(){
        for(int i=0; i<modelo.getRowCount();i++){
            modelo.removeRow(i);
            i = i-1;
        }
    }
    
    public void LimpiarProducto(){
        txtIdPro.setText("");
        txtNomPro.setText("");
        txtCatPro.setText("");
        txtPrePro.setText("");
        txtModPro.setText("");
        txtMarPro.setText("");
        txtDesPro.setText("");
        txtCarPro.setText("");
        txtSoPro.setText("");
        txtInPro.setText("");
        
    }
    public void ListaProveedores() {
        List<Proveedor> listaProveedores = ProveedorDao.ListarProveedores();
        modelo = (DefaultTableModel) TableProveedor.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < listaProveedores.size(); i++) {
            ob[0] = listaProveedores.get(i).getID_PROVEEDOR();
            ob[1] = listaProveedores.get(i).getNOMBRE_PROVEEDOR();
            ob[2] = listaProveedores.get(i).getTELEFONO_P();
            ob[3] = listaProveedores.get(i).getCORREO_P();
            ob[4] = listaProveedores.get(i).getCONTACTO();
            ob[5] = listaProveedores.get(i).getID_PRODUCTO();
            modelo.addRow(ob);
        }
        TableProveedor.setModel(modelo);
    }

    public void LimpiarTableProveedor() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void LimpiarProveedor() {
        txtIDProveedor.setText("");
        txtNombreProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtCorreoProveedor.setText("");
        txtContactoProveedor.setText("");
        txtIDProductoProveedor.setText("");
    }
    
    public void ListarSucursal(){
        sucursal = prodao.BucarDatos();
        txtIdSucursal.setText(""+sucursal.getID_DE_LA_SUCURSAL());
        txtNombreSucursal.setText(""+sucursal.getNOMBRE_SUCURSAL());
        txtTelefonoSucursal.setText(""+sucursal.getTELEFONO_S());
        txtCorreoSucursal.setText(""+sucursal.getCORREO_S());
        txtPaginaWEBSucursal.setText(""+sucursal.getPAGINA_WEB_ID_PW());
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnVerProducto = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnClientes1 = new javax.swing.JButton();
        btnEnvios = new javax.swing.JButton();
        btnDireccion = new javax.swing.JButton();
        btnDetalleVenta = new javax.swing.JButton();
        btnSucursal = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtIdPro = new javax.swing.JTextField();
        txtNomPro = new javax.swing.JTextField();
        txtCatPro = new javax.swing.JTextField();
        txtPrePro = new javax.swing.JTextField();
        txtModPro = new javax.swing.JTextField();
        txtMarPro = new javax.swing.JTextField();
        txtDesPro = new javax.swing.JTextField();
        txtCarPro = new javax.swing.JTextField();
        txtSoPro = new javax.swing.JTextField();
        txtInPro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        btnGuardarPro = new javax.swing.JButton();
        btnEditarPro = new javax.swing.JButton();
        btnEliminarPro = new javax.swing.JButton();
        btnNuevoPro = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        txtIdProducto = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        txtStockVenta = new javax.swing.JTextField();
        txtClienteVenta = new javax.swing.JTextField();
        txtProductoVenta = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableVenta = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        txtFechaVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        btnGuardarVenta = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtVendedorVenta = new javax.swing.JTextField();
        LabelTotal = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cbxMetodoPago = new javax.swing.JComboBox<>();
        btnEliminarVenta = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtIdVenta = new javax.swing.JTextField();
        txtIdDetalleV = new javax.swing.JTextField();
        txtTelefonoVenta = new javax.swing.JTextField();
        txtCorreoVenta = new javax.swing.JTextField();
        btnPDFVENTA = new javax.swing.JButton();
        btnActualizarVenta = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableProveedor = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        txtIDProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        txtIDProductoProveedor = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        txtCorreoProveedor = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        txtContactoProveedor = new javax.swing.JTextField();
        btnGuardarProveedor = new javax.swing.JButton();
        btnActualizarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        btnBuscarProveedor = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        txtIdDetalleC = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableDetallesCompra = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtCantidadCompra = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtIdCompraD = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtIdProductoD = new javax.swing.JTextField();
        btnBuscarDetalleC = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtIdEnvio = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableEnvios = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtIdVentaE = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtIdDirE = new javax.swing.JTextField();
        btnBuscarEnvio = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        txtIdEmpleadoE = new javax.swing.JTextField();
        btnGuardarEnvio = new javax.swing.JButton();
        btnActualizarEnvio = new javax.swing.JButton();
        btnEliminarEnvio = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbDireccion = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtD_ID = new javax.swing.JTextField();
        txtD_ALCALDIA = new javax.swing.JTextField();
        txtD_CP = new javax.swing.JTextField();
        txtD_CALLE = new javax.swing.JTextField();
        txtD_NUM_EXT = new javax.swing.JTextField();
        txtD_COLONIA = new javax.swing.JTextField();
        txtD_ID_ESTADO = new javax.swing.JTextField();
        btnD_MOSTRAR = new javax.swing.JButton();
        btnD_AGREGAR = new javax.swing.JButton();
        btnD_MODIFICAR = new javax.swing.JButton();
        btnD_ELIMINAR = new javax.swing.JButton();
        btnD_LIMPIAR = new javax.swing.JButton();
        btnD_ACTUALIZAR1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbCLIENTE = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtCID = new javax.swing.JTextField();
        txtCNOMBRE = new javax.swing.JTextField();
        txtCTELEFONO = new javax.swing.JTextField();
        txtCCORREO = new javax.swing.JTextField();
        btnD_MOSTRAR1 = new javax.swing.JButton();
        btnD_AGREGAR1 = new javax.swing.JButton();
        btnD_MODIFICAR1 = new javax.swing.JButton();
        btnD_ELIMINAR1 = new javax.swing.JButton();
        btnD_LIMPIAR1 = new javax.swing.JButton();
        btnD_ACTUALIZAR2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TableDetalleV = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtIdDetalleVenta = new javax.swing.JTextField();
        txtCntidadDetalle = new javax.swing.JTextField();
        txtCodigoDetalle = new javax.swing.JTextField();
        txtVentaDetalle = new javax.swing.JTextField();
        btnD_MOSTRAR2 = new javax.swing.JButton();
        btnD_AGREGAR2 = new javax.swing.JButton();
        btnD_MODIFICAR2 = new javax.swing.JButton();
        btnD_ELIMINAR2 = new javax.swing.JButton();
        btnD_LIMPIAR2 = new javax.swing.JButton();
        btnD_ACTUALIZAR3 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        txtIdSucursal = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        TableSucursal = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtNombreSucursal = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtTelefonoSucursal = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtCorreoSucursal = new javax.swing.JTextField();
        btnBuscarEnvio1 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        txtPaginaWEBSucursal = new javax.swing.JTextField();
        btnGuardarSucursal = new javax.swing.JButton();
        btnActualizarSucursal = new javax.swing.JButton();
        btnEliminarSucursal = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 51));

        btnVerProducto.setBackground(new java.awt.Color(0, 0, 0));
        btnVerProducto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnVerProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/producto.png"))); // NOI18N
        btnVerProducto.setText("Productos");
        btnVerProducto.setMaximumSize(new java.awt.Dimension(123, 36));
        btnVerProducto.setMinimumSize(new java.awt.Dimension(123, 36));
        btnVerProducto.setPreferredSize(new java.awt.Dimension(123, 36));
        btnVerProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerProductoActionPerformed(evt);
            }
        });

        btnVentas.setBackground(new java.awt.Color(0, 0, 0));
        btnVentas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/compras.png"))); // NOI18N
        btnVentas.setText("Ventas");
        btnVentas.setMaximumSize(new java.awt.Dimension(123, 36));
        btnVentas.setMinimumSize(new java.awt.Dimension(123, 36));
        btnVentas.setPreferredSize(new java.awt.Dimension(123, 36));
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        btnProveedor.setBackground(new java.awt.Color(0, 0, 0));
        btnProveedor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/proveedor.png"))); // NOI18N
        btnProveedor.setText("Proveedor");
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        btnClientes.setBackground(new java.awt.Color(0, 0, 0));
        btnClientes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Clientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setMaximumSize(new java.awt.Dimension(123, 36));
        btnClientes.setMinimumSize(new java.awt.Dimension(123, 36));
        btnClientes.setPreferredSize(new java.awt.Dimension(123, 36));
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnClientes1.setBackground(new java.awt.Color(0, 0, 0));
        btnClientes1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClientes1.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Clientes.png"))); // NOI18N
        btnClientes1.setText("DetalleCompra");
        btnClientes1.setMaximumSize(new java.awt.Dimension(123, 36));
        btnClientes1.setMinimumSize(new java.awt.Dimension(123, 36));
        btnClientes1.setPreferredSize(new java.awt.Dimension(123, 36));
        btnClientes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientes1ActionPerformed(evt);
            }
        });

        btnEnvios.setBackground(new java.awt.Color(0, 0, 0));
        btnEnvios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnvios.setForeground(new java.awt.Color(255, 255, 255));
        btnEnvios.setText("Envio");
        btnEnvios.setMaximumSize(new java.awt.Dimension(123, 36));
        btnEnvios.setMinimumSize(new java.awt.Dimension(123, 36));
        btnEnvios.setPreferredSize(new java.awt.Dimension(123, 36));
        btnEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviosActionPerformed(evt);
            }
        });

        btnDireccion.setText("Dirección");
        btnDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDireccionActionPerformed(evt);
            }
        });

        btnDetalleVenta.setText("Detalle Venta");
        btnDetalleVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleVentaActionPerformed(evt);
            }
        });

        btnSucursal.setText("Sucursal");
        btnSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSucursalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVentas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVerProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEnvios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDetalleVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSucursal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnVerProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProveedor)
                .addGap(18, 18, 18)
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEnvios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDireccion)
                .addGap(27, 27, 27)
                .addComponent(btnDetalleVenta)
                .addGap(18, 18, 18)
                .addComponent(btnSucursal)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 140, 530));

        jLabel2.setText("ID");

        jLabel3.setText("Nombre");

        jLabel4.setText("Categoria");

        jLabel5.setText("Precio");

        jLabel6.setText("Modelo");

        jLabel7.setText("Marca");

        jLabel8.setText("Descripcion");

        jLabel9.setText("Caracteristicas");

        jLabel10.setText("S.O");

        jLabel11.setText("Inventario");

        txtIdPro.setColumns(3);
        txtIdPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProActionPerformed(evt);
            }
        });

        txtNomPro.setColumns(14);

        txtCatPro.setColumns(6);

        txtPrePro.setColumns(4);
        txtPrePro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPreProActionPerformed(evt);
            }
        });

        txtModPro.setColumns(8);

        txtMarPro.setColumns(6);

        txtDesPro.setColumns(14);

        txtCarPro.setColumns(14);
        txtCarPro.setMaximumSize(new java.awt.Dimension(15, 22));

        txtSoPro.setColumns(6);

        txtInPro.setColumns(2);

        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Categoria", "Precio", "Modelo", "Marca", "Descripcion", "Caracteristicas", "S.O", "Inventario"
            }
        ));
        TableProducto.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TableProductoAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        TableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableProducto);
        if (TableProducto.getColumnModel().getColumnCount() > 0) {
            TableProducto.getColumnModel().getColumn(0).setPreferredWidth(25);
            TableProducto.getColumnModel().getColumn(1).setPreferredWidth(25);
            TableProducto.getColumnModel().getColumn(2).setPreferredWidth(25);
            TableProducto.getColumnModel().getColumn(3).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(4).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(5).setPreferredWidth(25);
            TableProducto.getColumnModel().getColumn(6).setPreferredWidth(25);
            TableProducto.getColumnModel().getColumn(7).setPreferredWidth(25);
            TableProducto.getColumnModel().getColumn(8).setPreferredWidth(10);
            TableProducto.getColumnModel().getColumn(9).setPreferredWidth(15);
        }

        btnGuardarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GuardarTodo.png"))); // NOI18N
        btnGuardarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProActionPerformed(evt);
            }
        });

        btnEditarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actualizar.jpeg"))); // NOI18N
        btnEditarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProActionPerformed(evt);
            }
        });

        btnEliminarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProActionPerformed(evt);
            }
        });

        btnNuevoPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtCatPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtPrePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtModPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtMarPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDesPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCarPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(117, 117, 117)))
                .addGap(22, 22, 22))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardarPro)
                .addGap(18, 18, 18)
                .addComponent(btnEditarPro)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarPro)
                .addGap(18, 18, 18)
                .addComponent(btnNuevoPro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCatPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCarPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarPro)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditarPro)
                        .addComponent(btnEliminarPro)
                        .addComponent(btnNuevoPro)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos", jPanel2);

        jLabel1.setText("Id_Cliente:");

        jLabel12.setText("Id_Producto:");

        jLabel13.setText("Precio:");

        jLabel14.setText("Stock Disponible:");

        jLabel15.setText("Cliente:");

        jLabel16.setText("Producto:");

        jLabel17.setText("Cantidad:");

        txtIdCliente.setColumns(5);
        txtIdCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClienteActionPerformed(evt);
            }
        });
        txtIdCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdClienteKeyPressed(evt);
            }
        });

        txtIdProducto.setColumns(5);
        txtIdProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProductoActionPerformed(evt);
            }
        });
        txtIdProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdProductoKeyPressed(evt);
            }
        });

        txtPrecioVenta.setColumns(5);

        txtStockVenta.setColumns(5);

        txtClienteVenta.setEditable(false);
        txtClienteVenta.setColumns(10);
        txtClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteVentaActionPerformed(evt);
            }
        });

        txtProductoVenta.setColumns(10);

        TableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane2.setViewportView(TableVenta);

        jLabel18.setText("Fecha:");

        txtFechaVenta.setEditable(false);
        txtFechaVenta.setColumns(10);
        txtFechaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaVentaActionPerformed(evt);
            }
        });

        txtCantidadVenta.setColumns(10);
        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
        });

        btnGuardarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GuardarTodo.png"))); // NOI18N
        btnGuardarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVentaActionPerformed(evt);
            }
        });

        jLabel19.setText("Vendedor:");

        txtVendedorVenta.setEditable(false);
        txtVendedorVenta.setColumns(10);
        txtVendedorVenta.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                txtVendedorVentaComponentShown(evt);
            }
        });
        txtVendedorVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVendedorVentaActionPerformed(evt);
            }
        });

        LabelTotal.setText("-------------------------");

        jLabel20.setText("Total a Pagar:");

        jLabel21.setText("Tipo de Pago:");

        cbxMetodoPago.setEditable(true);

        btnEliminarVenta.setText("Eliminar");
        btnEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentaActionPerformed(evt);
            }
        });

        jLabel22.setText("Serie:");

        txtIdVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdVentaActionPerformed(evt);
            }
        });
        txtIdVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdVentaKeyPressed(evt);
            }
        });

        txtIdDetalleV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdDetalleVActionPerformed(evt);
            }
        });

        txtTelefonoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoVentaActionPerformed(evt);
            }
        });

        txtCorreoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoVentaActionPerformed(evt);
            }
        });

        btnPDFVENTA.setText("PDF");
        btnPDFVENTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFVENTAActionPerformed(evt);
            }
        });

        btnActualizarVenta.setText("Actualizar");
        btnActualizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCorreoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txtTelefonoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(139, 139, 139)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaVenta)
                            .addComponent(txtClienteVenta)
                            .addComponent(txtProductoVenta)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel22)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtVendedorVenta)
                            .addComponent(LabelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxMetodoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdVenta)
                            .addComponent(txtIdDetalleV, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarVenta)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnGuardarVenta)
                                .addGap(14, 14, 14)
                                .addComponent(btnPDFVENTA))
                            .addComponent(btnActualizarVenta))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel15)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(cbxMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPDFVENTA)
                            .addComponent(btnGuardarVenta))
                        .addGap(8, 8, 8)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel16)
                            .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(txtVendedorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnEliminarVenta)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelTotal)
                            .addComponent(jLabel20)
                            .addComponent(btnActualizarVenta))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(txtFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTelefonoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCorreoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdDetalleV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ventas", jPanel3);

        TableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Telefono", "Correo", "Contacto", "ID_PRODUCTO"
            }
        ));
        jScrollPane4.setViewportView(TableProveedor);

        jTextField1.setText("ID");

        txtIDProveedor.setColumns(3);

        txtNombreProveedor.setColumns(3);

        jTextField2.setText("Nombre");

        txtTelefonoProveedor.setColumns(3);

        jTextField3.setText("Telefono");

        jTextField4.setText("Correo");

        txtIDProductoProveedor.setColumns(3);

        jTextField5.setText("Contacto");

        txtCorreoProveedor.setColumns(3);

        jTextField6.setText("ID_PRODUCTO");

        txtContactoProveedor.setColumns(3);

        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GuardarTodo.png"))); // NOI18N
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        btnActualizarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actualizar.jpeg"))); // NOI18N
        btnActualizarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        btnBuscarProveedor.setText("Buscar");
        btnBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1318, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(127, 127, 127)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(txtContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(txtIDProductoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btnBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDProductoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1330, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Proveedor", jPanel4);

        txtIdDetalleC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdDetalleCActionPerformed(evt);
            }
        });

        TableDetallesCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID_Detalle_C", "Cantidad de Compra", "ID_Compra", "ID_Producto"
            }
        ));
        jScrollPane6.setViewportView(TableDetallesCompra);

        jLabel23.setText("ID DetalleC");

        jLabel24.setText("Cantidad de Compra");

        txtCantidadCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadCompraActionPerformed(evt);
            }
        });

        jLabel25.setText("ID Compra");

        txtIdCompraD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdCompraDActionPerformed(evt);
            }
        });

        jLabel26.setText("ID Producto");

        txtIdProductoD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProductoDActionPerformed(evt);
            }
        });

        btnBuscarDetalleC.setText("Busqueda");
        btnBuscarDetalleC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDetalleCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtIdDetalleC, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(txtCantidadCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel26))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtIdCompraD, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdProductoD, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addComponent(btnBuscarDetalleC, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(656, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdDetalleC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdCompraD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProductoD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscarDetalleC, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DetalleCompra", jPanel8);

        txtIdEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEnvioActionPerformed(evt);
            }
        });

        TableEnvios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Envio", "Fecha", "ID Venta", "ID Dir", "ID Empleado"
            }
        ));
        TableEnvios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableEnviosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TableEnvios);

        jLabel27.setText("ID Envio");

        jLabel28.setText("Fecha");

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        jLabel29.setText("ID_Venta");

        txtIdVentaE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdVentaEActionPerformed(evt);
            }
        });

        jLabel30.setText("ID_DIR");

        txtIdDirE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdDirEActionPerformed(evt);
            }
        });

        btnBuscarEnvio.setText("Buscar");
        btnBuscarEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEnvioActionPerformed(evt);
            }
        });

        jLabel31.setText("ID_Empleado");

        txtIdEmpleadoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEmpleadoEActionPerformed(evt);
            }
        });

        btnGuardarEnvio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GuardarTodo.png"))); // NOI18N
        btnGuardarEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEnvioActionPerformed(evt);
            }
        });

        btnActualizarEnvio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actualizar.jpeg"))); // NOI18N
        btnActualizarEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEnvioActionPerformed(evt);
            }
        });

        btnEliminarEnvio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminarEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEnvioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtIdEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdVentaE, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdDirE, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdEmpleadoE, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel28)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel29)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel30)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel31)))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarEnvio))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(571, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel31))
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdVentaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdDirE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdEmpleadoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscarEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnGuardarEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizarEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Envios", jPanel7);

        tbDireccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Alcaldia/Municipio", "C.P", "Calle", "Numero Exterior", "Colonia", "Id Estado"
            }
        ));
        jScrollPane7.setViewportView(tbDireccion);
        if (tbDireccion.getColumnModel().getColumnCount() > 0) {
            tbDireccion.getColumnModel().getColumn(2).setHeaderValue("C.P");
            tbDireccion.getColumnModel().getColumn(5).setHeaderValue("Colonia");
            tbDireccion.getColumnModel().getColumn(6).setHeaderValue("Id Estado");
        }

        jLabel32.setText("ID");

        jLabel33.setText("Alcaldia/Municipio");

        jLabel34.setText("C.P");

        jLabel35.setText("Calle");

        jLabel36.setText("Numero Exterior");

        jLabel37.setText("Colonia");

        jLabel38.setText("Id Estado");

        txtD_ID.setColumns(10);

        txtD_ALCALDIA.setColumns(10);

        txtD_CP.setColumns(10);
        txtD_CP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtD_CPActionPerformed(evt);
            }
        });

        txtD_CALLE.setColumns(10);

        txtD_NUM_EXT.setColumns(10);

        txtD_COLONIA.setColumns(10);

        txtD_ID_ESTADO.setColumns(10);
        txtD_ID_ESTADO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtD_ID_ESTADOActionPerformed(evt);
            }
        });

        btnD_MOSTRAR.setText("Mostrar");
        btnD_MOSTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_MOSTRARActionPerformed(evt);
            }
        });

        btnD_AGREGAR.setText("Agregar");
        btnD_AGREGAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_AGREGARActionPerformed(evt);
            }
        });

        btnD_MODIFICAR.setText("Modificar");
        btnD_MODIFICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_MODIFICARActionPerformed(evt);
            }
        });

        btnD_ELIMINAR.setText("Eliminar");
        btnD_ELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_ELIMINARActionPerformed(evt);
            }
        });

        btnD_LIMPIAR.setText("Limpiar");
        btnD_LIMPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_LIMPIARActionPerformed(evt);
            }
        });

        btnD_ACTUALIZAR1.setText("Actualizar");

        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane7))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(txtD_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(jLabel32)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtD_ALCALDIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(txtD_CP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtD_CALLE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtD_NUM_EXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtD_COLONIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtD_ID_ESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(72, 72, 72)
                                    .addComponent(jLabel34)
                                    .addGap(113, 113, 113)
                                    .addComponent(jLabel35)
                                    .addGap(89, 89, 89)
                                    .addComponent(jLabel36)
                                    .addGap(73, 73, 73)
                                    .addComponent(jLabel37)
                                    .addGap(99, 99, 99)
                                    .addComponent(jLabel38)))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnD_MOSTRAR)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_AGREGAR)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_MODIFICAR)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_ELIMINAR)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_LIMPIAR)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_ACTUALIZAR1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtD_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtD_ALCALDIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtD_CP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtD_CALLE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtD_NUM_EXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtD_COLONIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtD_ID_ESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnD_MOSTRAR)
                    .addComponent(btnD_AGREGAR)
                    .addComponent(btnD_MODIFICAR)
                    .addComponent(btnD_ELIMINAR)
                    .addComponent(btnD_LIMPIAR)
                    .addComponent(btnD_ACTUALIZAR1)
                    .addComponent(jButton7))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dirección", jPanel9);

        tbCLIENTE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Teléfono", "Correo"
            }
        ));
        jScrollPane8.setViewportView(tbCLIENTE);

        jLabel39.setText("ID");

        jLabel40.setText("Nombre");

        jLabel41.setText("Teléfono");

        jLabel42.setText("Correo");

        txtCID.setColumns(10);

        txtCNOMBRE.setColumns(10);

        txtCTELEFONO.setColumns(10);
        txtCTELEFONO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCTELEFONOActionPerformed(evt);
            }
        });

        txtCCORREO.setColumns(10);

        btnD_MOSTRAR1.setText("Mostrar");
        btnD_MOSTRAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_MOSTRAR1ActionPerformed(evt);
            }
        });

        btnD_AGREGAR1.setText("Agregar");
        btnD_AGREGAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_AGREGAR1ActionPerformed(evt);
            }
        });

        btnD_MODIFICAR1.setText("Modificar");
        btnD_MODIFICAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_MODIFICAR1ActionPerformed(evt);
            }
        });

        btnD_ELIMINAR1.setText("Eliminar");
        btnD_ELIMINAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_ELIMINAR1ActionPerformed(evt);
            }
        });

        btnD_LIMPIAR1.setText("Limpiar");
        btnD_LIMPIAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_LIMPIAR1ActionPerformed(evt);
            }
        });

        btnD_ACTUALIZAR2.setText("Actualizar");
        btnD_ACTUALIZAR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_ACTUALIZAR2ActionPerformed(evt);
            }
        });

        jButton8.setText("Buscar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane8))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(txtCID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(jLabel39)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(txtCTELEFONO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCCORREO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGap(72, 72, 72)
                                    .addComponent(jLabel41)
                                    .addGap(81, 81, 81)
                                    .addComponent(jLabel42)))
                            .addGap(426, 426, 426)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnD_MOSTRAR1)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_AGREGAR1)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_MODIFICAR1)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_ELIMINAR1)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_LIMPIAR1)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_ACTUALIZAR2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCTELEFONO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCCORREO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnD_MOSTRAR1)
                    .addComponent(btnD_AGREGAR1)
                    .addComponent(btnD_MODIFICAR1)
                    .addComponent(btnD_ELIMINAR1)
                    .addComponent(btnD_LIMPIAR1)
                    .addComponent(btnD_ACTUALIZAR2)
                    .addComponent(jButton8))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clientes", jPanel10);

        TableDetalleV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Detalle Venta", "Cantidad", "Código", "Venta"
            }
        ));
        jScrollPane9.setViewportView(TableDetalleV);

        jLabel43.setText("Id Detalle");

        jLabel44.setText("Cantidad");

        jLabel45.setText("Codigo");

        jLabel46.setText("Venta");

        txtIdDetalleVenta.setColumns(10);

        txtCntidadDetalle.setColumns(10);

        txtCodigoDetalle.setColumns(10);
        txtCodigoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoDetalleActionPerformed(evt);
            }
        });

        txtVentaDetalle.setColumns(10);

        btnD_MOSTRAR2.setText("Mostrar");
        btnD_MOSTRAR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_MOSTRAR2ActionPerformed(evt);
            }
        });

        btnD_AGREGAR2.setText("Agregar");
        btnD_AGREGAR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_AGREGAR2ActionPerformed(evt);
            }
        });

        btnD_MODIFICAR2.setText("Modificar");
        btnD_MODIFICAR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_MODIFICAR2ActionPerformed(evt);
            }
        });

        btnD_ELIMINAR2.setText("Eliminar");
        btnD_ELIMINAR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_ELIMINAR2ActionPerformed(evt);
            }
        });

        btnD_LIMPIAR2.setText("Limpiar");
        btnD_LIMPIAR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_LIMPIAR2ActionPerformed(evt);
            }
        });

        btnD_ACTUALIZAR3.setText("Actualizar");
        btnD_ACTUALIZAR3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD_ACTUALIZAR3ActionPerformed(evt);
            }
        });

        jButton9.setText("Buscar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane9))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(txtIdDetalleVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(jLabel43)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCntidadDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(txtCodigoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtVentaDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGap(72, 72, 72)
                                    .addComponent(jLabel45)
                                    .addGap(81, 81, 81)
                                    .addComponent(jLabel46)))
                            .addGap(426, 426, 426)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnD_MOSTRAR2)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_AGREGAR2)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_MODIFICAR2)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_ELIMINAR2)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_LIMPIAR2)
                        .addGap(18, 18, 18)
                        .addComponent(btnD_ACTUALIZAR3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdDetalleVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCntidadDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVentaDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnD_MOSTRAR2)
                    .addComponent(btnD_AGREGAR2)
                    .addComponent(btnD_MODIFICAR2)
                    .addComponent(btnD_ELIMINAR2)
                    .addComponent(btnD_LIMPIAR2)
                    .addComponent(btnD_ACTUALIZAR3)
                    .addComponent(jButton9))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Detalle Venta", jPanel11);

        txtIdSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSucursalActionPerformed(evt);
            }
        });

        TableSucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Sucursal", "Nombre ", "Telefono", "Correo", "Pagina WEB"
            }
        ));
        TableSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableSucursalMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(TableSucursal);

        jLabel47.setText("ID de la Sucursal");

        jLabel48.setText("Nombre");

        txtNombreSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreSucursalActionPerformed(evt);
            }
        });

        jLabel49.setText("Telefono");

        txtTelefonoSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoSucursalActionPerformed(evt);
            }
        });

        jLabel50.setText("Correo");

        txtCorreoSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoSucursalActionPerformed(evt);
            }
        });

        btnBuscarEnvio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEnvio1ActionPerformed(evt);
            }
        });

        jLabel51.setText("Pagina WEB");

        txtPaginaWEBSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaginaWEBSucursalActionPerformed(evt);
            }
        });

        btnGuardarSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GuardarTodo.png"))); // NOI18N
        btnGuardarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSucursalActionPerformed(evt);
            }
        });

        btnActualizarSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actualizar.jpeg"))); // NOI18N
        btnActualizarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarSucursalActionPerformed(evt);
            }
        });

        btnEliminarSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSucursalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(txtIdSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(txtNombreSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefonoSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCorreoSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel48)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel49)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel50)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(txtPaginaWEBSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarEnvio1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(571, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel51))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreoSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPaginaWEBSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscarEnvio1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnGuardarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sucursal", jPanel12);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 1250, 530));

        jPanel5.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1400, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerProductoActionPerformed
        LimpiarTable();
        ListaPro();
        jTabbedPane1.setSelectedIndex(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerProductoActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        jTabbedPane1.setSelectedIndex(1);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        LimpiarTable();
        ListaProveedores();
        jTabbedPane1.setSelectedIndex(2);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        LimpiarTable();
        jTabbedPane1.setSelectedIndex(6);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnClientes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientes1ActionPerformed
        LimpiarTable();
        ListaDetallesCompra();
        jTabbedPane1.setSelectedIndex(3); 
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientes1ActionPerformed

    private void btnEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviosActionPerformed
        LimpiarTable();
        ListaEnvios();
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_btnEnviosActionPerformed

    private void btnEliminarEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEnvioActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdEnvio.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar el envío?");
            if (pregunta == 0) {
                String ID_ENVIO = txtIdEnvio.getText();
                EnvioDAO.EliminarEnvio(ID_ENVIO);
                LimpiarTable();  // Asegúrate de tener un método similar para limpiar la tabla de envíos
                LimpiarEnvio();  // Asegúrate de tener un método similar para limpiar los campos de la interfaz de envíos
                ListaEnvios();   // Asegúrate de tener un método similar para actualizar la lista de envíos
            }
        }
    }//GEN-LAST:event_btnEliminarEnvioActionPerformed

    private void btnActualizarEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEnvioActionPerformed
        if ("".equals(txtIdEnvio.getText())) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        } else {
            if (!"".equals(txtIdEnvio.getText()) || !"".equals(txtFecha.getText()) || !"".equals(txtIdVentaE.getText()) || !"".equals(txtIdDirE.getText()) || !"".equals(txtIdEmpleadoE.getText())) {
                Envio envio = new Envio();
                envio.setID_ENVIO(txtIdEnvio.getText());
                try {
                    // Formato de la cadena de fecha que se espera, ajusta el patrón según tu necesidad
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    // Parsea la cadena de texto a un objeto Date
                    Date fecha = dateFormat.parse(txtFecha.getText());

                    // Establece la fecha en el objeto envio
                    envio.setFECHA(fecha);
                } catch (ParseException e) {
                    // Maneja la excepción en caso de que la cadena de texto no sea válida como fecha
                    e.printStackTrace(); // o haz algo más apropiado según tus requerimientos
                }
                envio.setID_VENTA(Integer.parseInt(txtIdVentaE.getText()));
                envio.setID_DIR(txtIdDirE.getText());
                envio.setID_EMPLEADO(txtIdEmpleadoE.getText());

                // Modificar envío
                EnvioDAO.ModificarEnvio(envio);

                JOptionPane.showMessageDialog(null, "Envío modificado exitosamente");
                LimpiarTable();  // Puedes adaptar esto según tu implementación
                ListaEnvios();   // Puedes adaptar esto según tu implementación
                LimpiarEnvio();  // Puedes adaptar esto según tu implementación
            }
        }
    }//GEN-LAST:event_btnActualizarEnvioActionPerformed

    private void btnGuardarEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEnvioActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdEnvio.getText()) || !"".equals(txtFecha.getText()) || !"".equals(txtIdVentaE.getText()) || !"".equals(txtIdDirE.getText()) || !"".equals(txtIdEmpleadoE.getText())) {
            Envio envio = new Envio();
            envio.setID_ENVIO(txtIdEnvio.getText());
            try {
                // Formato de la cadena de fecha que se espera, ajusta el patrón según tu necesidad
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

                // Parsea la cadena de texto a un objeto Date
                Date fecha = dateFormat.parse(txtFecha.getText());

                // Establece la fecha en el objeto envio
                envio.setFECHA(fecha);
            } catch (ParseException e) {
                // Maneja la excepción en caso de que la cadena de texto no sea válida como fecha
                e.printStackTrace(); // o haz algo más apropiado según tus requerimientos
            }
            envio.setID_VENTA(Integer.parseInt(txtIdVentaE.getText()));
            envio.setID_DIR(txtIdDirE.getText());
            envio.setID_EMPLEADO(txtIdEmpleadoE.getText());

            EnvioDAO.RegistrarEnvio(envio);
            JOptionPane.showMessageDialog(null, "Envío Registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos están vacíos");
        }
    }//GEN-LAST:event_btnGuardarEnvioActionPerformed

    private void txtIdEmpleadoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEmpleadoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEmpleadoEActionPerformed

    private void btnBuscarEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEnvioActionPerformed
        String idEnvio = txtIdEnvio.getText();
        String fecha = txtFecha.getText();
        String idVenta = txtIdVentaE.getText();
        String idDir = txtIdDirE.getText();
        String idEmpleado = txtIdEmpleadoE.getText();

        // Realizar la búsqueda utilizando el método BuscarEnvio del EnvioDAO
        List<Envio> resultados = EnvioDAO.BuscarEnvio(idEnvio, fecha, idVenta, idDir, idEmpleado);

        // Limpiar la tabla antes de agregar los nuevos resultados
        LimpiarTableEnvios();

        // Llenar la tabla con los resultados de la búsqueda
        for (Envio envio : resultados) {
            Object[] fila = {envio.getID_ENVIO(), envio.getFECHA(), envio.getID_VENTA(), envio.getID_DIR(), envio.getID_EMPLEADO()};
            modelo.addRow(fila);
        }

        // Notificar que la tabla se actualizó
        JOptionPane.showMessageDialog(null, "Búsqueda completada");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarEnvioActionPerformed

    private void txtIdDirEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdDirEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdDirEActionPerformed

    private void txtIdVentaEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdVentaEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdVentaEActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void TableEnviosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEnviosMouseClicked
        // TODO add your handling code here:
        int fila = TableEnvios.rowAtPoint(evt.getPoint());
        txtIdEnvio.setText(TableEnvios.getValueAt(fila, 0).toString());
        txtFecha.setText(TableEnvios.getValueAt(fila, 1).toString());
        txtIdVentaE.setText(TableEnvios.getValueAt(fila, 2).toString());
        txtIdDirE.setText(TableEnvios.getValueAt(fila, 3).toString());
        txtIdEmpleadoE.setText(TableEnvios.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableEnviosMouseClicked

    private void txtIdEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEnvioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEnvioActionPerformed

    private void btnBuscarDetalleCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDetalleCActionPerformed
        // Obtener los valores ingresados para la búsqueda desde los textfields
        String idDetalleCompra = txtIdDetalleC.getText();
        String cantidadCompra = txtCantidadCompra.getText();
        String idCompraD = txtIdCompraD.getText();
        String idProductoD = txtIdProductoD.getText();

        // Realizar la búsqueda utilizando el método BuscarDetalleCompra del DetalleCompraDAO
        List<DetalleCompra> resultados = detalleCompraDAO.BuscarDetalleCompra(idDetalleCompra, cantidadCompra, idCompraD, idProductoD);

        // Limpiar la tabla antes de agregar los nuevos resultados
        LimpiarTable();

        // Llenar la tabla con los resultados de la búsqueda
        for (DetalleCompra detalleCompra : resultados) {
            Object[] fila = {detalleCompra.getIdDetalleCompra(), detalleCompra.getCantidadCompra(), detalleCompra.getIdCompra(), detalleCompra.getIdProducto()};
            modelo.addRow(fila);
        }

        // Notificar que la tabla se actualizó
        JOptionPane.showMessageDialog(null, "Búsqueda completada");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarDetalleCActionPerformed

    private void txtIdProductoDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProductoDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoDActionPerformed

    private void txtIdCompraDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdCompraDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdCompraDActionPerformed

    private void txtCantidadCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadCompraActionPerformed

    private void txtIdDetalleCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdDetalleCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdDetalleCActionPerformed

    private void btnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarProveedorActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        if(!"".equals(txtIDProveedor.getText())){
            int pregunta = JOptionPane.showConfirmDialog(null, "Deseas eliminar");
            if(pregunta ==0){
                String ID_PROVEEDOR =(txtIDProveedor.getText());
                ProveedorDao.EliminarProveedor(ID_PROVEEDOR);
                LimpiarTable();
                LimpiarProducto();
                ListaPro();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnActualizarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProveedorActionPerformed
        if("".equals(txtIDProveedor.getText())){
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }else{
            if(!"".equals(txtIDProveedor.getText())||!"".equals(txtNombreProveedor.getText())||!"".equals(txtTelefonoProveedor.getText())||!"".equals(txtCorreoProveedor.getText())||!"".equals(txtContactoProveedor.getText())||!"".equals(txtIDProductoProveedor.getText())){
                Proveedor.setID_PROVEEDOR(txtIDProveedor.getText());
                Proveedor.setNOMBRE_PROVEEDOR(txtNombreProveedor.getText());
                Proveedor.setTELEFONO_P(txtTelefonoProveedor.getText());

                Proveedor.setCORREO_P(txtCorreoProveedor.getText());
                Proveedor.setCONTACTO(txtContactoProveedor.getText());
                Proveedor.setID_PRODUCTO(txtIDProductoProveedor.getText());

                ProveedorDao.ModificarProveedor(Proveedor);
                JOptionPane.showMessageDialog(null, "Proovedor modificado exitosamente");
                LimpiarTable();
                ListaPro();
                LimpiarProducto();

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarProveedorActionPerformed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        if(!"".equals(txtIDProveedor.getText()) ||!"".equals(txtNombreProveedor.getText()) ||!"".equals(txtTelefonoProveedor.getText())){
            Proveedor.setID_PROVEEDOR(txtIDProveedor.getText());
            Proveedor.setNOMBRE_PROVEEDOR(txtNombreProveedor.getText());
            Proveedor.setTELEFONO_P(txtTelefonoProveedor.getText());
            Proveedor.setCORREO_P(txtCorreoProveedor.getText());
            Proveedor.setCONTACTO(txtContactoProveedor.getText());
            Proveedor.setID_PRODUCTO(txtIDProductoProveedor.getText());

            ProveedorDao.RegistrarProveedor(Proveedor);
            JOptionPane.showMessageDialog(null, "Proveedor Registrado");
        }else{
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void txtIdDetalleVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdDetalleVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdDetalleVActionPerformed

    private void txtIdVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdVentaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdVentaKeyPressed

    private void txtIdVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdVentaActionPerformed

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed
        modelo = (DefaultTableModel) TableVenta.getModel();
        modelo.removeRow(TableVenta.getSelectedRow());
        updateTotal();
        txtIdProducto.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void txtVendedorVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVendedorVentaActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtVendedorVentaActionPerformed

    private void txtVendedorVentaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtVendedorVentaComponentShown

        // TODO add your handling code here:
    }//GEN-LAST:event_txtVendedorVentaComponentShown

    private void btnGuardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVentaActionPerformed
        RegistrarVenta();
        RegistrarDetalleVenta();
        ActualizarStock();
        Reportepdf();
        LimpiarTableVenta();
        LimpiarClientVenta();

        //RegistrarDetalleVenta();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarVentaActionPerformed

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidadVenta.getText())) {
                String id = txtIdProducto.getText();
                String NomProducto = txtProductoVenta.getText();
                int cantidad = Integer.parseInt(txtCantidadVenta.getText());
                double precio = Double.parseDouble(txtPrecioVenta.getText());
                double total = cantidad * precio;
                int stock = Integer.parseInt(txtStockVenta.getText());

                if (stock >= cantidad) {
                    item = item + 1;
                    tmp = (DefaultTableModel) TableVenta.getModel();

                    ArrayList<Object> lista = new ArrayList<>();
                    lista.add(item);
                    lista.add(id);
                    lista.add(NomProducto);
                    lista.add(cantidad);
                    lista.add(precio);
                    lista.add(total);

                    Object[] Ob = new Object[6];
                    Ob[0] = lista.get(1);
                    Ob[1] = lista.get(2);
                    Ob[2] = lista.get(3);
                    Ob[3] = lista.get(4);
                    Ob[4] = lista.get(5);

                    tmp.addRow(Ob);
                    TableVenta.setModel(tmp);

                    // Call the updateTotal function after adding a new row
                    updateTotal();
                    LimpiarVenta();
                    txtIdProducto.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Stock no disponible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese cantidad");
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void txtFechaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaVentaActionPerformed

    private void txtClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteVentaActionPerformed

    private void txtIdProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProductoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!"".equals(txtIdProducto.getText())){
                String idProducto = txtIdProducto.getText();
                pro = prodao.BucarProducto(idProducto);
                if(pro.getNOMBRE_PRODUCTO() != null){
                    txtProductoVenta.setText(""+pro.getNOMBRE_PRODUCTO());
                    txtPrecioVenta.setText(""+pro.getPRECIO());
                    txtStockVenta.setText(""+pro.getINVENTARIO_EN_STOCK());
                    txtCantidadVenta.requestFocus();
                }else{
                    LimpiarVenta();
                    txtIdProducto.requestFocus();
                    JOptionPane.showMessageDialog(null, "El producto no existe");

                }
            } else{
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                txtProductoVenta.requestFocus();

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoKeyPressed

    private void txtIdProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoActionPerformed

    private void txtIdClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdClienteKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!"".equals(txtIdCliente.getText())){
                String idclient = txtIdCliente.getText();
                cli = clidao.buscarClientePorID(idclient);
                if(cli.getNOMBRE_CLIENTE()!= null){
                    txtClienteVenta.setText(""+cli.getNOMBRE_CLIENTE());
                    txtTelefonoVenta.setText(""+cli.getTELEFONO_C());
                    txtCorreoVenta.setText(""+cli.getCORREO_C());
                    txtIdCliente.setText(""+cli.getID_CLIENTE());                    
                    txtIdProducto.requestFocus();
                }else{
                    LimpiarClienteVenta();
                    txtIdCliente.requestFocus();
                    JOptionPane.showMessageDialog(null, "El cliente no existe");

                }
            } else{
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del cliente");
                txtClienteVenta.requestFocus();

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClienteKeyPressed

    private void txtIdClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClienteActionPerformed

    private void btnEliminarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProActionPerformed
        if(!"".equals(txtIdPro.getText())){
            int pregunta = JOptionPane.showConfirmDialog(null, "Deseas eliminar");
            if(pregunta ==0){
                String ID_PRODUCTO =(txtIdPro.getText());
                prodao.EliminarProducto(ID_PRODUCTO);
                LimpiarTable();
                LimpiarProducto();
                ListaPro();
            }
        }
    }//GEN-LAST:event_btnEliminarProActionPerformed

    private void btnEditarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProActionPerformed
        if("".equals(txtIdPro.getText())){
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        }else{
            if(!"".equals(txtIdPro.getText())||!"".equals(txtNomPro.getText())||!"".equals(txtCatPro.getText())||!"".equals(txtPrePro.getText())||!"".equals(txtModPro.getText())||!"".equals(txtMarPro.getText())||!"".equals(txtDesPro.getText())||!"".equals(txtCarPro.getText())||!"".equals(txtSoPro.getText())||!"".equals(txtInPro.getText())){
                pro.setID_PRODUCTO(txtIdPro.getText());
                pro.setNOMBRE_PRODUCTO(txtNomPro.getText());
                pro.setCATEGORÍA(txtCatPro.getText());
                pro.setPRECIO(Integer.parseInt(txtPrePro.getText())); // Asumiendo que el precio es un número
                pro.setMODELO(txtModPro.getText());
                pro.setMARCA(txtMarPro.getText());
                pro.setDESCRIPCION(txtDesPro.getText());
                pro.setCARACTERISTICAS(txtCarPro.getText());
                pro.setNOMBRE_DEL_SO(txtSoPro.getText());
                pro.setINVENTARIO_EN_STOCK(Integer.parseInt(txtInPro.getText()));
                prodao.ModificarProducto(pro);
                JOptionPane.showMessageDialog(null, "Producto modificado exitosamente");
                LimpiarTable();
                ListaPro();
                LimpiarProducto();

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarProActionPerformed

    private void btnGuardarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProActionPerformed
        // TODO add your handling code here:
        if(!"".equals(txtIdPro.getText()) ||!"".equals(txtNomPro.getText()) ||!"".equals(txtCatPro.getText())||!"".equals(txtPrePro.getText())||!"".equals(txtModPro.getText())||!"".equals(txtMarPro.getText())||!"".equals(txtDesPro.getText())||!"".equals(txtCarPro.getText())||!"".equals(txtSoPro.getText())||!"".equals(txtInPro.getText())){
            pro.setID_PRODUCTO(txtIdPro.getText());
            pro.setNOMBRE_PRODUCTO(txtNomPro.getText());
            pro.setCATEGORÍA(txtCatPro.getText());
            pro.setPRECIO(Integer.parseInt(txtPrePro.getText()));
            pro.setMODELO(txtModPro.getText());
            pro.setMARCA(txtMarPro.getText());
            pro.setDESCRIPCION(txtDesPro.getText());
            pro.setCARACTERISTICAS(txtCarPro.getText());
            pro.setNOMBRE_DEL_SO(txtSoPro.getText());
            pro.setINVENTARIO_EN_STOCK(Integer.parseInt(txtInPro.getText()));
            prodao.RegistrarProductos(pro);
            JOptionPane.showMessageDialog(null, "Producto Registrado");
        }else{
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarProActionPerformed

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked
        int fila = TableProducto.rowAtPoint(evt.getPoint());
        txtIdPro.setText(TableProducto.getValueAt(fila, 0).toString());
        txtNomPro.setText(TableProducto.getValueAt(fila, 1).toString());
        txtCatPro.setText(TableProducto.getValueAt(fila, 2).toString());
        txtPrePro.setText(TableProducto.getValueAt(fila, 3).toString());
        txtModPro.setText(TableProducto.getValueAt(fila, 4).toString());
        txtMarPro.setText(TableProducto.getValueAt(fila, 5).toString());
        txtDesPro.setText(TableProducto.getValueAt(fila, 6).toString());
        txtCarPro.setText(TableProducto.getValueAt(fila, 7).toString());
        txtSoPro.setText(TableProducto.getValueAt(fila, 8).toString());
        txtInPro.setText(TableProducto.getValueAt(fila, 9).toString());
    }//GEN-LAST:event_TableProductoMouseClicked

    private void TableProductoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_TableProductoAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_TableProductoAncestorAdded

    private void txtPreProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPreProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPreProActionPerformed

    private void txtIdProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProActionPerformed

    private void txtD_ID_ESTADOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtD_ID_ESTADOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtD_ID_ESTADOActionPerformed

    private void btnDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDireccionActionPerformed
        LimpiarTable();
        jTabbedPane1.setSelectedIndex(5);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDireccionActionPerformed

    private void txtD_CPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtD_CPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtD_CPActionPerformed

    private void btnD_MOSTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_MOSTRARActionPerformed
    MostrarDireccion();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_MOSTRARActionPerformed

    private void btnD_AGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_AGREGARActionPerformed
if(!"".equals(txtD_ID.getText())|| !"".equals(txtD_ALCALDIA.getText()) ||!"".equals(txtD_CP.getText()) ||!"".equals(txtD_CALLE.getText()) ||!"".equals(txtD_NUM_EXT.getText()) ||!"".equals(txtD_COLONIA.getText()) ||!"".equals(txtD_ID_ESTADO.getText()))
    {
        Direccion direccion = new Direccion();
        
        direccion.setID_DIR(txtD_ID.getText());
        direccion.setALCALDIA_MUNICIPIO(txtD_ALCALDIA.getText());
        direccion.setCP(txtD_CP.getText());
        direccion.setCALLE(txtD_CALLE.getText());
        direccion.setNUM_EXT(txtD_NUM_EXT.getText());
        direccion.setCOLONIA(txtD_COLONIA.getText());
        direccion.setID_ES(txtD_ID_ESTADO.getText());
        
        DireccionDAO direccionDAO = new DireccionDAO();
        direccionDAO.AgregarDireccion(direccion);
        
        JOptionPane.showMessageDialog(null, "Direccion Registrado");
    }
    else
    {
        JOptionPane.showMessageDialog(null, "Campos Vacios");
    }
    
    LimpiarTable();
    MostrarDireccion();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_AGREGARActionPerformed

    private void btnD_MODIFICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_MODIFICARActionPerformed
         if("".equals(txtD_ID.getText()))
    {
        JOptionPane.showMessageDialog(null, "Seleccione una Fila");
    }
    else
    {
        if(!"".equals(txtD_ID.getText())|| "".equals(txtD_ALCALDIA.getText()) ||"".equals(txtD_CP.getText()) ||"".equals(txtD_CALLE.getText()) ||"".equals(txtD_NUM_EXT.getText()) ||"".equals(txtD_COLONIA.getText()) ||"".equals(txtD_ID_ESTADO.getText()))
        
        {
            dir.setID_DIR(txtD_ID.getText());
            dir.setALCALDIA_MUNICIPIO(txtD_ALCALDIA.getText());
            dir.setCP(txtD_CP.getText());
            dir.setCALLE(txtD_CALLE.getText());
            dir.setNUM_EXT(txtD_NUM_EXT.getText());
            dir.setCOLONIA(txtD_COLONIA.getText());
            dir.setID_ES(txtD_ID_ESTADO.getText());
            
            dirdao.ModificarDireccion(dir);
            JOptionPane.showMessageDialog(null, "Direccion Modificada");
        }
    }
    LimpiarTable();
    MostrarDireccion();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_MODIFICARActionPerformed

    private void btnD_ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_ELIMINARActionPerformed
         if(!"".equals(txtD_ID.getText()))
    {
        int pregunta = JOptionPane.showConfirmDialog(null, "¡Desea Eliminar?");
        if (pregunta == 0)
        {
            String ID_DIRECCION = (txtD_ID.getText());
            dirdao.EliminarDireccion(ID_DIRECCION); //720 Ltable, prod lostprot
        }
    }
    LimpiarTable();
    MostrarDireccion();
            // TODO add your handling code here:
    }//GEN-LAST:event_btnD_ELIMINARActionPerformed

    private void btnD_LIMPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_LIMPIARActionPerformed
    LimpiarTable();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_LIMPIARActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         String idDireccion = txtD_ID.getText();
        String alcaldiaMunicipio = txtD_ALCALDIA.getText();
        String cP = txtD_CP.getText();
        String calle = txtD_CALLE.getText();
        String numExt = txtD_NUM_EXT.getText();
        String colonia = txtD_COLONIA.getText();
        String idEstado = txtD_ID_ESTADO.getText();
        
        List<Direccion> BusquedaDir = dirdao.BuscarDireccion(idDireccion, alcaldiaMunicipio, cP, calle, numExt, colonia, idEstado);
        
        LimpiarTable();
        
        for(Direccion dir : BusquedaDir)
        {
            Object[] filad = {dir.getID_DIR(), dir.getALCALDIA_MUNICIPIO(), dir.getCP(), dir.getCALLE(), dir.getNUM_EXT(), dir.getCOLONIA(), dir.getID_ES()};
            modelo.addRow(filad);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtCTELEFONOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCTELEFONOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTELEFONOActionPerformed

    private void btnD_MOSTRAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_MOSTRAR1ActionPerformed
            MostrarCliente();   
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_MOSTRAR1ActionPerformed

    private void btnD_AGREGAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_AGREGAR1ActionPerformed
        if(!"".equals(txtCID.getText())|| !"".equals(txtCNOMBRE.getText()) || !"".equals(txtCTELEFONO.getText()) ||!"".equals(txtCCORREO.getText()))
    {
        Cliente cliente = new Cliente();
        
        cliente.setID_CLIENTE(txtCID.getText());
        cliente.setNOMBRE_CLIENTE(txtCNOMBRE.getText());
        cliente.setTELEFONO_C(txtCTELEFONO.getText());
        cliente.setCORREO_C(txtCCORREO.getText());
        
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.AgregarCliente(cliente);
        
        JOptionPane.showMessageDialog(null, "Cliente Registrado");
    }
    else
    {
        JOptionPane.showMessageDialog(null, "Campos Vacios");
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_AGREGAR1ActionPerformed

    private void btnD_MODIFICAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_MODIFICAR1ActionPerformed
        if("".equals(txtCID.getText()))
    {
        JOptionPane.showMessageDialog(null, "Seleccione una Fila");
    }
    else
    {
        if(!"".equals(txtCID.getText()) ||"".equals(txtCNOMBRE.getText()) ||"".equals(txtCTELEFONO.getText()) ||"".equals(txtCCORREO.getText()))
        {
            cli.setID_CLIENTE(txtCID.getText());
            cli.setNOMBRE_CLIENTE(txtCNOMBRE.getText());
            cli.setTELEFONO_C(txtCTELEFONO.getText());
            cli.setCORREO_C(txtCCORREO.getText());
            
            clidao.ModificarCliente(cli);
            JOptionPane.showMessageDialog(null, "Cliente Modificado");
            
        }
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_MODIFICAR1ActionPerformed

    private void btnD_ELIMINAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_ELIMINAR1ActionPerformed
if(!"".equals(txtCID.getText()))
    {
        int pregunta = JOptionPane.showConfirmDialog(null, "¡Desea Eliminar?");
        if (pregunta == 0)
        {
            String ID_CLIENTE = (txtCID.getText());
            clidao.EliminarCliente(ID_CLIENTE); //720 Ltable, prod lostprot
        }
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_ELIMINAR1ActionPerformed

    private void btnD_LIMPIAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_LIMPIAR1ActionPerformed
         LimpiarTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_LIMPIAR1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String idCliente = txtCID.getText();
        String nombreCliente = txtCNOMBRE.getText();
        String telefonoCliente = txtCTELEFONO.getText();
        String correoCliente = txtCCORREO.getText();
        
        List<Cliente> busquedaCli = clidao.BuscarCliente(idCliente, nombreCliente, telefonoCliente, correoCliente);
        
        LimpiarTable();
        
        for(Cliente cli : busquedaCli)
        {
            Object[] fila = {cli.getID_CLIENTE(), cli.getNOMBRE_CLIENTE(), cli.getTELEFONO_C(), cli.getCORREO_C()};
            modelo.addRow(fila);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtCorreoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoVentaActionPerformed

    private void txtTelefonoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoVentaActionPerformed

    private void btnPDFVENTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFVENTAActionPerformed
        try {
            File file = new File("src/Reportepdf/venta.pdf");
            Desktop.getDesktop().open(file);
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPDFVENTAActionPerformed

    private void btnActualizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarVentaActionPerformed

    }//GEN-LAST:event_btnActualizarVentaActionPerformed

    private void txtCodigoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoDetalleActionPerformed

    private void btnD_MOSTRAR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_MOSTRAR2ActionPerformed

        MostrarDetalleV();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_MOSTRAR2ActionPerformed

    private void btnD_AGREGAR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_AGREGAR2ActionPerformed
    if(!"".equals(txtCID.getText())|| !"".equals(txtCNOMBRE.getText()) || !"".equals(txtCTELEFONO.getText()) ||!"".equals(txtCCORREO.getText()))
    {
        Cliente cliente = new Cliente();
        
        cliente.setID_CLIENTE(txtCID.getText());
        cliente.setNOMBRE_CLIENTE(txtCNOMBRE.getText());
        cliente.setTELEFONO_C(txtCTELEFONO.getText());
        cliente.setCORREO_C(txtCCORREO.getText());
        
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.AgregarCliente(cliente);
        
        JOptionPane.showMessageDialog(null, "Cliente Registrado");
    }
    else
    {
        JOptionPane.showMessageDialog(null, "Campos Vacios");
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_AGREGAR2ActionPerformed

    private void btnD_MODIFICAR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_MODIFICAR2ActionPerformed
        if("".equals(txtCID.getText()))
    {
        JOptionPane.showMessageDialog(null, "Seleccione una Fila");
    }
    else
    {
        if(!"".equals(txtCID.getText()) ||"".equals(txtCNOMBRE.getText()) ||"".equals(txtCTELEFONO.getText()) ||"".equals(txtCCORREO.getText()))
        {
            cli.setID_CLIENTE(txtCID.getText());
            cli.setNOMBRE_CLIENTE(txtCNOMBRE.getText());
            cli.setTELEFONO_C(txtCTELEFONO.getText());
            cli.setCORREO_C(txtCCORREO.getText());
            
            clidao.ModificarCliente(cli);
            JOptionPane.showMessageDialog(null, "Cliente Modificado");
            
        }
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_MODIFICAR2ActionPerformed

    private void btnD_ELIMINAR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_ELIMINAR2ActionPerformed

if(!"".equals(txtCID.getText()))
    {
        int pregunta = JOptionPane.showConfirmDialog(null, "¡Desea Eliminar?");
        if (pregunta == 0)
        {
            String ID_CLIENTE = (txtCID.getText());
            clidao.EliminarCliente(ID_CLIENTE); //720 Ltable, prod lostprot
        }
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_ELIMINAR2ActionPerformed

    private void btnD_LIMPIAR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_LIMPIAR2ActionPerformed
         LimpiarTable();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_LIMPIAR2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String iddetalle = txtIdDetalleVenta.getText();
        String cantidad = txtCntidadDetalle.getText();
        String idpro = txtCodigoDetalle.getText();
        String idvent = txtVentaDetalle.getText();
        
        List<DetalleVenta> busquedadet = dvdao.BuscarDetalle( iddetalle,  cantidad,  idpro,  idvent);
        
        LimpiarTable();
        
        for(DetalleVenta dv : busquedadet)
        {
            Object[] fila = {dv.getID_DETALLEV(), dv.getCANTIDAD_PRODUCTOS_VENDIDOS(), dv.getID_PRODUCTO(), dv.getID_VENTA()};
            modelo.addRow(fila);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btnDetalleVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleVentaActionPerformed
    jTabbedPane1.setSelectedIndex(7);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetalleVentaActionPerformed

    private void btnD_ACTUALIZAR3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_ACTUALIZAR3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_ACTUALIZAR3ActionPerformed

    private void btnD_ACTUALIZAR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD_ACTUALIZAR2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnD_ACTUALIZAR2ActionPerformed

    private void txtIdSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSucursalActionPerformed

    private void TableSucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableSucursalMouseClicked
        // TODO add your handling code here:
        int fila = TableSucursal.rowAtPoint(evt.getPoint());
        txtIdSucursal.setText(TableSucursal.getValueAt(fila, 0).toString());
        txtNombreSucursal.setText(TableSucursal.getValueAt(fila, 1).toString());
        txtTelefonoSucursal.setText(TableSucursal.getValueAt(fila, 2).toString());
        txtCorreoSucursal.setText(TableSucursal.getValueAt(fila, 3).toString());
        txtPaginaWEBSucursal.setText(TableSucursal.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableSucursalMouseClicked

    private void txtNombreSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreSucursalActionPerformed

    private void txtTelefonoSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoSucursalActionPerformed

    private void txtCorreoSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoSucursalActionPerformed

    private void btnBuscarEnvio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEnvio1ActionPerformed
        // Obtener los valores de los campos de búsqueda
        // Crear una instancia de SucursalDAO
        // Crear una instancia de SucursalDAO
        SucursalDAO sucursalDAO = new SucursalDAO();

        // Obtener los valores de los campos de búsqueda
        String idSucursal = txtIdSucursal.getText();
        String nombreSucursal = txtNombreSucursal.getText();
        String telefonoSucursal = txtTelefonoSucursal.getText();
        String correoSucursal = txtCorreoSucursal.getText();
        String idPW = txtPaginaWEBSucursal.getText();

        // Realizar la búsqueda utilizando el método BuscarSucursal de la instancia de SucursalDAO
        List<Sucursal> resultados = sucursalDAO.BuscarSucursal(idSucursal, nombreSucursal, telefonoSucursal, correoSucursal, idPW);

        // Limpiar la tabla antes de agregar los nuevos resultados
        LimpiarTable();

        // Llenar la tabla con los resultados de la búsqueda
        for (Sucursal sucursal : resultados) {
            Object[] fila = {sucursal.getID_DE_LA_SUCURSAL(), sucursal.getNOMBRE_SUCURSAL(), sucursal.getTELEFONO_S(), sucursal.getCORREO_S(), sucursal.getPAGINA_WEB_ID_PW()};
            modelo.addRow(fila);
        }

        // Notificar que la tabla se actualizó
        JOptionPane.showMessageDialog(null, "Búsqueda de sucursales completada");

    }//GEN-LAST:event_btnBuscarEnvio1ActionPerformed

    private void txtPaginaWEBSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaginaWEBSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaginaWEBSucursalActionPerformed

    private void btnGuardarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSucursalActionPerformed
        // Crear una instancia de SucursalDAO
        SucursalDAO sucursalDAO = new SucursalDAO();

        if (!"".equals(txtIdSucursal.getText()) || !"".equals(txtNombreSucursal.getText()) || !"".equals(txtTelefonoSucursal.getText()) || !"".equals(txtCorreoSucursal.getText()) || !"".equals(txtPaginaWEBSucursal.getText())) {
            // Crear una instancia de Sucursal
            Sucursal sucursal = new Sucursal();
            sucursal.setID_SUCURSAL(txtIdSucursal.getText());

            sucursal.setNOMBRE_SUCURSAL(txtNombreSucursal.getText());
            sucursal.setTELEFONO_S(txtTelefonoSucursal.getText());
            sucursal.setCORREO_S(txtCorreoSucursal.getText());
            sucursal.setPAGINA_WEB_ID_PW(txtPaginaWEBSucursal.getText());

            // Utiliza la instancia de SucursalDAO para registrar la sucursal
            sucursalDAO.RegistrarSucursal(sucursal);
            JOptionPane.showMessageDialog(null, "Sucursal Registrada");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos están vacíos");
        }

    }//GEN-LAST:event_btnGuardarSucursalActionPerformed

    private void btnActualizarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarSucursalActionPerformed
        if ("".equals(txtIdSucursal.getText())) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        } else {
            if (!"".equals(txtIdSucursal.getText()) || !"".equals(txtNombreSucursal.getText()) || !"".equals(txtTelefonoSucursal.getText()) || !"".equals(txtCorreoSucursal.getText()) || !"".equals(txtPaginaWEBSucursal.getText())) {
                Sucursal sucursal = new Sucursal();
                sucursal.setID_SUCURSAL(txtIdSucursal.getText());
                sucursal.setNOMBRE_SUCURSAL(txtNombreSucursal.getText());
                sucursal.setTELEFONO_S(txtTelefonoSucursal.getText());
                sucursal.setCORREO_S(txtCorreoSucursal.getText());
                sucursal.setPAGINA_WEB_ID_PW(txtPaginaWEBSucursal.getText());

                // Crea una instancia de SucursalDAO
                SucursalDAO sucursalDAO = new SucursalDAO();

                // Modifica el método ModificarSucursal según tu implementación
                if (sucursalDAO.ModificarSucursal(sucursal)) {
                    JOptionPane.showMessageDialog(null, "Sucursal modificada exitosamente");
                    LimpiarTable();
                    ListaSucursales();
                    LimpiarSucursal();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar la sucursal");
                }
            }
        }

    }//GEN-LAST:event_btnActualizarSucursalActionPerformed

    private void btnEliminarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSucursalActionPerformed
        SucursalDAO SucursalDAO = new SucursalDAO();
        if (!"".equals(txtIdSucursal.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar?");
            if (pregunta == 0) {
                String idSucursal = txtIdSucursal.getText();
                SucursalDAO.EliminarSucursal(idSucursal);
                LimpiarTable();
                LimpiarSucursal();
                ListaSucursales();
            }
        }

        // ...
    }//GEN-LAST:event_btnEliminarSucursalActionPerformed

    private void btnSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSucursalActionPerformed
LimpiarTable();
        ListaSucursales();
        jTabbedPane1.setSelectedIndex(8);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSucursalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JTable TableDetalleV;
    private javax.swing.JTable TableDetallesCompra;
    private javax.swing.JTable TableEnvios;
    private javax.swing.JTable TableProducto;
    private javax.swing.JTable TableProveedor;
    private javax.swing.JTable TableSucursal;
    private javax.swing.JTable TableVenta;
    private javax.swing.JButton btnActualizarEnvio;
    private javax.swing.JButton btnActualizarProveedor;
    private javax.swing.JButton btnActualizarSucursal;
    private javax.swing.JButton btnActualizarVenta;
    private javax.swing.JButton btnBuscarDetalleC;
    private javax.swing.JButton btnBuscarEnvio;
    private javax.swing.JButton btnBuscarEnvio1;
    private javax.swing.JButton btnBuscarProveedor;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnClientes1;
    private javax.swing.JButton btnD_ACTUALIZAR1;
    private javax.swing.JButton btnD_ACTUALIZAR2;
    private javax.swing.JButton btnD_ACTUALIZAR3;
    private javax.swing.JButton btnD_AGREGAR;
    private javax.swing.JButton btnD_AGREGAR1;
    private javax.swing.JButton btnD_AGREGAR2;
    private javax.swing.JButton btnD_ELIMINAR;
    private javax.swing.JButton btnD_ELIMINAR1;
    private javax.swing.JButton btnD_ELIMINAR2;
    private javax.swing.JButton btnD_LIMPIAR;
    private javax.swing.JButton btnD_LIMPIAR1;
    private javax.swing.JButton btnD_LIMPIAR2;
    private javax.swing.JButton btnD_MODIFICAR;
    private javax.swing.JButton btnD_MODIFICAR1;
    private javax.swing.JButton btnD_MODIFICAR2;
    private javax.swing.JButton btnD_MOSTRAR;
    private javax.swing.JButton btnD_MOSTRAR1;
    private javax.swing.JButton btnD_MOSTRAR2;
    private javax.swing.JButton btnDetalleVenta;
    private javax.swing.JButton btnDireccion;
    private javax.swing.JButton btnEditarPro;
    private javax.swing.JButton btnEliminarEnvio;
    private javax.swing.JButton btnEliminarPro;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarSucursal;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnEnvios;
    private javax.swing.JButton btnGuardarEnvio;
    private javax.swing.JButton btnGuardarPro;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnGuardarSucursal;
    private javax.swing.JButton btnGuardarVenta;
    private javax.swing.JButton btnNuevoPro;
    private javax.swing.JButton btnPDFVENTA;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnSucursal;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnVerProducto;
    private javax.swing.JComboBox<String> cbxMetodoPago;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTable tbCLIENTE;
    private javax.swing.JTable tbDireccion;
    private javax.swing.JTextField txtCCORREO;
    private javax.swing.JTextField txtCID;
    private javax.swing.JTextField txtCNOMBRE;
    private javax.swing.JTextField txtCTELEFONO;
    private javax.swing.JTextField txtCantidadCompra;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCarPro;
    private javax.swing.JTextField txtCatPro;
    private javax.swing.JTextField txtClienteVenta;
    private javax.swing.JTextField txtCntidadDetalle;
    private javax.swing.JTextField txtCodigoDetalle;
    private javax.swing.JTextField txtContactoProveedor;
    private javax.swing.JTextField txtCorreoProveedor;
    private javax.swing.JTextField txtCorreoSucursal;
    private javax.swing.JTextField txtCorreoVenta;
    private javax.swing.JTextField txtD_ALCALDIA;
    private javax.swing.JTextField txtD_CALLE;
    private javax.swing.JTextField txtD_COLONIA;
    private javax.swing.JTextField txtD_CP;
    private javax.swing.JTextField txtD_ID;
    private javax.swing.JTextField txtD_ID_ESTADO;
    private javax.swing.JTextField txtD_NUM_EXT;
    private javax.swing.JTextField txtDesPro;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaVenta;
    private javax.swing.JTextField txtIDProductoProveedor;
    private javax.swing.JTextField txtIDProveedor;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdCompraD;
    private javax.swing.JTextField txtIdDetalleC;
    private javax.swing.JTextField txtIdDetalleV;
    private javax.swing.JTextField txtIdDetalleVenta;
    private javax.swing.JTextField txtIdDirE;
    private javax.swing.JTextField txtIdEmpleadoE;
    private javax.swing.JTextField txtIdEnvio;
    private javax.swing.JTextField txtIdPro;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtIdProductoD;
    private javax.swing.JTextField txtIdSucursal;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtIdVentaE;
    private javax.swing.JTextField txtInPro;
    private javax.swing.JTextField txtMarPro;
    private javax.swing.JTextField txtModPro;
    private javax.swing.JTextField txtNomPro;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreSucursal;
    private javax.swing.JTextField txtPaginaWEBSucursal;
    private javax.swing.JTextField txtPrePro;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtProductoVenta;
    private javax.swing.JTextField txtSoPro;
    private javax.swing.JTextField txtStockVenta;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTelefonoSucursal;
    private javax.swing.JTextField txtTelefonoVenta;
    private javax.swing.JTextField txtVendedorVenta;
    private javax.swing.JTextField txtVentaDetalle;
    // End of variables declaration//GEN-END:variables

private void updateTotal() {
    Totalpagar = 0;

    // Ensure that TableVenta and LabelTotal are not null
    if (TableVenta != null && LabelTotal != null) {
        // Iterate through the rows of TableVenta and calculate the total
        for (int i = 0; i < TableVenta.getRowCount(); i++) {
            Object totalObject = TableVenta.getValueAt(i, 4);

            // Handle the case where precioObject or cantidadObject is null
            if (totalObject != null) {
                try {
                    float total = ((Number) totalObject).floatValue();
                    float subtotal = total;
                    Totalpagar = Totalpagar+subtotal;
                } catch (ClassCastException e) {
                    System.out.println("Error casting values in TableVenta");
                    e.printStackTrace();
                }
            }
        }

        // Set the calculated total to LabelTotal
        LabelTotal.setText(String.format("%.2f", Totalpagar));
    } else {
        System.out.println("TableVenta or LabelTotal is null");
    }
}

private void LimpiarVenta(){
    txtIdProducto.setText("");
    txtProductoVenta.setText("");
    txtPrecioVenta.setText("");
    txtStockVenta.setText("");
    txtCantidadVenta.setText("");
    txtIdDetalleV.setText("");
}

private void LimpiarClienteVenta(){
    txtIdCliente.setText("");
}

private void RegistrarVenta() {
    String cliente = txtIdCliente.getText();
    if (cliente != null && !cliente.isEmpty()) {
        String vendedor = txtVendedorVenta.getText();
        String pago = cbxMetodoPago.getSelectedItem().toString();
        int vent = Integer.parseInt(txtIdVenta.getText());
        float monto = Totalpagar;
        
        //AQUI PARA OBTENER LA FECHA ACTUAL 
        Date fechaActual = new Date();
        
        ven.setID_VENTA(vent);
        ven.setID_CLIENTE(cliente);
        ven.setNOMBRE_EMPLEADO(vendedor);
        ven.setID_M(pago);
        ven.setTOTAL_VENTA(monto);
        ven.setFECHA_VENTA(fechaActual); 

        vendao.RegistrarVenta(ven);//AQUI ES PARA QUE SE GUANDEN TODOS LOS VALORES EN LA DB
    } else {
        System.out.println("Error.");
    }
}

private void RegistrarDetalleVenta(){
    int id = vendao.IdVenta();
    int idv = vendao.obtenerNuevoIdDetalle();
    for (int i = 0; i < TableVenta.getRowCount(); i++) {
        String idpro = TableVenta.getValueAt(i, 0).toString();
        int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
        dv.setID_DETALLEV(idv);
        dv.setID_PRODUCTO(idpro);
        dv.setCANTIDAD_PRODUCTOS_VENDIDOS(cant);
        dv.setID_VENTA(id);
        vendao.RegistrarDetalle(dv);

    }
    
}

private void ActualizarStock(){
    for (int i = 0; i < TableVenta.getRowCount(); i++) {
        String cod = TableVenta.getValueAt(i, 0).toString();
        int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
        pro = prodao.BucarProducto(cod);
        int stockactual = pro.getINVENTARIO_EN_STOCK()-cant;
        vendao.ActualizarStock(stockactual, cod);
    }
}

private void  LimpiarTableVenta(){
    tmp = (DefaultTableModel) TableVenta.getModel();
    int fila = TableVenta.getRowCount();
    for (int i = 0; i < fila; i++) {
        tmp.removeRow(0);
        
    }
}

private void LimpiarClientVenta(){
       txtIdCliente.setText("");
        txtClienteVenta.setText("");
        txtIdVenta.setText("");
        LabelTotal.setText("");
}

private void Reportepdf(){
    try{
        FileOutputStream archivo;
        File file= new File("src/Reportepdf/venta.pdf");
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter.getInstance(doc,archivo);
        doc.open();
        Image img =Image.getInstance("src/imagenes/Logo_IPN.png");
        
        Paragraph fecha = new Paragraph();
        Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD,BaseColor.BLUE);
        fecha.add(Chunk.NEWLINE);
        Date date = new Date();
        fecha.add("Fecha: "+ new SimpleDateFormat("dd-mm-yyyy").format(date)+"\n\n");
        
        PdfPTable Encabezado = new PdfPTable(4);
        Encabezado.setWidthPercentage(100);
        Encabezado.getDefaultCell().setBorder(0);
        float[] ColumnaEncabezado = new float[]{20f,30f,70f,40f};
        Encabezado.setWidths(ColumnaEncabezado);
        Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
        
        Encabezado.addCell(img);
        String ruc = "S01";
        String nomsuc = "SmartTech";
        String cel = "5510203040";
        String correo = "support@smarttech.com";
        String pag = "www.smarttech.com";
        
        Encabezado.addCell("");
        Encabezado.addCell("Sucursal: "+ruc+"\nNombre: "+nomsuc+"\nTeléfono: "+cel+"\nCorero: "+correo+"\nWeb: "+pag);
        Encabezado.addCell(fecha);
        doc.add(Encabezado);
        
        Paragraph clien = new Paragraph();
        clien.add(Chunk.NEWLINE);
        clien.add("\n");
        doc.add(clien);
        
        PdfPTable tablacli = new PdfPTable(4);        
        tablacli.setWidthPercentage(100);
        tablacli.getDefaultCell().setBorder(0);
        float[] Columnacli = new float[]{20f,50f,30f,40f};
        tablacli.setWidths(Columnacli);
        tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
        
        PdfPCell  clien1 = new PdfPCell(new Phrase("Id Cliente"));
        PdfPCell  clien2 = new PdfPCell(new Phrase("Nombre"));
        PdfPCell  clien3 = new PdfPCell(new Phrase("Teléfono"));
        PdfPCell  clien4 = new PdfPCell(new Phrase("Correo"));
        clien1.setBorder(0);
        clien2.setBorder(0);
        clien3.setBorder(0);
        clien4.setBorder(0);
        tablacli.addCell(clien1);
        tablacli.addCell(clien2);
        tablacli.addCell(clien3);
        tablacli.addCell(clien4);
        tablacli.addCell(txtIdCliente.getText());
        tablacli.addCell(txtClienteVenta.getText());
        tablacli.addCell(txtTelefonoVenta.getText());
        tablacli.addCell(txtCorreoVenta.getText());
        doc.add(tablacli);
    
        //Productos
        PdfPTable tablapro = new PdfPTable(4);        
        tablapro.setWidthPercentage(100);
        tablapro.getDefaultCell().setBorder(0);
        float[] Columnapro = new float[]{20f,50f,30f,40f};
        tablapro.setWidths(Columnapro);
        tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
        
        PdfPCell  pro1 = new PdfPCell(new Phrase("Cantidad"));
        PdfPCell  pro2 = new PdfPCell(new Phrase("Producto"));
        PdfPCell  pro3 = new PdfPCell(new Phrase("Precio U."));
        PdfPCell  pro4 = new PdfPCell(new Phrase("Total."));
        pro1.setBorder(0);
        pro2.setBorder(0);
        pro3.setBorder(0);
        pro4.setBorder(0);
        tablapro.addCell(pro1);
        tablapro.addCell(pro2);
        tablapro.addCell(pro3);
        tablapro.addCell(pro4);
        for (int i = 0; i < TableVenta.getRowCount(); i++) {
            String producto = TableVenta.getValueAt(i, 1).toString();
            String cantidad = TableVenta.getValueAt(i, 2).toString();
            String precio = TableVenta.getValueAt(i, 3).toString();
            String total = TableVenta.getValueAt(i, 4).toString();
            tablapro.addCell(cantidad);
            tablapro.addCell(producto);
            tablapro.addCell(precio);
            tablapro.addCell(total);
            
        }
        doc.add(tablapro);
        
        Paragraph info = new Paragraph();
        info.add(Chunk.NEWLINE);
        info.add("Total a Pagar: "+ Totalpagar );
        info.setAlignment(Element.ALIGN_RIGHT);
        doc.add(info);
        
        
        Paragraph mensaje = new Paragraph();
        mensaje.add(Chunk.NEWLINE);
        mensaje.add("Gracias por su Compra" );
        mensaje.setAlignment(Element.ALIGN_CENTER);
        doc.add(mensaje);
        
        
        doc.close();
        archivo.close();
    }catch(Exception e){}
}
     public void ListaDetallesCompra() {
    List<DetalleCompra> ListarDetallesCompra = detalleCompraDAO.ListarDetalleCompra(); // Asegúrate de tener el método correspondiente en tu clase DetalleCompraDao
    modelo = (DefaultTableModel) TableDetallesCompra.getModel(); // Asegúrate de tener un JTable llamado TableDetallesCompra en tu interfaz
    Object[] ob = new Object[10];

    for(int i = 0; i < ListarDetallesCompra.size(); i++) {
        ob[0] = ListarDetallesCompra.get(i).getIdDetalleCompra();
        ob[1] = ListarDetallesCompra.get(i).getCantidadCompra();
        ob[2] = ListarDetallesCompra.get(i).getIdCompra();
        ob[3] = ListarDetallesCompra.get(i).getIdProducto();
        // Continúa con los campos adicionales según sea necesario

        modelo.addRow(ob);
    }

    TableDetallesCompra.setModel(modelo);
}
     public void ListaEnvios(){
    List<Envio> listaEnvios = EnvioDAO.ListarEnvios();
    modelo = (DefaultTableModel) TableEnvios.getModel();
    Object[] ob = new Object[5];
     
    for(int i = 0; i < listaEnvios.size(); i++){
        ob[0] = listaEnvios.get(i).getID_ENVIO();
        ob[1] = listaEnvios.get(i).getFECHA();
        ob[2] = listaEnvios.get(i).getID_VENTA();
        ob[3] = listaEnvios.get(i).getID_DIR();
        ob[4] = listaEnvios.get(i).getID_EMPLEADO();
        modelo.addRow(ob);
    }
    TableEnvios.setModel(modelo);
}

public void LimpiarTableEnvios(){
    for(int i = 0; i < modelo.getRowCount(); i++){
        modelo.removeRow(i);
        i = i - 1;
    }
}

public void LimpiarEnvio(){
    txtIdEnvio.setText("");
    txtFecha.setText("");
    txtIdVentaE.setText("");
    txtIdDirE.setText("");
    txtIdEmpleadoE.setText("");
}

 public void MostrarDireccion()
    {
        List<Direccion> MostrarDir = dirdao.MostrarDireccion();
        modelo = (DefaultTableModel) tbDireccion.getModel();
        Object[] ob = new Object[7];
        
        for (int i = 0; i < MostrarDir.size(); i++) 
        {
            ob[0] = MostrarDir.get(i).getID_DIR();
            ob[1] = MostrarDir.get(i).getALCALDIA_MUNICIPIO();
            ob[2] = MostrarDir.get(i).getCP();
            ob[3] = MostrarDir.get(i).getCALLE();
            ob[4] = MostrarDir.get(i).getNUM_EXT();
            ob[5] = MostrarDir.get(i).getCOLONIA();
            ob[6] = MostrarDir.get(i).getID_ES();
            modelo.addRow(ob);
        }
        tbDireccion.setModel(modelo);
    }
 
     public void MostrarCliente()
    {
        List<Cliente> MostrarCli = clidao.MostrarCliente();
        modelo = (DefaultTableModel) tbCLIENTE.getModel();
        Object[] ob = new Object[4];
        
        for (int i = 0; i<MostrarCli.size(); i++) 
        {
            ob[0] = MostrarCli.get(i).getID_CLIENTE();
            ob[1] = MostrarCli.get(i).getNOMBRE_CLIENTE();
            ob[2] = MostrarCli.get(i).getTELEFONO_C();
            ob[3] = MostrarCli.get(i).getCORREO_C();
            modelo.addRow(ob);
        }
        tbCLIENTE.setModel(modelo);
    }
     
          public void MostrarDetalleV()
    {
        List<DetalleVenta> MostrarDetalle = dvdao.MostrarDetalleV();
        modelo = (DefaultTableModel) TableDetalleV.getModel();
        Object[] ob = new Object[4];
        
        for (int i = 0; i<MostrarDetalle.size(); i++) 
        {
            ob[0] = MostrarDetalle.get(i).getID_DETALLEV();
            ob[1] = MostrarDetalle.get(i).getCANTIDAD_PRODUCTOS_VENDIDOS();
            ob[2] = MostrarDetalle.get(i).getID_PRODUCTO();
            ob[3] = MostrarDetalle.get(i).getID_VENTA();
            modelo.addRow(ob);
        }
        TableDetalleV.setModel(modelo);
    }
          
   public void ListaSucursales() {
        List<Sucursal> listaSucursales = sucursalDAO.ListarSucursales();
        modelo = (DefaultTableModel) TableSucursal.getModel();
        Object[] ob = new Object[5];

        for (int i = 0; i < listaSucursales.size(); i++) {
            ob[0] = listaSucursales.get(i).getID_DE_LA_SUCURSAL();
            ob[1] = listaSucursales.get(i).getNOMBRE_SUCURSAL();
            ob[2] = listaSucursales.get(i).getTELEFONO_S();
            ob[3] = listaSucursales.get(i).getCORREO_S();
            ob[4] = listaSucursales.get(i).getPAGINA_WEB_ID_PW();
            modelo.addRow(ob);
        }
        TableSucursal.setModel(modelo);
    }

    public void LimpiarTableSucursal() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void LimpiarSucursal() {
        // Limpiar los campos de la interfaz relacionados con la sucursal
        txtIdSucursal.setText("");
        txtNombreSucursal.setText("");
        txtTelefonoSucursal.setText("");
        txtCorreoSucursal.setText("");
        txtPaginaWEBSucursal.setText("");
    }

}
