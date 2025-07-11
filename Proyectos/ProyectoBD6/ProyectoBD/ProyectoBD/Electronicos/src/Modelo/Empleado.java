
package Modelo;

public class Empleado 
{
    private String ID_EMPLEADO;
    private String NOMBRE_EMPLEADO;

    public Empleado()
    {
        
    }

    public Empleado(String ID_EMPLEADO, String NOMBRE_EMPLEADO) 
    {
        this.ID_EMPLEADO = ID_EMPLEADO;
        this.NOMBRE_EMPLEADO = NOMBRE_EMPLEADO;
    }

    public String getID_EMPLEADO() 
    {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(String ID_EMPLEADO) 
    {
        this.ID_EMPLEADO = ID_EMPLEADO;
    }

    public String getNOMBRE_EMPLEADO() 
    {
        return NOMBRE_EMPLEADO;
    }

    public void setNOMBRE_EMPLEADO(String NOMBRE_EMPLEADO) 
    {
        this.NOMBRE_EMPLEADO = NOMBRE_EMPLEADO;
    }
   
}
