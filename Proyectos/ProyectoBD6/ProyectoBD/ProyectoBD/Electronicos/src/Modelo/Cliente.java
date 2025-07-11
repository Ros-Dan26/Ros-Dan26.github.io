
package Modelo;

/**
 *
 * @author Rosal
 */
public class Cliente 
{
    private String ID_CLIENTE;
    private String NOMBRE_CLIENTE;
    private String TELEFONO_CLIENTE;
    private String CORREO_CLIENTE;

    public Cliente() 
    {
        
    }

    public Cliente(String ID_CLIENTE, String NOMBRE_CLIENTE, String TELEFONO_CLIENTE, String CORREO_CLIENTE) 
    {
        this.ID_CLIENTE = ID_CLIENTE;
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
        this.TELEFONO_CLIENTE = TELEFONO_CLIENTE;
        this.CORREO_CLIENTE = CORREO_CLIENTE;
    }

    public String getID_CLIENTE() 
    {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String ID_CLIENTE) 
    {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getNOMBRE_CLIENTE() 
    {
        return NOMBRE_CLIENTE;
    }

    public void setNOMBRE_CLIENTE(String NOMBRE_CLIENTE) 
    {
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
    }

    public String getTELEFONO_CLIENTE() 
    {
        return TELEFONO_CLIENTE;
    }

    public void setTELEFONO_CLIENTE(String TELEFONO_CLIENTE) 
    {
        this.TELEFONO_CLIENTE = TELEFONO_CLIENTE;
    }

    public String getCORREO_CLIENTE() 
    {
        return CORREO_CLIENTE;
    }

    public void setCORREO_CLIENTE(String CORREO_CLIENTE) 
    {
        this.CORREO_CLIENTE = CORREO_CLIENTE;
    }

    @Override
    public String toString() 
    {
        return "Cliente{" + "ID_CLIENTE=" + ID_CLIENTE + ", NOMBRE_CLIENTE=" + NOMBRE_CLIENTE + ", TELEFONO_CLIENTE=" + TELEFONO_CLIENTE + ", CORREO_CLIENTE=" + CORREO_CLIENTE + '}';
    }
    
    
    
}
