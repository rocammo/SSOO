package so;

import java.util.ArrayList;
import java.util.Iterator;

public class Directorio extends Cluster {
    ArrayList<EntradaDir> entradas;

    public Directorio() {
        entradas = new ArrayList<EntradaDir>();
    }
    
    public void agregarEntradaDir(EntradaDir entradaDir) {
    	entradas.add(entradaDir);
    }
    
    public boolean eliminarEntradaDir(String nombre) {
    	Iterator<EntradaDir> itrED = entradas.iterator();
    	
    	int index = 0;
    	while (itrED.hasNext()) {
    		if (itrED.next().nombre.equals(nombre)) {
    			entradas.remove(index);
    			return true;
    		}
    		index++;
    	}
    	
    	return false;
    }

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