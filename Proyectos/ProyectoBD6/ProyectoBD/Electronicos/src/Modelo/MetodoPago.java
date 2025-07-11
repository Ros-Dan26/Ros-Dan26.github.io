
package Modelo;


public class MetodoPago {
    private String ID_M;
    private String METODO;
    
    public MetodoPago(){}

    public MetodoPago(String ID_M, String METODO) {
        this.ID_M = ID_M;
        this.METODO = METODO;
    }

    public String getID_M() {
        return ID_M;
    }

    public void setID_M(String ID_M) {
        this.ID_M = ID_M;
    }

    public String getMETODO() {
        return METODO;
    }

    public void setMETODO(String METODO) {
        this.METODO = METODO;
    }
    
}
