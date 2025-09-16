package coordenada;

public class Punto {
	private final double x;
	private final double y;
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double distancia(Punto otro) {
		return Math.sqrt(Math.pow(this.x - otro.x, 2) + Math.pow(this.y - otro.y, 2));
	}
}
