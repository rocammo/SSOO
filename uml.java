public class Principal {
    public static void main(String[] args) {
        FAT fat = new FAT(10);
    }
}

class FAT {
    ArrayList<EF> entradasFat;
    ArrayList<Cluster> clusters;

    public FAT(int dimension) {
        entradasFAT = new ArrayList<EF>(dimension);
        clusters = new ArrayList<Cluster>(dimension);
    }

    public void formatear() {
        for (int i = 0; i < entradasFAT.length; i++) {
            entradasFat[i].setDisponible(true);
            entradasFat[i].setFinal(false);
        }
    }
}

class EF {
    boolean disponible;
    boolean fin;

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
}

abstract class Cluster {

}

class Archivo extends Cluster {
    String dato;

    public Archivo(String dato) {
        this.dato = dato;
    }
}

class Directorio extends Cluster {
    ArrayList<EntradaDir> entradas;

    public Directorio() {
        entradas = new ArrayList<EntradaDir>();
    }
}

class EntradaDir {
    String nombre;
    boolean tipo;

    public EntradaDir(String nombre, boolean tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
}