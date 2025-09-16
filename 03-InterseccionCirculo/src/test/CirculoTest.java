package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import coordenada.Punto;
import figura.Circulo;

class CirculoTest {

	@Test
	void testInterseccionCirculo() {
		Circulo circuloBase = new Circulo(new Punto(0.0, 0.0), 1.0);
		Circulo circuloInterseccion = new Circulo(new Punto(0.5, 0.0), 1.0);
		Circulo circuloNoInterseccion = new Circulo(new Punto(3.0, 0.0), 1.0);
		
		assertTrue(circuloBase.intersectaCon(circuloInterseccion));
		assertFalse(circuloBase.intersectaCon(circuloNoInterseccion));
	}
	
	@Test
	void testCirculosDentro() {
		Circulo circuloGrande = new Circulo(new Punto(0.0, 0.0), 5.0);
		Circulo circuloPequeno = new Circulo(new Punto(1.0, 1.0), 2.0);
		
		assertTrue(circuloGrande.intersectaCon(circuloPequeno));
		assertTrue(circuloPequeno.intersectaCon(circuloGrande));
	}
	
	@Test
	void testCirculoNulo() {
		Circulo circuloNulo = new Circulo(new Punto(2.0, 1.0), 0.0);
		
		Circulo circuloCualquier = new Circulo(new Punto(0.0, 0.0), 5.0);
		Circulo circuloOtroNulo = new Circulo(new Punto(3.0, 3.0), 0.0);
		
		assertFalse(circuloNulo.intersectaCon(circuloCualquier));
		assertFalse(circuloNulo.intersectaCon(circuloOtroNulo));
	}
	
	@Test
	void testCirculoNegativo() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Circulo(new Punto(1.0, 1.0), -1.0);
		});
	}
}
