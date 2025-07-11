package Modelo;


public class Cliente {
    private String ID_CLIENTE;
    private String NOMBRE_CLIENTE;
    private String TELEFONO_C;
    private String CORREO_C;
    
    public Cliente(){}

    public Cliente(String ID_CLIENTE, String NOMBRE_CLIENTE, String TELEFONO_C, String CORREO_C) {
        this.ID_CLIENTE = ID_CLIENTE;
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
        this.TELEFONO_C = TELEFONO_C;
        this.CORREO_C = CORREO_C;
    }

    public String getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(String ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getNOMBRE_CLIENTE() {
        return NOMBRE_CLIENTE;
    }

    public void setNOMBRE_CLIENTE(String NOMBRE_CLIENTE) {
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
    }

    public String getTELEFONO_C() {
        return TELEFONO_C;
    }

    public void setTELEFONO_C(String TELEFONO_C) {
        this.TELEFONO_C = TELEFONO_C;
    }

    public String getCORREO_C() {
        return CORREO_C;
    }

    public void setCORREO_C(String CORREO_C) {
        this.CORREO_C = CORREO_C;
    }
    
       @Override
    public String toString() 
    {
        return "Cliente{" + "ID_CLIENTE=" + ID_CLIENTE + ", NOMBRE_CLIENTE=" + NOMBRE_CLIENTE + ", TELEFONO_C=" + TELEFONO_C + ", CORREO_C=" + CORREO_C + '}';
    }
    
    
}
