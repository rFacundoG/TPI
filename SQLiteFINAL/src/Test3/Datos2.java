package Test3;

public class Datos2 {
    private String alias;
    private String cbu;
    private String email;
    private String titular;
    private boolean propio;
    private double importe;
    private String concepto;
    private String motivo;
    private String referencia;
    
    public Datos2(String alias, String cbu, String email, String titular, boolean propio, double importe,
            String concepto, String motivo, String referencia) {
        this.alias = alias;
        this.cbu = cbu;
        this.email = email;
        this.titular = titular;
        this.propio = propio;
        this.importe = importe;
        this.concepto = concepto;
        this.motivo = motivo;
        this.referencia = referencia;
    }

   public String getAlias() {
        return alias;
    }
    public String getCbu() {
        return cbu;
    }
    public String getEmail() {
        return email;
    }
    public String getTitular() {
        return titular;
    }
    public boolean isPropio() {
        return propio;
    }
    public double getImporte() {
        return importe;
    }
    public String getConcepto() {
        return concepto;
    }
    public String getMotivo() {
        return motivo;
    }
    public String getReferencia() {
        return referencia;
    }
}
