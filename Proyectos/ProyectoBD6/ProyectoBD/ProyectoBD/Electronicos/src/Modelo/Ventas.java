
package Modelo;

import java.time.LocalDate;

public class Ventas 
{
    private String ID_VENTA;        
    private LocalDate FECHA_VENTA;
    private String ID_CLIENTE;
    private String ID_EMPLEADO;
    private String ID_M;
    private int TOTAL_VENTA;
    
    public Ventas()
    {
        
    }

    public Ventas(String ID_VENTA, LocalDate FECHA_VENTA, String ID_CLIENTE, String ID_EMPLEADO, String ID_M, int TOTAL_VENTA) 
    {
        this.ID_VENTA = ID_VENTA;
        this.FECHA_VENTA = FECHA_VENTA;
        this.ID_CLIENTE = ID_CLIENTE;
        this.ID_EMPLEADO = ID_EMPLEADO;
        this.ID_M = ID_M;
        this.TOTAL_VENTA = TOTAL_VENTA;
    }

    public String getID_VENTA() 
    {
        return ID_VENTA;
    }

    public void setID_VENTA(String ID_VENTA) 
    {
        this.ID_VENTA = ID_VENTA;
    }

    public LocalDate getFECHA_VENTA() 
    {
        return FECHA_VENTA;
    }

    public void setFECHA_VENTA(LocalDate FECHA_VENTA) 
    {
        this.FECHA_VENTA = FECHA_VENTA;
    }

    public String getID_CLIENTE() 
    {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String ID_CLIENTE) 
    {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getID_EMPLEADO() 
    {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(String ID_EMPLEADO) 
    {
        this.ID_EMPLEADO = ID_EMPLEADO;
    }

    public String getID_M() 
    {
        return ID_M;
    }

    public void setID_M(String ID_M) 
    {
        this.ID_M = ID_M;
    }

    public int getTOTAL_VENTA() 
    {
        return TOTAL_VENTA;
    }

    public void setTOTAL_VENTA(int TOTAL_VENTA) 
    {
        this.TOTAL_VENTA = TOTAL_VENTA;
    }
    

}
