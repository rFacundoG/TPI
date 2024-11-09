public class AliasCliente {
    private String aliasDEBITO;
    private String aliasCREDITO;
    private String cbuDEBITO;
    private String cbuCREDITO;
    private double importeActual;
    private String concepto;
    private String motivo;
    private String referencia;
    private String email;
    private String titular;

    public AliasCliente(String aliasDEBITO, String aliasCREDITO, String cbuDEBITO, String cbuCREDITO, double importeActual, String concepto, String refencia, String email, String titular, String motivo) {
        this.aliasDEBITO = aliasDEBITO;
        this.aliasCREDITO = aliasCREDITO;
        this.cbuDEBITO = cbuDEBITO;
        this.cbuCREDITO = cbuCREDITO;
        this.importeActual = importeActual;
        this.concepto = concepto;
        this.motivo = motivo;
        this.referencia = refencia;
        this.email = email;
        this.titular = titular;
    }

    public String getConcepto() {
        return concepto;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getEmail() {
        return email;
    }

    public String getTitular() {
        return titular;
    }

    public String getAliasDEBITO() {
        return aliasDEBITO;
    }

    public String getAliasCREDITO() {
        return aliasCREDITO;
    }

    public String getCbuDEBITO() {
        return cbuDEBITO;
    }

    public String getCbuCREDITO() {
        return cbuCREDITO;
    }

    public double getImporteActual() {
        return importeActual;
    }
    
    public String getMotivo() {
        return motivo;
    }
}
