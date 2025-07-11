
package Modelo;


public class Direccion 
{
    private String ID_DIR;
    private String ALCALDIA_MUNICIPIO;
    private String CP;
    private String CALLE;
    private String NUM_EXT;
    private String COLONIA;
    private String ID_ES;

    public Direccion() 
    {
        
    }

    public Direccion(String ID_DIR, String ALCALDIA_MUNICIPIO, String CP, String CALLE, String NUM_EXT, String COLONIA, String ID_ES) {
        this.ID_DIR = ID_DIR;
        this.ALCALDIA_MUNICIPIO = ALCALDIA_MUNICIPIO;
        this.CP = CP;
        this.CALLE = CALLE;
        this.NUM_EXT = NUM_EXT;
        this.COLONIA = COLONIA;
        this.ID_ES = ID_ES;
    }

    public String getID_DIR() {
        return ID_DIR;
    }

    public void setID_DIR(String ID_DIR) {
        this.ID_DIR = ID_DIR;
    }

    public String getALCALDIA_MUNICIPIO() {
        return ALCALDIA_MUNICIPIO;
    }

    public void setALCALDIA_MUNICIPIO(String ALCALDIA_MUNICIPIO) {
        this.ALCALDIA_MUNICIPIO = ALCALDIA_MUNICIPIO;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getCALLE() {
        return CALLE;
    }

    public void setCALLE(String CALLE) {
        this.CALLE = CALLE;
    }

    public String getNUM_EXT() {
        return NUM_EXT;
    }

    public void setNUM_EXT(String NUM_EXT) {
        this.NUM_EXT = NUM_EXT;
    }

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getID_ES() {
        return ID_ES;
    }

    public void setID_ES(String ID_ES) {
        this.ID_ES = ID_ES;
    }



    @Override
    public String toString() 
    {
        return "Direccion{" + "ID_DIR=" + ID_DIR + ", ALCALDIA_MUNICIPIO=" + ALCALDIA_MUNICIPIO + ", CP=" + CP + ", CALLE=" + CALLE + ", NUM_EXT=" + NUM_EXT + ", COLONIA=" + COLONIA + ", ID_ES=" + ID_ES + '}';
    }
    
    
}
