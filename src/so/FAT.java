package so;

import java.util.ArrayList;
import java.util.Iterator;

public class FAT {
    ArrayList<EF> entradasFat;
    ArrayList<Cluster> clusters;
    Directorio root;

    int dimension;

    public FAT(int dimension) {
        this.dimension = dimension;

        entradasFat = new ArrayList<EF>(dimension);
        clusters = new ArrayList<Cluster>(dimension);

        for (int i = 0; i < dimension; i++) {
            entradasFat.add(new EF());
        }
        
        root = new Directorio();
        clusters.add(root);
        entradasFat.get(0).disponible = false;
    	entradasFat.get(0).fin = true;
    }
    
    public void crearArchivo(Archivo archivo, String directorio, boolean lecture) {
    	int clusterInicio = (lecture) ? buscarEnDirectorio(archivo.getDato()) : buscarClusterLibre();
    	if (clusterInicio >= dimension) {
    		System.out.println("\\n[ERROR] Disco lleno.");
    		return;
    	}
    	
    	if (directorio.equals("/")) {
    		root.agregarEntradaDir(new EntradaDir(archivo.getDato(), Tipo.ARCHIVO, clusterInicio));
    	} else {
    		int cluster = buscarEnDirectorio(directorio);
    		
    		if (cluster > 0) {
    			Directorio nuevoDirectorio = ((Directorio) clusters.get(cluster));
    			nuevoDirectorio.agregarEntradaDir(new EntradaDir(archivo.getDato(), Tipo.ARCHIVO, clusterInicio));
    		} else return;
    	}
    	
    	if (lecture) return;
    	clusters.add(clusterInicio, archivo);
		entradasFat.get(clusterInicio).disponible = false;
		entradasFat.get(clusterInicio).fin = true;
    }
    
    public void crearDirectorio(Directorio dir, String directorio, String nombre) {
    	int clusterInicio = buscarClusterLibre();
    	if (clusterInicio >= dimension) {
    		System.out.println("\\n[ERROR] Disco lleno.");
    		return;
    	}
    	
    	if (directorio.equals("/")) {
    		root.agregarEntradaDir(new EntradaDir(nombre, Tipo.DIRECTORIO, clusterInicio));
    	} else {
    		int cluster = buscarEnDirectorio(directorio);
    		
    		if (cluster > 0) {
    			Directorio nuevoDirectorio = ((Directorio) clusters.get(cluster));
    			nuevoDirectorio.agregarEntradaDir(new EntradaDir(nombre, Tipo.DIRECTORIO, clusterInicio));
    		} else return;
    	}
    	
		clusters.add(clusterInicio, dir);
    	entradasFat.get(clusterInicio).disponible = false;
    	entradasFat.get(clusterInicio).fin = true;
    }

    public void borrarArchivo(String nombre) {
    	for (int i = 0; i < clusters.size(); i++) {
    		if (clusters.get(i) instanceof Directorio) continue;
    		if (((Archivo) clusters.get(i)).getDato().equals(nombre)) {
    			entradasFat.get(i).disponible = true;
    			eliminarDeDirectorioPadre(nombre);
    			return;
    		}
    	}
        
        System.out.println("\n[ERROR] Archivo no encontrado.");
    }
    
    public void borrarDirectorio(String nombre) {
    	int clusterInicio = buscarEnDirectorio(nombre);
    	if (clusterInicio < 0) {
    		System.out.println("\n[ERROR] Carpeta no encontrado.");
    		return;
    	}
    	
    	// "Elimina" (solo en apariencia) el contenido del directorio
    	for (int i = 0; i < ((Directorio) clusters.get(clusterInicio)).entradas.size(); i++) {
    		int index = ((Directorio) clusters.get(clusterInicio)).entradas.get(i).clusterIni;
    		entradasFat.get(index).disponible = true;
    	}
    	
    	entradasFat.get(clusterInicio).disponible = true;
    	
    	// Elimina la entrada en el directorio padre
    	eliminarDeDirectorioPadre(nombre);
    }
    
    public void mover(String nombre, String directorioD) {
    	crearArchivo(new Archivo(nombre), directorioD, true);
    	eliminarDeDirectorioPadre(nombre);
    }
    
    public void borrarContenidoDir(String nombre) {
    	int clusterInicio = buscarEnDirectorio(nombre);
    	if (clusterInicio < 0) {
    		System.out.println("\n[ERROR] Carpeta no encontrado.");
    		return;
    	}
    	
    	if (!((Directorio) clusters.get(clusterInicio)).entradas.isEmpty()) {
    		int index = ((Directorio) clusters.get(clusterInicio)).entradas.get(0).clusterIni;
    		entradasFat.get(index).disponible = true;
    		((Directorio) clusters.get(clusterInicio)).entradas.remove(0);
    	}
    	
    }
    
    private void eliminarDeDirectorioPadre(String nombre) {
    	for (int i = 0; i < clusters.size(); i++) {
    		if (clusters.get(i) instanceof Directorio) {
    			if (((Directorio) clusters.get(i)).eliminarEntradaDir(nombre)) {
    				return;
    			}
    		}
		}
    }
    
    private int buscarEnDirectorio(String nombre) {
    	for (int i = 0; i < clusters.size(); i++) {
    		if (clusters.get(i) instanceof Directorio) {
    			for (int j = 0; j < ((Directorio) clusters.get(i)).entradas.size(); j++) {
        			EntradaDir entradaDir = ((Directorio) clusters.get(i)).entradas.get(j);
        			boolean disp = ((EF) entradasFat.get(i)).disponible;
        			
        			if (entradaDir.nombre.equals(nombre) && !disp) {
        				return entradaDir.clusterIni;
        			}
        		}
    		}
    	}
    	
    	return -1;
    }
    
    private int buscarClusterLibre() {
    	int clusterLibre = clusters.size();
    	
    	for (int i = 0; i < clusters.size(); i++) {
    		if (entradasFat.get(i).isDisponible()) {
    			clusterLibre = i;
    			break;
    		}
    	}
    	
    	return clusterLibre;
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
            System.out.format("%2d", i);
            System.out.println(".- " + itrEF.next());
        }

        System.out.println("\nClusters: ");
        for (int i = 0; i < dimension; i++) {
            System.out.format("%2d", i);
            if (entradasFat.get(i).isDisponible()) {
            	System.out.println(".- VACIO");
            } else {
            	System.out.println(".- " + clusters.get(i));
            }
        }
    }
}