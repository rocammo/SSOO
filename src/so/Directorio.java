package so;

import java.util.ArrayList;

public class Directorio extends Cluster {
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