package Test2;

public class Datos {
    private String alias;
    private String cbu;
    private String email;
    private String titular;
    private boolean propio;
    
    public Datos(String alias, String cbu, String email, String titular, boolean propio) {
        this.alias = alias;
        this.cbu = cbu;
        this.email = email;
        this.titular = titular;
        this.propio = propio;
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

    
}
