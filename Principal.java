import java.util.ArrayList;
import java.util.Iterator;

public class Principal {
    public static void main(String[] args) {
        FAT fat = new FAT(10);
        fat.mostrarMD();
    }
}

class FAT {
    ArrayList<EF> entradasFat;
    ArrayList<Cluster> clusters;

    public FAT(int dimension) {
        entradasFat = new ArrayList<EF>(dimension);
        clusters = new ArrayList<Cluster>(dimension);
    }

    public void formatear() {
        Iterator<EF> itr = entradasFat.iterator();
        while (itr.hasNext()) {
            itr.next().setDisponible(true);
            itr.next().setFinal(false);
        }
    }

    public void mostrarMD() {
        System.out.println(entradasFat);
    }
}

class EF {
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
        return "Disponible: " + disponible + ", Final: "+ fin;
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
    // falta cluster inicio

    public EntradaDir(String nombre, boolean tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
}