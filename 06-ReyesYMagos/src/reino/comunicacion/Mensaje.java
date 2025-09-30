package reino.comunicacion;

public class Mensaje {
	private String contenido;
	private PrioridadMensaje prioridad;
	
	public Mensaje(String contenido) {
		this(contenido, PrioridadMensaje.NORMAL);
	}
	
	public Mensaje(String contenido, PrioridadMensaje prioridad) {
		this.contenido = contenido;
		this.prioridad = prioridad;
	}
	
	public PrioridadMensaje getPrioridad() {
		return prioridad;
	}
}
