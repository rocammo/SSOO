package so;

import java.util.ArrayList;
import java.util.Iterator;

public class FAT {
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
    	int index = isLibre();
    	if (index == -1) {
    		System.out.println("\n[ERROR] Disco lleno.");
    		return;
    	}
    	
        clusters.add(index, c);
    }

    public void borrarArchivo(Cluster c) {
        Iterator<Cluster> itrA = clusters.iterator();
        for (int i = 0; itrA.hasNext(); i++) {
            if (((Archivo)itrA.next()).getDato() == ((Archivo)c).getDato()) {
                entradasFat.get(i).setDisponible(true);
                return;
            }
        }
        
        System.out.println("[ERROR] No se ha encontrado el archivo");
    }

    public void formatear() {
        Iterator<EF> itr = entradasFat.iterator();
        while (itr.hasNext()) {
            itr.next().setDisponible(true);
            itr.next().setFinal(false);
        }
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
            if (entradasFat.get(i).isDisponible()) {
            	System.out.println(".- VACIO");
            } else {
            	System.out.println(".- " + clusters.get(i));
            }
        }
    }

    private int isLibre() {
        for (int i = 0; i < dimension; i++) {
            if (entradasFat.get(i).isDisponible()) {
                entradasFat.get(i).setDisponible(false);
                return i;
            }
        }

        return -1;
    }
}