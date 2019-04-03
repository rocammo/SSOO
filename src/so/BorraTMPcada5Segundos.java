package so;

public class BorraTMPcada5Segundos extends Proceso implements Runnable {
    private FAT fat;
    private int tiempo;
	
	public BorraTMPcada5Segundos(FAT fat, int tiempo, String nombre) {
		super(nombre);
		this.fat = fat;
		this.tiempo = tiempo;
	}

	@Override
	public void run() {
        while (running) {
            try {
            	Thread.sleep(1000 * tiempo);
            	fat.borrarContenidoDir("tmp");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}