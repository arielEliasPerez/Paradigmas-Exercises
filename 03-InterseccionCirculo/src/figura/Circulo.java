package figura;

import coordenada.Punto;

public class Circulo {
	private final Punto centro;
	private final double radio;
	
	public Circulo(Punto centro, double radio) {
		if (radio < 0) {
			throw new IllegalArgumentException("El radio no puede ser negativo");
		}
		this.centro = centro;
		this.radio = radio;
	}
	
	public boolean intersectaCon(Circulo otro) {
		if (this.radio == 0 || otro.radio == 0) {
			return false;
		}
		double distanciaCentros = this.centro.distancia(otro.centro);
		return distanciaCentros <= (this.radio + otro.radio);
	}
}
