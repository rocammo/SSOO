package so;

import java.util.Scanner;

public class SO extends Proceso implements Runnable {
	FAT fat;
	ListaProcesos listaProcesos;
	BorraTMPcada5Segundos borraTMPcada5Segundos;
	
	private final int SHUTDOWN_SIGNAL = 10;
	
	public SO(String nombre, int numClusters) {
		super(nombre);
		
		this.fat = new FAT(numClusters);
		
		this.listaProcesos = new ListaProcesos();
		listaProcesos.crearProceso(this); // Proceso 'Consola'
	}
	
	public void menu() {
		System.out.println("** GreenOS \n");
		
		System.out.println(" 1.- Mostrar estado Fat.");
		System.out.println(" 2.- Crear directorio.");
		System.out.println(" 3.- Crear archivo.");
		System.out.println(" 4.- Borrar archivo.");
		System.out.println(" 5.- Borrar directorio.");
		System.out.println(" 6.- Iniciar borrado automatico.");
		System.out.println(" 7.- Mostrar borrado automatico.");
		System.out.println(" 8.- Terminar borrado automatico.");
		System.out.println(" 9.- Mover archivo a otra carpeta.");
		System.out.println("10.- Apagar.");
		System.out.println();
		
		System.out.print("> ");
		int opcion = input().nextInt();
		
		switch(opcion) {
			case 1:
				fat.mostrarMD();
				break;
			case 2: {
				System.out.print("Introduzca el nombre del nuevo directorio: ");
				String nombreDirectorio = input().next();
				System.out.print("Introduzca la carpeta donde crear el directorio: ");
				String nombreCarpeta = input().next();
				fat.crearDirectorio(new Directorio(), nombreCarpeta, nombreDirectorio);
				break;
			}
			case 3: {
				System.out.print("Introduzca el nombre del nuevo archivo: ");
				String nombreArchivo = input().next();
				System.out.print("Introduzca la carpeta donde crear el archivo: ");
				String nombreDirectorio = input().next();
				fat.crearArchivo(new Archivo(nombreArchivo), nombreDirectorio, false);
				break;
			}
			case 4: {
				System.out.print("Introduzca el nombre del archivo: ");
				String nombreArchivo = input().next();
				fat.borrarArchivo(nombreArchivo);
				break;
			}
			case 5: {
				System.out.print("Introduzca el nombre del directorio: ");
				String nombreDirectorio = input().next();
				fat.borrarDirectorio(nombreDirectorio);
				break;
			}
			case 6:
				borraTMPcada5Segundos =
					new BorraTMPcada5Segundos(fat, 5, "BorraTMPcada5Segundos");
				listaProcesos.crearProceso(borraTMPcada5Segundos);
				break;
			case 7:
				listaProcesos.mostrarProcesos();
				break;
			case 8:
				listaProcesos.matarProceso(borraTMPcada5Segundos);
				break;
			case 9: {
				System.out.print("Introduzca el nombre del archivo: ");
				String nombreArchivo = input().next();
				System.out.print("Introduzca la carpeta donde mover el archivo: ");
				String nombreDirectorioD = input().next();
				fat.mover(nombreArchivo, nombreDirectorioD);
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
