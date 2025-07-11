package Modelo;

public class Sucursal {
    private String ID_DE_LA_SUCURSAL;
    private String NOMBRE_SUCURSAL;
    private String TELEFONO_S;
    private String CORREO_S;
    private String PAGINA_WEB_ID_PW;

    public Sucursal() {
        // Constructor por defecto
    }

    public Sucursal(String ID_DE_LA_SUCURSAL, String NOMBRE_SUCURSAL, String TELEFONO_S, String CORREO_S, String PAGINA_WEB_ID_PW) {
        this.ID_DE_LA_SUCURSAL = ID_DE_LA_SUCURSAL;
        this.NOMBRE_SUCURSAL = NOMBRE_SUCURSAL;
        this.TELEFONO_S = TELEFONO_S;
        this.CORREO_S = CORREO_S;
        this.PAGINA_WEB_ID_PW = PAGINA_WEB_ID_PW;
    }

    public String getID_DE_LA_SUCURSAL() {
        return ID_DE_LA_SUCURSAL;
    }

    public void setID_SUCURSAL(String ID_SUCURSAL) {
        this.ID_DE_LA_SUCURSAL = ID_SUCURSAL;
    }

    public String getNOMBRE_SUCURSAL() {
        return NOMBRE_SUCURSAL;
    }

    public void setNOMBRE_SUCURSAL(String NOMBRE_SUCURSAL) {
        this.NOMBRE_SUCURSAL = NOMBRE_SUCURSAL;
    }

    public String getTELEFONO_S() {
        return TELEFONO_S;
    }

    public void setTELEFONO_S(String TELEFONO_S) {
        this.TELEFONO_S = TELEFONO_S;
    }

    public String getCORREO_S() {
        return CORREO_S;
    }

    public void setCORREO_S(String CORREO_S) {
        this.CORREO_S = CORREO_S;
    }

    public String getPAGINA_WEB_ID_PW() {
        return PAGINA_WEB_ID_PW;
    }

    public void setPAGINA_WEB_ID_PW(String PAGINA_WEB_ID_PW) {
        this.PAGINA_WEB_ID_PW = PAGINA_WEB_ID_PW;
    }
}
