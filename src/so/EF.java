package so;

public class EF {
    boolean disponible;
    boolean fin;
    int siguiente;

    public EF() {
        this.disponible = true;
        this.fin = false;
        this.siguiente = -1;
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

	public int getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(int siguiente) {
		this.siguiente = siguiente;
	}
	
	public String toString() {
    	String strSig = (siguiente != -1) ? "C" + siguiente : "";
        return "Disponible: " + disponible + ", Final: " + fin + ", Siguiente: " + strSig;
    }
}