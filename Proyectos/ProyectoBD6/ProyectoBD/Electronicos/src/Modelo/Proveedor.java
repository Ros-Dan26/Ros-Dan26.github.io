package Modelo;

public class Proveedor {
    private String ID_PROVEEDOR;
    private String NOMBRE_PROVEEDOR;
    private String TELEFONO_P;
    private String CORREO_P;
    private String CONTACTO;
    private String ID_PRODUCTO;

    public Proveedor() {
        // Constructor por defecto
    }

    public Proveedor(String ID_PROVEEDOR, String NOMBRE_PROVEEDOR, String TELEFONO_P, String CORREO_P, String CONTACTO, String ID_PRODUCTO) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
        this.NOMBRE_PROVEEDOR = NOMBRE_PROVEEDOR;
        this.TELEFONO_P = TELEFONO_P;
        this.CORREO_P = CORREO_P;
        this.CONTACTO = CONTACTO;
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public String getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }

    public void setID_PROVEEDOR(String ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }

    public String getNOMBRE_PROVEEDOR() {
        return NOMBRE_PROVEEDOR;
    }

    public void setNOMBRE_PROVEEDOR(String NOMBRE_PROVEEDOR) {
        this.NOMBRE_PROVEEDOR = NOMBRE_PROVEEDOR;
    }

    public String getTELEFONO_P() {
        return TELEFONO_P;
    }

    public void setTELEFONO_P(String TELEFONO_P) {
        this.TELEFONO_P = TELEFONO_P;
    }

    public String getCORREO_P() {
        return CORREO_P;
    }

    public void setCORREO_P(String CORREO_P) {
        this.CORREO_P = CORREO_P;
    }

    public String getCONTACTO() {
        return CONTACTO;
    }

    public void setCONTACTO(String CONTACTO) {
        this.CONTACTO = CONTACTO;
    }

    public String getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(String ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }
}
