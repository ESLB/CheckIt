package fii.industrial.a1551.incubadora.checkit.model;

public class Registro {

    private int Numero;
    private String RUC;
    private String Monto;
    private String Fecha;

    public Registro() {
    }

    public Registro(int numero, String RUC, String monto, String fecha) {
        Numero = numero;
        this.RUC = RUC;
        Monto = monto;
        Fecha = fecha;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String monto) {
        Monto = monto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
