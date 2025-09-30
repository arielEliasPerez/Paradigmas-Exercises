package reino.magia;

public class Hechizo {
	private String nombre;
	
	public Hechizo(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Hechizo)) return false;
		Hechizo other = (Hechizo) obj;
		return this.nombre.equals(other.nombre);
	}
}
