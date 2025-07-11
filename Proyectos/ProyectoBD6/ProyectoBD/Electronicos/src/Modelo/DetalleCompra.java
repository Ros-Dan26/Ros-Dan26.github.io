package Modelo;

public class DetalleCompra {
    private String ID_DETALLEC;
    private String CANTIDAD_COMPRA;
    private String ID_COMPRA;
    private String ID_PRODUCTO;

    public DetalleCompra() {}

    public DetalleCompra(String idDetalleCompra, String cantidadCompra, String idCompra, String idProducto) {
        this.ID_DETALLEC = idDetalleCompra;
        this.CANTIDAD_COMPRA = cantidadCompra;
        this.ID_COMPRA = idCompra;
        this.ID_PRODUCTO = idProducto;
    }

    public String getIdDetalleCompra() {
        return ID_DETALLEC;
    }

    public void setIdDetalleCompra(String idDetalleCompra) {
        this.ID_DETALLEC = idDetalleCompra;
    }

    public String getCantidadCompra() {
        return CANTIDAD_COMPRA;
    }

    public void setCantidadCompra(String cantidadCompra) {
        this.CANTIDAD_COMPRA = cantidadCompra;
    }

    public String getIdCompra() {
        return ID_COMPRA;
    }

    public void setIdCompra(String idCompra) {
        this.ID_COMPRA = idCompra;
    }

    public String getIdProducto() {
        return ID_PRODUCTO;
    }

    public void setIdProducto(String idProducto) {
        this.ID_PRODUCTO = idProducto;
    }
}

