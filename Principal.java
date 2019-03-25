import java.util.ArrayList;
import java.util.Iterator;

/**
 * TODO
 *
 * 1. Cuando borramos varios archivos, falla
 * 2. Elementos null dentro del ArrayList clusters
 */

public class Principal {
    public static void main(String[] args) {
        FAT fat = new FAT(10);
        fat.agregar(new Archivo("gataca.avi"));

        fat.mostrarMD();
        fat.agregar(new Archivo("gataca.avi"));
        fat.agregar(new Archivo("gataca.avi"));
        fat.mostrarMD();
        fat.borrarArchivo(new Archivo("gataca.avi"));
        fat.mostrarMD();
        // fat.borrarArchivo(new Archivo("gataca.avi"));
        // fat.borrarArchivo(new Archivo("gataca.avi"));
        // fat.mostrarMD();
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

    public void agregar(Cluster c) {
        if (isLibre()) {
            clusters.add(c);
        }
    }

    public void borrarArchivo(Cluster c) {
        Iterator<Cluster> itrA = clusters.iterator();
        for (int i = 0; itrA.hasNext(); i++) {
            if (((Archivo)itrA.next()).getDato() == ((Archivo)c).getDato()) {
                entradasFat.get(i).setDisponible(true);
                clusters.set(i, null); // en la realidad no sucede, pues el SO es un vago
                return;
            }
        }
    }

    public void formatear() {
        Iterator<EF> itr = entradasFat.iterator();
        while (itr.hasNext()) {
            itr.next().setDisponible(true);
            itr.next().setFinal(false);
        }
    }

    public boolean isLibre() {
        for (int i = 0; i < dimension; i++) {
            if (entradasFat.get(i).isDisponible()) {
                entradasFat.get(i).setDisponible(false);
                return true;
            }
        }

        return false;
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
                System.out.println(".- null");
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

    public String getDato() {
        return this.dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
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