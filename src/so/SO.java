package so;

import java.util.Scanner;

public class SO {
	FAT fat;
	
	
	public SO(int numClusters) {
		this.fat = new FAT(numClusters);
	}
	
	public void encender() {
		Scanner input = new Scanner(System.in);;
		
		System.out.println("** GreenOS \n");
		
		int opcion = 0;
		while (opcion != 4) {
			System.out.println("1.- Mostrar estado Fat.");
			System.out.println("2.- Agregar archivo.");
			System.out.println("3.- Borrar archivo.");
			System.out.println("4.- Apagar.");
			System.out.println();
			
			System.out.print("> ");
			opcion = input.nextInt();
			
			switch(opcion) {
				case 1:
					fat.mostrarMD();
					break;
				case 2: {
					System.out.println("Introduzca el nombre del archivo: ");
					String nombreArchivo = input.nextLine();
					fat.agregar(new Archivo(nombreArchivo));
					break;
				}
				case 3: {
					System.out.println("Introduzca el nombre del archivo: ");
					String nombreArchivo = input.nextLine();
					fat.borrarArchivo(new Archivo(nombreArchivo));
					break;
				}
			}
			clearScreen();
		}
		input.close();
	}
	
	public void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
}
