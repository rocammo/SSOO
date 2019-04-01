package so;

public abstract class Proceso implements Runnable {
	private String nombre;
	protected boolean running;
	
	public Proceso(String nombre) {
		this.nombre = nombre;
		this.running = true;
	}
	
	public void kill() {
		this.running = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
