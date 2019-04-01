package so;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaProcesos {
	private ArrayList<Proceso> listaProcesos;
	
	public ListaProcesos() {
		listaProcesos = new ArrayList<Proceso>();
	}
	
	public void crearProceso(Proceso p) {
		listaProcesos.add(p);
		
		Thread proceso = new Thread(p);
		proceso.start();
	}
	
	public void matarProceso(Proceso p) {
		for (int i = 0; i < listaProcesos.size(); i++) {
			if (listaProcesos.get(i).getNombre().equals(p.getNombre())) {
				listaProcesos.get(i).kill();
				listaProcesos.remove(i);
				System.out.println();
				return;
			}
		}
	}
	
	public void mostrarProceso() {
		Iterator<Proceso> itrLP = listaProcesos.iterator();
        System.out.println("\nProcesos: ");
        for (int i = 0; itrLP.hasNext(); i++) {
            System.out.format("%2d", i+1);
            System.out.println(".- " + itrLP.next());
        }
	}
}
