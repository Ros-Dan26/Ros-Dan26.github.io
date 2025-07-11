
package Modelo;

/**
 *
 * @author Rosal
 */
public class Direccion 
{
    private String ID_DIRECCION;
    private String ALCALDIA_MUNICIPIO;
    private String CP;
    private String CALLE;
    private String NUM_EXT;
    private String COLONIA;
    private String ID_ESTADO;

    public Direccion() 
    {
        
    }

    public Direccion(String ID_DIRECCION, String ALCALDIA_MUNICIPIO, String CP, String CALLE, String NUM_EXT, String COLONIA, String ID_ESTADO) 
    {
        this.ID_DIRECCION = ID_DIRECCION;
        this.ALCALDIA_MUNICIPIO = ALCALDIA_MUNICIPIO;
        this.CP = CP;
        this.CALLE = CALLE;
        this.NUM_EXT = NUM_EXT;
        this.COLONIA = COLONIA;
        this.ID_ESTADO = ID_ESTADO;
    }

    public String getID_DIRECCION() 
    {
        return ID_DIRECCION;
    }

    public void setID_DIRECCION(String ID_DIRECCION) 
    {
        this.ID_DIRECCION = ID_DIRECCION;
    }

    public String getALCALDIA_MUNICIPIO() 
    {
        return ALCALDIA_MUNICIPIO;
    }

    public void setALCALDIA_MUNICIPIO(String ALCALDIA_MUNICIPIO) 
    {
        this.ALCALDIA_MUNICIPIO = ALCALDIA_MUNICIPIO;
    }

    public String getCP() 
    {
        return CP;
    }

    public void setCP(String CP) 
    {
        this.CP = CP;
    }

    public String getCALLE() 
    {
        return CALLE;
    }

    public void setCALLE(String CALLE) 
    {
        this.CALLE = CALLE;
    }

    public String getNUM_EXT() 
    {
        return NUM_EXT;
    }

    public void setNUM_EXT(String NUM_EXT) 
    {
        this.NUM_EXT = NUM_EXT;
    }

    public String getCOLONIA() 
    {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) 
    {
        this.COLONIA = COLONIA;
    }

    public String getID_ESTADO() 
    {
        return ID_ESTADO;
    }

    public void setID_ESTADO(String ID_ESTADO) 
    {
        this.ID_ESTADO = ID_ESTADO;
    }

    @Override
    public String toString() 
    {
        return "Direccion{" + "ID_DIRECCION=" + ID_DIRECCION + ", ALCALDIA_MUNICIPIO=" + ALCALDIA_MUNICIPIO + ", CP=" + CP + ", CALLE=" + CALLE + ", NUM_EXT=" + NUM_EXT + ", COLONIA=" + COLONIA + ", ID_ESTADO=" + ID_ESTADO + '}';
    }
    
    
}
