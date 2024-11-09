package Test2;

public class DatosTrasferencia {
    double importe;
    String concepto;
    String motivo;
    String referencia;

    public DatosTrasferencia(double importe, String concepto, String motivo, String referencia) {
        this.importe = importe;
        this.concepto = concepto;
        this.motivo = motivo;
        this.referencia = referencia;
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
