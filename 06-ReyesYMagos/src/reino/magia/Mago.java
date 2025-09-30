package reino.magia;

import java.util.HashSet;

import exceptions.MagoException;

public class Mago {
	private HashSet<Hechizo> hechizos;
	
	public Mago() {
		hechizos = new HashSet<>();
	}
	
	public void agregarHechizo(Hechizo hechizo) {
		if(hechizo == null) {
			throw new MagoException("No se puede agregar un hechizo nulo.");
		}
		hechizos.add(hechizo);
	}
	
	public HashSet<Hechizo> hechizosEnComun(Mago otroMago) {
		if(otroMago == null) {
			throw new MagoException("El mago proporcionado es nulo.");
		}
		
		HashSet<Hechizo> comunes = new HashSet<>(this.hechizos);
		comunes.retainAll(otroMago.hechizos);
		return comunes;
	}
}
