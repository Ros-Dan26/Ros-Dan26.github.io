
package Modelo;


public class DetalleVenta {
    private int ID_DETALLEV;
    private int CANTIDAD_PRODUCTOS_VENDIDOS;
    private String ID_PRODUCTO;
    private int ID_VENTA;
    
    public DetalleVenta(){}

    public DetalleVenta(int ID_DETALLEV, int CANTIDAD_PRODUCTOS_VENDIDOS, String ID_PRODUCTO, int ID_VENTA) {
        this.ID_DETALLEV = ID_DETALLEV;
        this.CANTIDAD_PRODUCTOS_VENDIDOS = CANTIDAD_PRODUCTOS_VENDIDOS;
        this.ID_PRODUCTO = ID_PRODUCTO;
        this.ID_VENTA = ID_VENTA;
    }

    public int getID_DETALLEV() {
        return ID_DETALLEV;
    }

    public void setID_DETALLEV(int ID_DETALLEV) {
        this.ID_DETALLEV = ID_DETALLEV;
    }

    public int getCANTIDAD_PRODUCTOS_VENDIDOS() {
        return CANTIDAD_PRODUCTOS_VENDIDOS;
    }

    public void setCANTIDAD_PRODUCTOS_VENDIDOS(int CANTIDAD_PRODUCTOS_VENDIDOS) {
        this.CANTIDAD_PRODUCTOS_VENDIDOS = CANTIDAD_PRODUCTOS_VENDIDOS;
    }

    public String getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(String ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public int getID_VENTA() {
        return ID_VENTA;
    }

    public void setID_VENTA(int ID_VENTA) {
        this.ID_VENTA = ID_VENTA;
    }
    
}
