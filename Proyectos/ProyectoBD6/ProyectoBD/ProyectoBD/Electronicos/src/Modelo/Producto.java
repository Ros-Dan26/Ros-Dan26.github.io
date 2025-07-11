
package Modelo;


public class Producto 
{
    private  String ID_PRODUCTO;
    private  String NOMBRE_PRODUCTO;
    private  String CATEGORÍA;
    private  int PRECIO;
    private  String MODELO;
    private  String MARCA;
    private  String DESCRIPCION;
    private  String CARACTERISTICAS;
    private  String NOMBRE_DEL_SO;
    private  int INVENTARIO_EN_STOCK;

    
    public Producto()
    {
        
    }

    public Producto(String ID_PRODUCTO, String NOMBRE_PRODUCTO, String CATEGORÍA, int PRECIO, String MODELO, String MARCA, String DESCRIPCION, String CARACTERISTICAS, String NOMBRE_DEL_SO, int INVENTARIO_EN_STOCK) 
    {
        this.ID_PRODUCTO = ID_PRODUCTO;
        this.NOMBRE_PRODUCTO = NOMBRE_PRODUCTO;
        this.CATEGORÍA = CATEGORÍA;
        this.PRECIO = PRECIO;
        this.MODELO = MODELO;
        this.MARCA = MARCA;
        this.DESCRIPCION = DESCRIPCION;
        this.CARACTERISTICAS = CARACTERISTICAS;
        this.NOMBRE_DEL_SO = NOMBRE_DEL_SO;
        this.INVENTARIO_EN_STOCK = INVENTARIO_EN_STOCK;
    }

    public String getID_PRODUCTO() 
    {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(String ID_PRODUCTO) 
    {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public String getNOMBRE_PRODUCTO() 
    {
        return NOMBRE_PRODUCTO;
    }

    public void setNOMBRE_PRODUCTO(String NOMBRE_PRODUCTO) 
    {
        this.NOMBRE_PRODUCTO = NOMBRE_PRODUCTO;
    }

    public String getCATEGORÍA() 
    {
        return CATEGORÍA;
    }

    public void setCATEGORÍA(String CATEGORÍA) 
    {
        this.CATEGORÍA = CATEGORÍA;
    }

    public int getPRECIO() 
    {
        return PRECIO;
    }

    public void setPRECIO(int PRECIO) 
    {
        this.PRECIO = PRECIO;
    }

    public String getMODELO() 
    {
        return MODELO;
    }

    public void setMODELO(String MODELO) 
    {
        this.MODELO = MODELO;
    }

    public String getMARCA() 
    {
        return MARCA;
    }

    public void setMARCA(String MARCA) 
    {
        this.MARCA = MARCA;
    }

    public String getDESCRIPCION() 
    {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) 
    {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getCARACTERISTICAS() 
    {
        return CARACTERISTICAS;
    }

    public void setCARACTERISTICAS(String CARACTERISTICAS) 
    {
        this.CARACTERISTICAS = CARACTERISTICAS;
    }

    public String getNOMBRE_DEL_SO() 
    {
        return NOMBRE_DEL_SO;
    }

    public void setNOMBRE_DEL_SO(String NOMBRE_DEL_SO) 
    {
        this.NOMBRE_DEL_SO = NOMBRE_DEL_SO;
    }

    public int getINVENTARIO_EN_STOCK() 
    {
        return INVENTARIO_EN_STOCK;
    }

    public void setINVENTARIO_EN_STOCK(int INVENTARIO_EN_STOCK) 
    {
        this.INVENTARIO_EN_STOCK = INVENTARIO_EN_STOCK;
    }
    
    
    
}
