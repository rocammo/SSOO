package so;

public class EF {
    boolean disponible;
    boolean fin;
    // falta cluster siguiente

    public EF() {
        this.disponible = true;
        this.fin = false;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isFinal() {
        return fin;
    }

    public void setFinal(boolean fin) {
        this.fin = fin;
    }

    public String toString() {
        return "Disponible: " + disponible + ", Final: " + fin;
    }
}