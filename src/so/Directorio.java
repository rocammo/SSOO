package so;

import java.util.ArrayList;

public class Directorio extends Cluster {
    ArrayList<EntradaDir> entradas;

    public Directorio() {
        entradas = new ArrayList<EntradaDir>();
    }
    
//    public void agregar(EntradaDir eD) {
//    	entradas.add(eD);
//    }
//    
//    public void borrar(int index) {
//    	entradas.remove(index);
//    }
//    
//    public void borrar(EntradaDir eD) {
//    	entradas.remove(eD);
//    }

    public String toString() {
        return "" + entradas;
    }
}

enum Tipo { ARCHIVO, DIRECTORIO };
class EntradaDir {
    String nombre;
    Tipo tipo;
    int clusterIni;

    public EntradaDir(String nombre, Tipo tipo, int clusterIni) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.clusterIni = clusterIni;
    }

    public String toString() {
        return "Nombre: " + nombre + ", Tipo: " + tipo + ", ClusterIni: " + clusterIni;
    }
}