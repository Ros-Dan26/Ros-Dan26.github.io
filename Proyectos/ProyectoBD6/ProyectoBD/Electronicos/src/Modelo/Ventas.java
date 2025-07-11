
package Modelo;

import java.util.Date;

public class Ventas {
    private int ID_VENTA;        
    private String ID_CLIENTE;
    private String NOMBRE_EMPLEADO;
    private String ID_M;
    private float TOTAL_VENTA;
    private Date FECHA_VENTA;
    
    public Ventas(){
}

    public Ventas(int ID_VENTA, String ID_CLIENTE, String NOMBRE_EMPLEADO, String ID_M, float TOTAL_VENTA, Date FECHA_VENTA) {
        this.ID_VENTA = ID_VENTA;
        this.ID_CLIENTE = ID_CLIENTE;
        this.NOMBRE_EMPLEADO = NOMBRE_EMPLEADO;
        this.ID_M = ID_M;
        this.TOTAL_VENTA = TOTAL_VENTA;
        this.FECHA_VENTA = FECHA_VENTA;
    }

    public int getID_VENTA() {
        return ID_VENTA;
    }

    public void setID_VENTA(int ID_VENTA) {
        this.ID_VENTA = ID_VENTA;
    }

    public String getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getNOMBRE_EMPLEADO() {
        return NOMBRE_EMPLEADO;
    }

    public void setNOMBRE_EMPLEADO(String NOMBRE_EMPLEADO) {
        this.NOMBRE_EMPLEADO = NOMBRE_EMPLEADO;
    }

    public String getID_M() {
        return ID_M;
    }

    public void setID_M(String ID_M) {
        this.ID_M = ID_M;
    }

    public float getTOTAL_VENTA() {
        return TOTAL_VENTA;
    }

    public void setTOTAL_VENTA(float TOTAL_VENTA) {
        this.TOTAL_VENTA = TOTAL_VENTA;
    }

    public Date getFECHA_VENTA() {
        return FECHA_VENTA;
    }

    public void setFECHA_VENTA(Date FECHA_VENTA) {
        this.FECHA_VENTA = FECHA_VENTA;
    }
    
}
