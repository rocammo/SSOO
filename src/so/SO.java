package so;

import java.util.Scanner;

public class SO extends Proceso implements Runnable {
	FAT fat;
	ListaProcesos listaProcesos;
	
	private final int SHUTDOWN_SIGNAL = 4;
	
	public SO(String nombre, int numClusters) {
		super(nombre);
		
		this.fat = new FAT(numClusters);
		
		this.listaProcesos = new ListaProcesos();
		listaProcesos.crearProceso(this);
	}
	
	public void menu() {
		System.out.println("** GreenOS \n");
		
		System.out.println("1.- Mostrar estado Fat.");
		System.out.println("2.- Agregar archivo.");
		System.out.println("3.- Borrar archivo.");
		System.out.println("4.- Apagar.");
		System.out.println();
		
		System.out.print("> ");
		int opcion = input().nextInt();
		
		switch(opcion) {
			case 1:
				fat.mostrarMD();
				break;
			case 2: {
				System.out.print("Introduzca el nombre del archivo: ");
				String nombreArchivo = input().next();
				fat.agregar(new Archivo(nombreArchivo));
				break;
			}
			case 3: {
				System.out.print("Introduzca el nombre del archivo: ");
				String nombreArchivo = input().next();
				fat.borrarArchivo(new Archivo(nombreArchivo));
				break;
			}
			case SHUTDOWN_SIGNAL:
				running = false;
		}
		if (opcion != SHUTDOWN_SIGNAL) {
			pressAnyKeyToContinue();
			clearScreen();
		}
	}

	private Scanner input() {
		return new Scanner(System.in);
	}
	
	private void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
	
	private void pressAnyKeyToContinue()
	 { 
        System.out.println("\nPresione ENTER para continuar...");
        try {
            System.in.read();
        } catch(Exception e) {
        	e.printStackTrace();
        }
	 }

	@Override
	public void run() {
		while (running) {
			menu();
		}
	}
}
