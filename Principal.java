import java.util.ArrayList;
import java.util.Iterator;

public class Principal {
    public static void main(String[] args) {
        FAT fat = new FAT(10);
        fat.agregar(new Archivo("gataca.avi"));

        fat.mostrarMD();
    }
}

class FAT {
    ArrayList<EF> entradasFat;
    ArrayList<Cluster> clusters;

    int dimension;

    public FAT(int dimension) {
        this.dimension = dimension;

        entradasFat = new ArrayList<EF>(dimension);
        clusters = new ArrayList<Cluster>(dimension);

        for (int i = 0; i < dimension; i++) {
            entradasFat.add(new EF());
        }
    }

    public void formatear() {
        Iterator<EF> itr = entradasFat.iterator();
        while (itr.hasNext()) {
            itr.next().setDisponible(true);
            itr.next().setFinal(false);
        }
    }

    public void agregar(Cluster c) {
        clusters.add(c);
    }

    public void mostrarMD() {
        Iterator<EF> itrEF = entradasFat.iterator();
        System.out.println("\nEntradas FAT: ");
        for (int i = 0; itrEF.hasNext(); i++) {
            System.out.format("%2d", i+1);
            System.out.println(".- " + itrEF.next());
        }

        System.out.println("\nClusters: ");
        for (int i = 0; i < dimension; i++) {
            System.out.format("%2d", i+1);
            if (i < clusters.size()) { // clusters.get(i) != null
                System.out.println(".- " + clusters.get(i));
            } else {
                System.out.println(".- [vacío]");
            }
        }
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
        return "Disponible: " + disponible + ", Final: " + fin;
    }
}

abstract class Cluster {
    public String toString() {
        return "";
    }
}

class Archivo extends Cluster {
    String dato;

    public Archivo(String dato) {
        this.dato = dato;
    }

    public String toString() {
        return dato;
    }
}

class Directorio extends Cluster {
    ArrayList<EntradaDir> entradas;

    public Directorio() {
        entradas = new ArrayList<EntradaDir>();
    }

    public String toString() {
        return "" + entradas;
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

    public String toString() {
        return "Nombre: " + nombre + ", Tipo: " + tipo;
    }
}