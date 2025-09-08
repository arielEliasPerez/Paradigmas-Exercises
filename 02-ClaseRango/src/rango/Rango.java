package rango;

public class Rango {
	private final double inicio;
	private final double fin;
	private final boolean incluyeInicio;
	private final boolean incluyeFin;
	
	private Rango(double inicio, double fin, boolean incluyeInicio, boolean incluyeFin) {
		if (inicio > fin) {
			double temp = inicio;
			inicio = fin;
			fin = temp;
		}
		
		this.inicio = inicio;
		this.fin = fin;
		this.incluyeInicio = incluyeInicio;
		this.incluyeFin = incluyeFin;
	}
	
	public static Rango cerrado(double inicio, double fin) {
		return new Rango(inicio, fin, true, true);
	}
	
	public static Rango abierto(double inicio, double fin) {
		return new Rango(inicio, fin, false, false);
	}
	
	public static Rango abiertoCerrado(double inicio, double fin) {
		return new Rango(inicio, fin, false, true);
	}
	
	public static Rango cerradoAbierto(double inicio, double fin) {
		return new Rango(inicio, fin, true, false);
	}
	
	public boolean contiene(double valor) {
		boolean mayorQueInicio = incluyeInicio ? valor >= inicio : valor > inicio;
		boolean menorQueFin = incluyeFin ? valor <= fin : valor < fin;
		return mayorQueInicio && menorQueFin;
	}
	
	public boolean contiene(Rango otro) {
		return this.contiene(otro.inicio) && this.contiene(otro.fin);
	}
	
	public boolean hayInterseccion(Rango otro) {
		return this.contiene(otro.inicio) || this.contiene(otro.fin) || otro.contiene(this);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;                    // misma referencia
	    if (!(o instanceof Rango)) return false;      // no es un Rango
	    
	    Rango r = (Rango) o;

	    return compareTo(r) == 0;       // compara los atributos
	}
	
	public int compareTo(Rango otro) {
		if (this.inicio != otro.inicio) {
			return Double.compare(this.inicio, otro.inicio);
		}
		if (this.fin != otro.fin) {
			return Double.compare(this.fin, otro.fin);
		}
		if (this.incluyeInicio != otro.incluyeInicio) {
			return this.incluyeInicio ? -1 : 1;
		}
		if (this.incluyeFin != otro.incluyeFin) {
			return this.incluyeFin ? -1 : 1;
		}
		return 0;
	}
	
	public static Rango[] ordenar(Rango[] rangos) {
		java.util.Arrays.sort(rangos, (r1, r2) -> r1.compareTo(r2));
		return rangos;	
	}
	
	@Override
	public String toString() {
		return (incluyeInicio ? "[" : "(") + inicio + ", " + fin + (incluyeFin ? "]" : ")");
	}
	
	public static Rango union(Rango[] rangos) {
		if (rangos == null || rangos.length == 0) {
			return null;
		}
		
		double minInicio = Double.POSITIVE_INFINITY;
		double maxFin = Double.NEGATIVE_INFINITY;
		boolean incluyeMinInicio = false;
		boolean incluyeMaxFin = false;
		
		for (Rango rango : rangos) {
			if (rango.inicio < minInicio || (rango.inicio == minInicio && rango.incluyeInicio)) {
				minInicio = rango.inicio;
				incluyeMinInicio = rango.incluyeInicio;
			}
			if (rango.fin > maxFin || (rango.fin == maxFin && rango.incluyeFin)) {
				maxFin = rango.fin;
				incluyeMaxFin = rango.incluyeFin;
			}
		}
		
		return new Rango(minInicio, maxFin, incluyeMinInicio, incluyeMaxFin);
	}
	
	public Rango sumar(Rango otro) {
		return new Rango(this.inicio, otro.fin, this.incluyeInicio, otro.incluyeFin);
	}
	
	public Rango interseccion(Rango otro) {
		if (!this.hayInterseccion(otro)) {
			return new Rango(0, 0, false, false); // Rango vacío
		}
		
		double nuevoInicio;
		double nuevoFin;
		boolean incluyeNuevoInicio;
		boolean incluyeNuevoFin;
		
		if(this.inicio == otro.inicio) {
			nuevoInicio = this.inicio;
			incluyeNuevoInicio = !this.incluyeInicio ? this.incluyeInicio : otro.incluyeInicio;  // '(' le gana a '['
			
		} 
		else if(this.inicio > otro.inicio) {
			nuevoInicio = this.inicio;
			incluyeNuevoInicio = this.incluyeInicio;
		}
		else {
			nuevoInicio = otro.inicio;
			incluyeNuevoInicio = otro.incluyeInicio;
		}
		
		if(this.fin == otro.fin) {
			nuevoFin = this.fin;
			incluyeNuevoFin = !this.incluyeFin ? this.incluyeFin : otro.incluyeFin;  // ')' le gana a ']'
		} 
		else if(this.fin < otro.fin) {
			nuevoFin = this.fin;
			incluyeNuevoFin = this.incluyeFin;
		}
		else {
			nuevoFin = otro.fin;
			incluyeNuevoFin = otro.incluyeFin;
		}			
		
		return new Rango(nuevoInicio, nuevoFin, incluyeNuevoInicio, incluyeNuevoFin);
	}
	
	public Rango desplazar(double desplazamiento) {
		return new Rango(this.inicio + desplazamiento, this.fin + desplazamiento, this.incluyeInicio, this.incluyeFin);
	}
}
