package reino.comunicacion;

import java.util.LinkedList;

import exceptions.MensajeroException;

public class Mensajero {
	LinkedList<Mensaje> mensajes;
	
	public Mensajero() {
		mensajes = new LinkedList<>();
	}
	
	public void enviarMensaje(Mensaje mensaje) {
		if(mensaje == null) {
			throw new MensajeroException("No se puede enviar un mensaje nulo.");
		}
		
		if(mensaje.getPrioridad() == PrioridadMensaje.ALTA) {
			mensajes.addFirst(mensaje);
		} else {
			mensajes.addLast(mensaje);
		}
	}
	
	public Mensaje recibirMensaje() {
		if (mensajes.isEmpty()) {
			return null;
		}
		
		return mensajes.removeFirst();
	}
}
