package Modelo;
 import java.util.Date;
public class Envio {
    private String ID_ENVIO;
    private Date FECHA;
    private int ID_VENTA;
    private String ID_DIR;
    private String ID_EMPLEADO;

    public Envio() {
        // Constructor por defecto
    }

    public Envio(String ID_ENVIO, Date FECHA, int ID_VENTA, String ID_DIR, String ID_EMPLEADO) {
        this.ID_ENVIO = ID_ENVIO;
        this.FECHA = FECHA;
        this.ID_VENTA = ID_VENTA;
        this.ID_DIR = ID_DIR;
        this.ID_EMPLEADO = ID_EMPLEADO;
    }

    public String getID_ENVIO() {
        return ID_ENVIO;
    }

    public void setID_ENVIO(String ID_ENVIO) {
        this.ID_ENVIO = ID_ENVIO;
    }

    public Date getFECHA() {
        return FECHA;
    }

    public void setFECHA(Date FECHA) {
        this.FECHA = FECHA;
    }

    public int getID_VENTA() {
        return ID_VENTA;
    }

    public void setID_VENTA(int ID_VENTA) {
        this.ID_VENTA = ID_VENTA;
    }

    public String getID_DIR() {
        return ID_DIR;
    }

    public void setID_DIR(String ID_DIR) {
        this.ID_DIR = ID_DIR;
    }

    public String getID_EMPLEADO() {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(String ID_EMPLEADO) {
        this.ID_EMPLEADO = ID_EMPLEADO;
    }
}
