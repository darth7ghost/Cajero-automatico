package cajeroautomatico;

/**
 *
 * @author Oscar Sierra
 */
public class Cuenta {
    private int nC;
    private String usuario;
    private String contra;
    private String nombre;
    private float monto;
    private int tipo;
    private int deposito;
    private int retiroM;
    private int retiroC;

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contra
     */
    public String getContra() {
        return contra;
    }

    /**
     * @param contra the contra to set
     */
    public void setContra(String contra) {
        this.contra = contra;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nC
     */
    public int getnC() {
        return nC;
    }

    /**
     * @param nC the nC to set
     */
    public void setnC(int nC) {
        this.nC = nC;
    }

    /**
     * @return the monto
     */
    public float getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the deposito
     */
    public int getDeposito() {
        return deposito;
    }

    /**
     * @param deposito the deposito to set
     */
    public void setDeposito(int deposito) {
        this.deposito = deposito;
    }

    /**
     * @return the retiroC
     */
    public int getRetiroC() {
        return retiroC;
    }

    /**
     * @param retiroC the retiroC to set
     */
    public void setRetiroC(int retiroC) {
        this.retiroC = retiroC;
    }

    /**
     * @return the retiroM
     */
    public int getRetiroM() {
        return retiroM;
    }

    /**
     * @param retiroM the retiroM to set
     */
    public void setRetiroM(int retiroM) {
        this.retiroM = retiroM;
    }
}
