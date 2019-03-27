package so;

public class Archivo extends Cluster {
    String dato;

    public Archivo(String dato) {
        this.dato = dato;
    }

    public String toString() {
        return dato;
    }

    public String getDato() {
        return this.dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
}